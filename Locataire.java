import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Représente un locataire
 */
class Locataire {


    private int identifiant;
    private String nom;
    private String adresse;
    private String numTel;
    private int [] listeBiens;
    private int nbBiensLoues;

    /**
     * Construit un locataire
     */
    Locataire () {

        identifiant = -1;
        nom = "";
        adresse = "";
        numTel = "";
        listeBiens = new int[5];
        nbBiensLoues = 0;

    }

    void saisirLocataire () {
        Scanner sc = new Scanner (System.in);

        System.out.print ("Entrez le nom du locataire : ");
        nom = sc.nextLine();

        System.out.print ("Entrez l'adresse du locataire : ");
        adresse = sc.nextLine();

        System.out.print ("Entrez le numéro de téléphone du locataire : ");
        numTel = sc.nextLine();

    }


    public String toString () {

        return "\nIdentifiant : " + identifiant + "\n"
                + "Nom : " + nom + "\n"
                + "Adresse : " + adresse + "\n"
                + "Numéro de téléphone : " + numTel + "\n"
                + "Nombre de biens loués : " + nbBiensLoues;

    }

    String affichageSimplifieLocataire () {

        return "\n" + identifiant + " : " + nom;
    }

    void sauvegarderLocataire (DataOutputStream dos) throws IOException {

        dos.writeInt(identifiant);
        dos.writeUTF(nom);
        dos.writeUTF(adresse);
        dos.writeUTF(numTel);
        dos.writeInt(nbBiensLoues);
        for (int i = 0; i< nbBiensLoues; i++){
            dos.writeInt(listeBiens[i]);
        }
    }

    void chargerLocataire (DataInputStream dis) throws IOException {

        identifiant = dis.readInt();
        nom = dis.readUTF();
        adresse = dis.readUTF();
        numTel = dis.readUTF();
        nbBiensLoues = dis.readInt();
        for (int i=0; i<nbBiensLoues; i++) {
            listeBiens[i] = dis.readInt();
        }
    }

    public void setIdentifiant(int ident) {
        this.identifiant = ident;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public int getNbBiensLoues() {
        return nbBiensLoues;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}