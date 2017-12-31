import java.io.IOException;
import java.util.Scanner;

public class MenuLocataires {
    Scanner sc = new Scanner (System.in);

    void afficherMenu () throws IOException {

        int choixMenuGeneral;
        int identASupprimer;
        ListeLocataires liste = new ListeLocataires();

        liste.chargerListe();

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
            choixMenuGeneral = sc.nextInt();

            switch (choixMenuGeneral) {

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

                case 2:
                    if (liste.getNbLocataires()==0) {
                        System.out.println ("Il n'y a aucun locataire enregistré.\n");
                        return;
                    }

                    menuModifier(liste);
                    liste.sauvegarderListe();
                    break;

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


                case 4:
                    liste.afficherListeDesLocataires();
                    break;
            }
        } while (choixMenuGeneral!=7);
    }

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

                case 1:
                    System.out.print("Entrez le nouveau nom souhaité : ");
                    sc.nextLine();
                    newNom = sc.nextLine();
                    liste.modifierNom(identAModifier, newNom);
                    liste.sauvegarderListe();
                    break;

                case 2:
                    System.out.print("Entrez la nouvelle adresse souhaitée: ");
                    sc.nextLine();
                    newAdresse = sc.nextLine();
                    liste.modifierAdresse(identAModifier, newAdresse);
                    liste.sauvegarderListe();
                    break;

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
