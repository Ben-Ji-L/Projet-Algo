public class ListeLocataires {

    private Locataire [] tableauDeLocataires;
    private int nbLocataires;
    private int compteur;

    ListeLocataires () {

        tableauDeLocataires = new Locataire [500];
        nbLocataires = 0;
        compteur = 1;
    }

    void ajouterLocataire (Locataire loc) {
        int ident;

        ident = genererIdentifiant();
        loc.setIdentifiant(ident);
        tableauDeLocataires [nbLocataires] = loc;
        nbLocataires ++;

    }

    void afficherListeDesLocataires () {
        String resultat = "";

        for (int i=0; i<nbLocataires; i++) {

            resultat += tableauDeLocataires [i].toString() + "\n";
        }
        System.out.println (resultat);
    }

    int genererIdentifiant () {
        int  identifiant;

        identifiant = compteur;
        compteur ++;
        return identifiant;
    }
}
