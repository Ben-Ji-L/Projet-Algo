import java.io.IOException;
import java.util.Scanner;

/**
 * Classe gérant toutes les options diponnibles sur les locataires
 */
public class MenuLocataires {
    Scanner sc = new Scanner (System.in);

    /**
     * Affiche le menu des locataires
     * @throws IOException
     */
    void afficherMenu () throws IOException {

        int choixMenuLoc;
        int identASupprimer;
        int identPourAffichage;
        int identPourType;
        ListeLocataires liste = new ListeLocataires();
        ListeTypesDeBiens listeTypesDeBiens = new ListeTypesDeBiens();


        do {
            System.out.print("\n__________MENU DES LOCATAIRES__________\n\n" +
                    "[1] Ajouter un locataire\n" +
                    "[2] Modifier un locataire\n" +
                    "[3] Supprimer un locataire\n" +
                    "[4] Afficher la liste des locataires\n" +
                    "[5] Afficher la liste des locataires par type de bien\n" +
                    "[6] Rechercher la liste des locations d'un locataire\n" +
                    "[7] Sortir du menu\n\n");

            System.out.print("Entrez le numéro de l'action souhaitée : ");
            choixMenuLoc = sc.nextInt();

            switch (choixMenuLoc) {

                /*
                Ajoute un nouveau locataire à la liste des locataires
                 */
                case 1:
                    if (liste.getNbLocataires()<500) {
                        Locataire loc = new Locataire();
                        loc.saisirLocataire();
                        liste.ajouterLocataire(loc);
                        liste.sauvegarderListe();
                    }
                    else {
                        System.out.println ("Vous ne pouvez plus ajouter de locataire, le nombre maximal a déjà été atteint.");
                    }
                    break;

                /*
                Modifie un locataire préalablement enregistré
                 */
                case 2:
                    if (liste.getNbLocataires()==0) {
                        System.out.println ("Il n'y a aucun locataire enregistré.\n");
                        return;
                    }

                    menuModifier(liste);
                    liste.sauvegarderListe();
                    break;

                /*
                Supprime un locataire de la liste de locataires sauveragdée
                 */
                case 3:
                    if (liste.getNbLocataires()==0) {
                        System.out.println ("Il n'y a aucun locataire enregistré.\n");
                        return;
                    }
                    liste.afficherListeSimplifiee();
                    System.out.print ("Entrez le numéro identifiant du locataire à supprimer : ");
                    identASupprimer = sc.nextInt();
                    liste.supprimerLocataire(identASupprimer);
                    liste.sauvegarderListe();
                    break;


                /*
                Affiche une liste complète de tous les locataires les uns après les autres
                 */
                case 4:
                    liste.afficherListeDesLocataires();
                    break;

                /*
                Affiche la liste des locataires par type de biens
                 */
                case 5:
                    listeTypesDeBiens.afficherListeDesTypesDeBien();
                    System.out.print ("Entrez l'identifiant du type de bien dont vous souhaitez connaître les locataires : ");
                    identPourType = sc.nextInt();

                    break;

                /*
                Recherche et affiche la liste des locations d'un locataire donné
                 */
                case 6:
                    if (liste.getNbLocataires()==0) {
                        System.out.println ("Il n'y a aucun locataire enregistré.\n");
                        return;
                    }
                    liste.afficherListeSimplifiee();
                    System.out.print ("Entrez le numéro identifiant du locataire dont vous souhaitez voir les locations : ");
                    identPourAffichage = sc.nextInt();
                    liste.afficherListeDesBiensPourUnLocataire(identPourAffichage);
                    liste.sauvegarderListe();
                    break;

                /*
                permet du sortir du menu des locataires
                 */
                case 7:
                    break;

                /*
                Gère les choix non proposés dans le menu
                 */
                default:
                    System.out.print("Commande incorrecte.");
            }
        } while (choixMenuLoc!=7);
    }

    /**
     * Sous menu de l'option "modifier un locataire"
     * @param liste la liste de locataires chargée à l'appel du menu des locataires
     * @throws IOException
     */
    void menuModifier (ListeLocataires liste) throws IOException {
        int identAModifier;
        int choixMenuModifier;
        String newNom;
        String newAdresse;
        String newTelephone;

        liste.afficherListeSimplifiee();

        System.out.print ("Entrez le numéro identifiant du locataire à modifier : ");
        identAModifier = sc.nextInt();

        do {
            liste.afficherUnLocataire(identAModifier);

            System.out.print ("\n\n______MODIFIER UN LOCATAIRE_____\n" +
                "[1] Modifier le nom\n" +
                "[2] Modifier l'adresse\n" +
                "[3] Modifier le numéro de téléphone\n" +
                "[4] Modifications terminées\n\n" +
                "Entrez le numéro de l'action souhaitée : ");
            choixMenuModifier = sc.nextInt();

            switch (choixMenuModifier) {

                /*
                Permet de changer le nom d'un locataire
                 */
                case 1:
                    System.out.print("Entrez le nouveau nom souhaité : ");
                    sc.nextLine();
                    newNom = sc.nextLine();
                    liste.modifierNom(identAModifier, newNom);
                    liste.sauvegarderListe();
                    break;

                /*
                Permet de changer l'adresse d'un locataire
                 */
                case 2:
                    System.out.print("Entrez la nouvelle adresse souhaitée: ");
                    sc.nextLine();
                    newAdresse = sc.nextLine();
                    liste.modifierAdresse(identAModifier, newAdresse);
                    liste.sauvegarderListe();
                    break;

                /*
                Permet de changer le numéro de téléphone d'un locataire
                 */
                    case 3:
                    System.out.print("Entrez le nouveau numéro de téléphone souhaité : ");
                    sc.nextLine();
                    newTelephone = sc.nextLine();
                    liste.modifierTelephone(identAModifier, newTelephone);
                    liste.sauvegarderListe();
                    break;
            }
        } while (choixMenuModifier !=4);
    }
}
