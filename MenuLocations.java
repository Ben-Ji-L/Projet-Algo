/**
 * Classe de menu gérant toutes les options de location
 */
import java.io.IOException;
import java.util.Scanner;

public class MenuLocations {
    Scanner sc = new Scanner(System.in);
    int choixMenuLocations;
    ListeLocataires listeLocataires = new ListeLocataires();

    public MenuLocations() throws IOException {
    }

    void afficherMenu () throws IOException {

        do {
            System.out.print("\n______MENU DES LOCATIONS______\n\n" +
                    "[1] Louer un bien\n" +
                    "[2] Libérer un bien\n" +
                    "[3] Afficher la liste des biens loués\n" +
                    "[4] Afficher la liste des locataires de biens\n" +
                    "[5] Afficher la liste des locataires ayant au moins un bien en cours de location\n" +
                    "[6] Sortir du menu\n\n");


            System.out.print("Entrez le numéro de l'action souhaitée : ");
            choixMenuLocations = sc.nextInt();

            switch (choixMenuLocations) {

                case 1:
                    ListeBiens listeB = new ListeBiens();
                    if (listeB.getNbBiens() == 0) {
                        System.out.println("Aucun Biens enregistré !");
                        break;
                    }
                    listeB.afficherListeSimplifiee();
                    System.out.print("Entrez l'identifiant du bien à louer : ");
                    int idBien = sc.nextInt();

                    ListeLocataires listeLoc = new ListeLocataires();
                    listeLoc.afficherListeSimplifiee();
                    System.out.print("Entrez l'identifiant du locataire : ");
                    int idLoc = sc.nextInt();

                    listeLocataires.louer(idBien, idLoc);


                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    listeLocataires.afficherLocatairesLouantAuMoinsUnBien(true);
                    break;

                case 5:
                    listeLocataires.afficherLocatairesLouantAuMoinsUnBien(false);
                    break;

                case 6:
                    break;

                default:
                    System.out.print ("Commande incorrecte.");

            }
        } while (choixMenuLocations != 6);
    }
}
