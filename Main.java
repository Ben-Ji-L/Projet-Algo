import java.io.IOException;

public class Main {

    public static void main (String [] argument) throws IOException {

        Locataire loc = new Locataire();
        MenuLocataires menu1 = new MenuLocataires();

        Bien bien = new Bien();
        MenuBiens menu2 = new MenuBiens();

        menu1.afficherMenu();
        menu2.afficherMenu();
    }
}
