import java.io.IOException;
import java.util.Scanner;

public class MenuLocataires {

    void afficherMenu () throws IOException {

        int choix;
        int ident;
        Scanner sc = new Scanner (System.in);
        ListeLocataires liste = new ListeLocataires();

        liste.chargerListe();

        do {
            System.out.print("\n__________MENU DES LOCATAIRES__________\n\n" +
                    "[1] Ajouter un locataire\n" +
                    "[2] Modifier un locataire\n" +
                    "[3] Supprimer un locataire\n" +
                    "[4] Afficher la liste des locataires par ordre alphabétique\n" +
                    "[5] Afficher la liste des locataires par type de bien\n" +
                    "[6] Rechercher la liste des locations d'un locataire\n" +
                    "[7] Sortir du menu\n\n");

            System.out.print("Entrez le numéro de l'action souhaitée : ");
            choix = sc.nextInt();

            switch (choix) {

                case 1:
                    Locataire loc = new Locataire();
                    loc.saisirLocataire();
                    liste.ajouterLocataire(loc);
                    liste.sauvegarderListe();
                    break;

                case 3:
                    if (liste.getNbLocataires()==0) {
                        System.out.println ("Il n'y a aucun locataire enregistré.\n");
                        return;
                    }
                    liste.afficherListeSimplifiee();
                    System.out.print ("Entrez le numéro identifiant du locataire à supprimer : ");
                    ident = sc.nextInt();
                    liste.supprimerLocataire(ident);
                    liste.sauvegarderListe();
                    break;


                case 4:
                    liste.afficherListeDesLocataires();
                    break;
            }
        } while (choix!=7);
    }
}
