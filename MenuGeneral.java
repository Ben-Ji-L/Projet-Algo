/**
 *Classe qui rassemble tous les menus intermédiaires. Seule classe appellée par le "main".
 */
import java.io.IOException;
import java.util.Scanner;

class MenuGeneral {
    Scanner sc = new Scanner(System.in);
    int choixMenuGeneral;

    void afficherMenu () throws IOException {

        System.out.print ("...BIENVENUE SUR LE PROGRAMME DE GESTION DE LOCATIONS DE L'ASSOCIATION LOSC...\n");

         do {
             System.out.print("\n__________MENU GÉNÉRAL__________\n\n" +
                     "[1] Gestion des locataires\n" +
                     "[2] Gestion des biens \n" +
                     "[3] Gestion des types de biens\n" +
                     "[4] Gestion des locations\n" +
                     "[5] Quitter le programme\n\n");

             System.out.print("Entrez le numéro de l'action que vous souhaitez effectuer : ");
             choixMenuGeneral = sc.nextInt();

             switch (choixMenuGeneral) {

                 case 1:
                     MenuLocataires menuLoc = new MenuLocataires();
                     menuLoc.afficherMenu();
                     break;

                 case 2:
                     MenuBiens menuBiens = new MenuBiens();
                     menuBiens.afficherMenu();
                     break;

                 case 3:
                     MenuTypesDeBiens menuTypes = new MenuTypesDeBiens();
                     menuTypes.afficherMenu();
                     break;

                 case 4:
                     MenuLocations menuLocations = new MenuLocations();
                     menuLocations.afficherMenu();
                     break;

                 case 5:
                     break;

                 default:
                     System.out.print("Commande incorrecte.");

             }

         } while (choixMenuGeneral != 5);
    }
}