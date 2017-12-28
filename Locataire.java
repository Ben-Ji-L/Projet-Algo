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
    private int nbBiensLoués;

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Construit un locataire
     */
    Locataire () {

        identifiant = -1;
        nom = "";
        adresse = "";
        numTel = "";
        listeBiens = new int[5];
        nbBiensLoués = 0;

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
                + "Nombre de biens loués : " + nbBiensLoués;

    }

    
}