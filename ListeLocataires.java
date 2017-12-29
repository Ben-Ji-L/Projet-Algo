import java.io.*;
import java.util.Scanner;

public class ListeLocataires {

    private Locataire [] tableauDeLocataires;
    private int nbLocataires;
    private int prochainIdent;

    public int getNbLocataires() {
        return nbLocataires;
    }

    ListeLocataires () {

        tableauDeLocataires = new Locataire [500];
        nbLocataires = 0;
        prochainIdent = 1;

    }

    void ajouterLocataire (Locataire loc) {
        int ident;

        ident = genererIdentifiant();
        loc.setIdentifiant(ident);
        tableauDeLocataires [nbLocataires] = loc;
        nbLocataires ++;

    }

    void supprimerLocataire (int ident) {

        boolean trouve = false;

        if (nbLocataires==0){
            return;
        }
        for (int i=0; i<nbLocataires-1; i++) {
            if (tableauDeLocataires[i].getIdentifiant() == ident) {
                if (tableauDeLocataires[i].getNbBiensLoues()!=0) {
                    System.out.println ("Impossible de supprimer ce locataire car il a toujours des biens en location.\n");
                    return;
                }
                trouve = true;
            }
            if (trouve) {
                tableauDeLocataires[i]=tableauDeLocataires[i+1];
            }
        }
        if (!trouve) {
            if (tableauDeLocataires[nbLocataires-1].getIdentifiant()==ident) {
                if (tableauDeLocataires[nbLocataires-1].getNbBiensLoues()!=0) {
                    System.out.println ("Impossible de supprimer ce locataire car il a toujours des biens en location.\n");
                    return;
                }
                trouve = true;
            }
        }

        if (trouve) {
            tableauDeLocataires[nbLocataires-1] = null;
            nbLocataires --;
            System.out.println ("Locataire supprimé.\n");
        }
    }

    void afficherListeDesLocataires () {
        String resultat = "";

        for (int i=0; i<nbLocataires; i++) {

            resultat += tableauDeLocataires [i].toString() + "\n";
        }
        System.out.println (resultat);
    }

    void afficherListeSimplifiee (){
        String resultat = "";

        for (int i=0; i<nbLocataires; i++) {

            resultat += tableauDeLocataires[i].affichageSimplifieLocataire() + "\n";
        }
        System.out.println(resultat);
    }

    int genererIdentifiant () {
        int  identifiant;

        identifiant = prochainIdent;
        prochainIdent++;
        return identifiant;
    }

    void sauvegarderListe () throws IOException {
        String fichier = "Locataires";
        DataOutputStream dos;
        dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichier)));

        dos.writeInt(prochainIdent);
        dos.writeInt(nbLocataires);
        for (int i=0; i<nbLocataires; i++){
            Locataire loc = tableauDeLocataires [i];
            loc.sauvegarderLocataire(dos);
        }
        dos.close();
    }

    void chargerListe () throws IOException {
        String fichier = "Locataires";

        File file = new File(fichier);
        if (!file.exists()) {
            return;
        }

        DataInputStream dis;
        dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fichier)));

        prochainIdent = dis.readInt();
        nbLocataires = dis.readInt();
        for (int i=0; i<nbLocataires; i++) {
            Locataire loc = new Locataire();
            loc.chargerLocataire(dis);
            tableauDeLocataires[i]=loc;
        }
        dis.close();
    }
}
