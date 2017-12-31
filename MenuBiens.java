import java.util.Scanner;

public class MenuBiens {

    void afficherMenu () {

        Scanner sc = new Scanner(System.in);
        int numChoisi;
        ListeBiens listeB = new ListeBiens();

        listeB.chargerListe();

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
                    Bien bien = new Bien();
                    bien.saisirBien();
                    listeB.ajouterBien(bien);
                    listeB.sauvegarderListe();
                    break;

                case 3:
                    if (listeB.getNbBiens() == 0) {
                        System.out.println("Il n'y a aucun bien enregistré.\n");
                        return;
                    }
                    listeB.afficherListeSimplifiee();
                    System.out.println("Entrez le numéro identifiant du Bien à supprimer : ");
                    id = nextInt();
                    listeB.supprimerBien(id);
                    listeB.sauvegarderListe();
                    break;
            }
        } while(numChoisi!=4);

    }

}
