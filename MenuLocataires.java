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
        ListeLocataires listeLocataires = new ListeLocataires();
        ListeTypesDeBiens listeTypesDeBiens = new ListeTypesDeBiens();

        do {
            System.out.print("\n__________MENU DES LOCATAIRES__________\n\n" +
                    "[1] Ajouter un locataire\n" +
                    "[2] Modifier un locataire\n" +
                    "[3] Supprimer un locataire\n" +
                    "[4] Afficher la liste des locataires par ordre d'identifiant\n" +
                    "[5] Afficher la liste des locataires par ordre alphabétique\n" +
                    "[6] Afficher la liste des locataires par type de bien\n" +
                    "[7] Rechercher la liste des locations d'un locataire\n" +
                    "[8] Sortir du menu\n\n");

            System.out.print("Entrez le numéro de l'action souhaitée : ");
            choixMenuLoc = sc.nextInt();

            switch (choixMenuLoc) {

                /*
                Ajoute un nouveau locataire à la listeLocataires des locataires
                 */
                case 1:
                    if (listeLocataires.getNbLocataires()<500) {
                        Locataire loc = new Locataire();
                        loc.saisirLocataire();
                        listeLocataires.ajouterLocataire(loc);
                        listeLocataires.sauvegarderListe();
                    }
                    else {
                        System.out.println ("\nVous ne pouvez plus ajouter de locataire, le nombre maximal a déjà été atteint.\n");
                    }
                    break;

                /*
                Modifie un locataire préalablement enregistré
                 */
                case 2:
                    if (listeLocataires.getNbLocataires()==0) {
                        System.out.println ("Il n'y a aucun locataire enregistré.\n");
                        return;
                    }

                    menuModifier(listeLocataires);
                    listeLocataires.sauvegarderListe();
                    break;

                /*
                Supprime un locataire de la listeLocataires de locataires sauveragdée
                 */
                case 3:
                    if (listeLocataires.getNbLocataires()==0) {
                        System.out.println ("Il n'y a aucun locataire enregistré.\n");
                        return;
                    }
                    listeLocataires.afficherListeSimplifiee();
                    System.out.print ("Entrez le numéro identifiant du locataire à supprimer : ");
                    identASupprimer = sc.nextInt();
                    listeLocataires.supprimerLocataire(identASupprimer);
                    listeLocataires.sauvegarderListe();
                    break;


                /*
                Affiche une listeLocataires complète de tous les locataires les uns après les autres
                 */
                case 4:
                    System.out.print ("\n\n");
                    listeLocataires.afficherListeDesLocataires();
                    break;
                    
                case 5:
                    System.out.print ("\n\n");
                    listeLocataires.afficherListeLocAlpha(listeLocataires.trierListe());
                	break;

                /*
                Affiche la listeLocataires des locataires par type de biens
                 */
                case 6:
                    listeTypesDeBiens.afficherListeDesTypesDeBien();
                    System.out.print ("\nEntrez l'identifiant du type de bien dont vous souhaitez connaître les locataires : ");
                    identPourType = sc.nextInt();

                    if (listeLocataires.compterTousLesLocatairesPourUnTypeDeBien(identPourType) == 0) {
                        System.out.print("Il n'y a aucun locataire louant ce type de bien actuelement.\n");
                    }
                    else {
                        System.out.print("\nLes locataires louant un bien de ce type sont : \n");
                        listeLocataires.afficherTousLesLocatairesPourUnTypeDeBien(identPourType);
                    }
                    break;

                /*
                Recherche et affiche la listeLocataires des locations d'un locataire donné
                 */
                case 7:
                    if (listeLocataires.getNbLocataires()==0) {
                        System.out.println ("Il n'y a aucun locataire enregistré.\n");
                        return;
                    }
                    listeLocataires.afficherListeSimplifiee();
                    System.out.print ("Entrez le numéro identifiant du locataire dont vous souhaitez voir les locations : ");
                    identPourAffichage = sc.nextInt();
                    listeLocataires.afficherListeDesBiensPourUnLocataire(identPourAffichage);
                    break;

                /*
                Permet du sortir du menu des locataires
                 */
                case 8:
                    break;

                /*
                Gère les choix non proposés dans le menu
                 */
                default:
                    System.out.print("Commande incorrecte.\n");
            }
        } while (choixMenuLoc!=8);
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
                    System.out.print ("Le nom a été correctement modifié.\n");
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
                    System.out.print("L'adresse a été correctement modifié. \n");
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
                    System.out.print("Le numéro de téléphone a été correctement modifié.\n");
                    break;
            }
        } while (choixMenuModifier !=4);
    }
}
