/**
 * Classe de menu gérant toutes les options de location
 */
import java.io.IOException;
import java.util.Scanner;

public class MenuLocations {
    Scanner sc = new Scanner(System.in);
    int choixMenuLocations;
    ListeLocataires listeLocataires = new ListeLocataires();
    ListeBiens listeDeBiens = new ListeBiens();

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
                    if (listeDeBiens.getNbBiens() == 0) {
                        System.out.println("Aucun bien enregistré !");
                        break;
                    }
                    listeDeBiens.afficherListeSimplifiee();
                    System.out.print("Entrez l'identifiant du bien à louer : ");
                    int idBien = sc.nextInt();
                    
                    if (listeLocataires.getNbLocataires() == 0) {
                    	System.out.println("Aucun locataire enregistré !");
                    	break;
                    }
                    listeLocataires.afficherListeSimplifiee();
                    System.out.print("Entrez l'identifiant du locataire : ");
                    int idLoc = sc.nextInt();

                    listeLocataires.louer(idBien, idLoc);
                    listeDeBiens.louer(idBien, idLoc);
                    break;

                case 2:
                	if (listeDeBiens.getNbBiens() == 0) {
                		System.out.println("Aucun bien enregistré !");
                        break;
                	}
                	listeDeBiens.afficherListeSimplifiee();
                    System.out.print("Entrez l'identifiant du bien à libérer : ");
                    int idBienALiberer = sc.nextInt();
                    
                    if (listeLocataires.getNbLocataires() == 0) {
                    	System.out.println("Aucun locataire enregistré !");
                    	break;
                    }
                    listeLocataires.afficherListeSimplifiee();
                    System.out.print("Entrez l'identifiant du locataire du bien à libérer : ");
                    int idLocDuBienALiberer = sc.nextInt();
                    
                    listeLocataires.liberer(idBienALiberer, idLocDuBienALiberer);
                    listeDeBiens.liberer(idBienALiberer);
                    break;

                case 3:
                	listeDeBiens.afficherLaListeDesBiensLoues();
                    System.out.print ("La liste des biens actuellement loués est la suivante : ");
                    listeDeBiens.afficherLaListeDesBiensLoues();
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
