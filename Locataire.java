import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Classe repésentant un locataire
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

    /**
     * Permet la saisie d'un nouveau locataire
     */
    void saisirLocataire () {
        Scanner sc = new Scanner (System.in);

        System.out.print ("Entrez le nom du locataire : ");
        nom = sc.nextLine();

        System.out.print ("Entrez l'adresse du locataire : ");
        adresse = sc.nextLine();

        System.out.print ("Entrez le numéro de téléphone du locataire : ");
        numTel = sc.nextLine();

    }


    /**
     * Affiche les attributs d'un objet Locataire
     * @return les attributs d'un locataire
     */
    public String toString () {

        return "\nIdentifiant : " + identifiant + "\n"
                + "Nom : " + nom + "\n"
                + "Adresse : " + adresse + "\n"
                + "Numéro de téléphone : " + numTel + "\n"
                + "Nombre de biens loués : " + nbBiensLoues;

    }

    /**
     * Affiche un locataire de manière simplifiée
     * @return identifiant et nom
     */
    String affichageSimplifieLocataire () {

        return "\n" + identifiant + " : " + nom;
    }

    /**
     * Permet de sauvegarder un locataire
     * @param dos fichier binaire de sauvegarde
     * @throws IOException
     */
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

    /**
     * Permet de chargzer un locataire
     * @param dis fichier binaire à charger
     * @throws IOException
     */
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

    /**
     * Donne un identifiant unique généré auparavant à un locataire
     * @param ident Identifiant généré
     */
    public void setIdentifiant(int ident) {
        this.identifiant = ident;
    }

    /**
     * Permet de récupérer l'identifiant d'un locataire
     * @return Identifiant du locataire
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Permet de récupérer le nombre total de biens loués par le locataire
     * @return le nombre total de biens loués
     */
    public int getNbBiensLoues() {
        return nbBiensLoues;
    }

    /**
     * Permet de remplacer le nom d'un locataire
     * @param nom Le nouveau nom à donner
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Permet de remplacer le numéro de téléphone d'un locataire
     * @param numTel Le nouveau numéro de téléphone
     */
    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    /**
     * Permet de remplacer l'adresse d'un locataire
     * @param adresse La nouvelle adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}