import java.io.IOException;
import java.util.Scanner;

/**
 * Classe gérant le menu des biens
 */
public class MenuBiens {

    Scanner sc = new Scanner(System.in);

    /**
     * Affiche le menu des biens
     * @throws IOException
     */
    void afficherMenu () throws IOException {

    	// Le numéro de l'action à effectuer
        int numChoisi;
        int id;
        // La liste des biens
        ListeBiens listeB = new ListeBiens();

        do {
        	// Le menu principal des biens
            System.out.print("\n__________MENU DES BIENS__________\n\n" +
                    "[1] Ajouter un bien\n" +
                    "[2] Modifier un bien\n" +
                    "[3] Supprimer un bien\n" +
                    "[4] Afficher la liste des biens par identifiant\n" +
                    "[5] Afficher la liste des biens par ordre alphabétique\n" +
                    "[6] Sortir du menu\n\n");

            System.out.print("Sélectionner l'action souhaitée : ");
            numChoisi = sc.nextInt();

            /**
             * En fonction du choix de l'utilisateur, le switch propose l'option qui lui est relative
             */
            switch (numChoisi) {
                case 1:
                    if (listeB.getNbBiens() < 100) {
                        Bien bien = new Bien();
                        if (bien.saisirBien()) {
                            listeB.ajouterBien(bien);
                            listeB.sauvegarderListe();
                        }

                    } else {
                        System.out.println("Il y a trop de biens enregistrés !\n");
                    }
                    break;

                case 2:
                    if (listeB.getNbBiens() == 0) {
                        System.out.println ("Il n'y a aucun bien enregistré.\n");
                        return;
                    }

                    modifierBien(listeB);
                    listeB.sauvegarderListe();
                    break;

                case 3:
                    if (listeB.getNbBiens() == 0) {
                        System.out.println("Il n'y a aucun bien enregistré.\n");
                        break;
                    }
                    listeB.afficherListeSimplifiee();
                    System.out.print("Entrez le numéro identifiant du bien à supprimer : ");
                    id = sc.nextInt();
                    listeB.supprimerBien(id);
                    listeB.sauvegarderListe();
                    break;

                case 4:
                    if (listeB.getNbBiens() == 0) {
                        System.out.println("Il n'y a aucun bien enregistré.\n");
                        break;
                    } else {
                        listeB.afficherListeDesBiens();
                    }
                    break;
                
                case 5:
                	listeB.afficherListeBiensAlpha(listeB.trierListe());
                	break;
                	
                case 6:
                	break;
                	
                default:
                	System.out.println("Commande incorrecte !\n");
                	
            }
        } while(numChoisi != 6);

    }

    /**
     * Permet de modifier un bien
     * @param listeB la liste des biens
     * @throws IOException
     */
    void modifierBien (ListeBiens listeB) throws IOException {

        int oldId;
        int choixMenuModif;
        String newType;
        int newNumRue;
        String newRue;
        int newCp;
        String newVille;

        System.out.print("Entrez le numéro identifiant du bien à modifier : ");
        oldId = sc.nextInt();

        do {
        	// Le sous-menu permettant la modification d'un bien
            System.out.print ("\n\n______MODIFIER UN BIEN_____\n" +
                "[1] Modifier le type\n" +
                "[2] Modifier le numéro de rue\n" +
                "[3] Modifier le nom de la rue\n" +
                "[4] Modifier le code postal\n" +
                "[5] Modifier le nom de la ville\n" +
                "[6] Modifications terminées\n\n" +
                "Entrez le numéro de l'action souhaitée : ");
            choixMenuModif = sc.nextInt();

            switch(choixMenuModif) {
                case 1:
                    System.out.print("Entrez le nouveau type de bien souhaité : ");
                    sc.nextLine();
                    newType = sc.nextLine();
                    listeB.modifierType(oldId, newType);
                    listeB.sauvegarderListe();
                    break;

                case 2:
                    System.out.print("Entrez le nouveau numéro de rue souhaitée: ");
                    sc.nextLine();
                    newNumRue = sc.nextInt();
                    listeB.modifierNumRue(oldId, newNumRue);
                    listeB.sauvegarderListe();
                    break;

                case 3:
                    System.out.print("Entrez le nouveau nom de rue souhaité : ");
                    sc.nextLine();
                    newRue = sc.nextLine();
                    listeB.modifierRue(oldId, newRue);
                    listeB.sauvegarderListe();
                    break;

                case 4:
                    System.out.print("Entrez le nouveau code postal souhaité : ");
                    sc.nextLine();
                    newCp = sc.nextInt();
                    listeB.modifierCp(oldId, newCp);
                    listeB.sauvegarderListe();
                    break;

                case 5:
                    System.out.print("Entrez le nouveau nom de ville : ");
                    sc.nextLine();
                    newVille = sc.nextLine();
                    listeB.modifierVille(oldId, newVille);
                    listeB.sauvegarderListe();
                    break;
            }
        } while(choixMenuModif != 6);

    }

}
