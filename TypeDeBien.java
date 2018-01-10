/* 
classe qui définit les types de biens et les actions possibles sur ces types 
*/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

class TypeDeBien {
    private int identifiant;
    public String nom;

    /**
     * Construit un type de bien
     */
    TypeDeBien () {
        identifiant = -1;
        nom = "";
    }

    void saisirTypeDeBien () {
        Scanner sc = new Scanner (System.in);

        System.out.print ("Entrez le nom du nouveau type de bien : ");
        nom = sc.nextLine();
    }


    public String toString () {

        return "\nIdentifiant : " + identifiant + "\n"
                + "Nom du type de bien : " + nom + "\n";

    }

    void sauvegarderTypeDeBien (DataOutputStream dos) throws IOException {

        dos.writeInt(identifiant);
        dos.writeUTF(nom);
    }

    void chargerTypeDeBien (DataInputStream dis) throws IOException {

        identifiant = dis.readInt();
        nom = dis.readUTF();
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom (){
        return nom;
    }
}