import java.io.*;
import java.util.Scanner;

public class ListeBiens {

    private Bien[] tabBiens;
    private int  nbBiens;
    private int nextId;

    public int getNbBiens() {
        return nbBiens;
    }

    ListeBiens () {

        tabBiens = new Bien[500];
        nbBiens = 0;
        nextId = 1;
    }

    void ajouterBien(Bien bien) {

        int id;

        id = genererId();
        bien.setId(id);
        tabBiens [nbBiens] = bien;
        nbBiens++;
    }

    void supprimerBien (int id) {
        boolean trouve = false;

        if (nbBiens == 0){
            return;
        }
        for (int i = 0; i < nbBiens - 1; i++) {
            if (tabBiens[i].getId() == id) {
                trouve = true;
            }
        }
        if (!trouve) {
            if (tabBiens[nbBiens-1].getId() == id) {
                trouve = true;
            } else {
                System.out.println("Bien non trouvé.");
            }
        }

        if (trouve) {
            tabBiens[nbBiens-1] = null;
            nbBiens --;
            System.out.println ("Bien supprimé.\n");
        }
    }

    int genererId() {
        int id;

        id = nextId;
        nextId++;
        return id;
    }

    void sauvegarderListe () throws IOException {
        String fichier = "Biens";
        DataOutputStream dos;
        dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichier)));

        dos.writeInt(nextId);
        dos.writeInt(nbBiens);
        for (int i = 0; i < nbBiens; i++){
            Bien bien = tabBiens[i];
            bien.sauvegarderBien(dos);
        }
        dos.close();
    }

    void chargerListe () throws IOException {
        String fichier = "Biens";

        File file = new File(fichier);
        if (!file.exists()) {
            return;
        }

        DataInputStream dis;
        dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fichier)));

        nextId = dis.readInt();
        nbBiens = dis.readInt();
        for (int i = 0; i < nbBiens; i++) {
            Bien bien = new Bien();
            bien.chargerBien(dis);
            tabBiens[i] = bien;
        }
        dis.close();
    }
}
