import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Classe représentant un locataire
 */
class Locataire implements Comparable<Locataire>{


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

        System.out.print ("\nEntrez le nom du locataire : ");
        nom = sc.nextLine();

        System.out.print ("Entrez l'adresse du locataire : ");
        adresse = sc.nextLine();

        System.out.print ("Entrez le numéro de téléphone du locataire : ");
        numTel = sc.nextLine();

        System.out.print ("\nLocataire correctement enregistré.\n\n");

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

    boolean loueCeTypeDeBien (int identType) throws IOException {
        ListeBiens listeDeBiens = new ListeBiens();

        for (int i=0; i<nbBiensLoues; i++) {
            if (listeDeBiens.getIDTypeDeBien(listeBiens [i]) == identType) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permet d'ajouter un bien aux biens loués un locataire
     * @param identBien Identifiant du bien
     */
    void ajouterUnBien (int identBien) {
        listeBiens [nbBiensLoues] = identBien;
        nbBiensLoues ++;
    }

    /**
     * Permet de supprimer un bien des la liste des biens loués d'un locataire
     * @param identBien Identifiant du bien à supprimer
     */
    void supprimerUnBien (int identBien) {
        boolean trouve = false;

        //On parcourt la liste des biens loués jusqu'à l'avant dernier bien pour trouver le bien à supprimer
        for (int i=0; i<nbBiensLoues-1; i++) {
            if (listeBiens[i] == identBien) {
                trouve = true;
            }
            //Si on a trouvé le bien à supprimer, on l'écrase en le remplaçant
            // par le bien qui suit, et ainsi de suite jusqu'à la fin de la boucle de recherche
            if (trouve) {
                listeBiens[i] = listeBiens[i+1];
            }
        }

        //Si on n'a pas trouvé le bien à supprimer en parcourant la liste jusqu'à l'avant-dernier,
        //on cherche s'il ne se trouve pas en dernière position de la liste
        if (!trouve) {
            if (listeBiens[nbBiensLoues-1] == identBien) {
                trouve = true;
            }
        }

        //Une fois trouvé et les modifications à faire effectuées, on ignore la dernière case du tableau
        //et on diminue le nombre de biens de 1 pour la "supprimer"
        if (trouve) {
            listeBiens[nbBiensLoues-1] = -1;
            nbBiensLoues --;
            System.out.println ("Locataire supprimé.\n");
        }
        else {
            System.out.println ("Identifiant inconnu.\n");
        }
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
     * Permet de charger un locataire
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
     * Permet de comparer un objet Locataire à un autre objet de même type
     * @param autre L'autre objet Locataire avec lequelle on fait la comparaison
     * @return Un entier qui donne le résultat de la comparaison
     */
    @Override
    public int compareTo(Locataire autre) {
    	return nom.compareTo(autre.nom);
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
     * Permet de récupérer la liste des biens loués par le locataire
     * @return le tableau contenant tous les biens loués
     */
    public int[] getListeBiens() {
        return listeBiens;
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