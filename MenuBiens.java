import java.util.Scanner;

public class MenuBiens {

    void afficherMeu () {   // la vache !!!

        Scanner sc = new Scanner(System.in);
        int numChoisi;

        do {
            System.out.print("\n__________MENU DES BIENS__________\n\n" +
                    "[1] Ajouter un bien\n" +
                    "[2] Modifier un bien\n" +
                    "[3] Supprimer un bien\n" +
                    "[4] Sortir du menu\n\n");

            System.out.print("Sélectionner l'action souhaitée : ");
            numChoisi = sc.nextInt();

            switch (numChoisi) {
                case 1:
                    break;
            }
        } while(numChoisi!=4);

    }

}
