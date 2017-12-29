import java.io.IOException;

public class Main {

    public static void main (String [] argument) throws IOException {

        Locataire loc = new Locataire();
        MenuLocataires menu1 = new MenuLocataires();

        menu1.afficherMenu();
    }
}
