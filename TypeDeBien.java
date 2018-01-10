import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/* 
 * classe qui définit les types de biens et les actions possibles sur ces types 
 */
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
    
    /**
     * Permet de saisir un nouveau type de bien
     */
    void saisirTypeDeBien () {
        Scanner sc = new Scanner (System.in);

        System.out.print ("Entrez le nom du nouveau type de bien : ");
        nom = sc.nextLine();
    }

    /**
     * Renvoie les attributs du type de bien sous forme de chaine de caractéres
     */
    public String toString () {

        return "\nIdentifiant : " + identifiant + "\n"
                + "Nom du type de bien : " + nom + "\n";

    }

    /**
     * Permet de sauvegarder un type de bien dans un fichier binaire
     * @param dos le stream qui permet d'écrire dans le fichier
     * @throws IOException
     */
    void sauvegarderTypeDeBien (DataOutputStream dos) throws IOException {

        dos.writeInt(identifiant);
        dos.writeUTF(nom);
    }

    /**
     * Permet de charger un type de bien depuis le fichier binaire
     * @param dis le stream permettant de lire dans le fichier
     * @throws IOException
     */
    void chargerTypeDeBien (DataInputStream dis) throws IOException {

        identifiant = dis.readInt();
        nom = dis.readUTF();
    }

    /**
     * Permet de modifier l'identifiant du type de bien
     * @param identifiant
     */
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Permet de récupérer l'identifiant du type de bien
     * @return
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Permet de modifier le nom du type de bien
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Permet de récupérer le nom du type de bien
     * @return
     */
    public String getNom (){
        return nom;
    }
}