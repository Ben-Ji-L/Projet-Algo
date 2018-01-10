import java.io.IOException;
import java.util.Scanner;

/**
 * Classe qui gère toutes les actions relatives aux types de biens
 */
public class MenuTypesDeBiens {
    Scanner sc = new Scanner (System.in);
    int choixMenuTypes;
    int identASupprimer;
    int identAModifier;
    String newNom;

    //Affiche le menu de gestion des types de biens
    void afficherMenu () throws IOException {

        ListeTypesDeBiens liste = new ListeTypesDeBiens();

        //On répète l'affichage du menu après chaque action, tant que l'utilisateur ne demande pas à en sortir
        do {
            System.out.print("\n________MENU DES TYPES DE BIENS________\n\n" +
                    "[1] Ajouter un nouveau type de bien\n" +
                    "[2] Modifier un type de bien\n" +
                    "[3] Supprimer un type de bien\n" +
                    "[4] Afficher la liste des types de biens\n" +
                    "[5] Sortir du menu\n\n");

            System.out.print("Entrez le numéro de l'action souhaitée : ");
            choixMenuTypes = sc.nextInt();

            switch (choixMenuTypes) {

                //Ajoute un nouveau type de bien à la liste des types de biens
                case 1:
                    if (liste.getNbTypes() < 20) {
                        TypeDeBien type = new TypeDeBien();
                        type.saisirTypeDeBien();
                        liste.ajouterTypeDeBien(type);
                        liste.sauvegarderListe();
                    } else {
                        System.out.print("Vous ne pouvez plus ajouter de type de bien, le nombre maximal a déjà été atteint.");
                    }
                    break;

                //Modifie un type de bien
                case 2:
                    liste.afficherListeDesTypesDeBien();
                    System.out.print("Entrez l'identifiant du type à modifier : ");
                    identAModifier = sc.nextInt();
                    System.out.print("Saisissez le nouveau nom souhaité pour le type : ");
                    newNom = sc.nextLine();
                    liste.modifierNom(identAModifier, newNom);
                    liste.sauvegarderListe();
                    break;

                //Supprime un type de bien
                case 3:
                    if (liste.getNbTypes() == 0) {
                        System.out.print("Il n'y a pas encore de type de bien enregistré.");
                        return;
                    }

                    liste.afficherListeDesTypesDeBien();
                    System.out.print("Saisissez l'identifiant du type de bien à supprimer : ");
                    identASupprimer = sc.nextInt();
                    liste.supprimerTypeDeBien(identASupprimer);
                    liste.sauvegarderListe();
                    break;

                //Affiche la liste des types de biens
                case 4:
                    liste.afficherListeDesTypesDeBien();
                    break;

                //Permet de sortir du menu de gestion des types de biens
                case 5:
                    break;

                //Instruction par défaut, au cas où l'utilisateur taperait un chiffre non compris dans les options
                default:
                    System.out.print("Commande incorrecte.");
            }
        } while (choixMenuTypes != 5);

    }
}
