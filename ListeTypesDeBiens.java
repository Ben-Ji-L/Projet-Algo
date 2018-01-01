import java.io.*;

/**
 * Classe qui référence tous les types de biens entrés dans l'application
 */
public class ListeTypesDeBiens {
    private TypeDeBien [] tableauDeTypesDeBiens;
    private int nbTypes;
    private int prochainIdent;

    ListeTypesDeBiens () {

        tableauDeTypesDeBiens = new TypeDeBien[20];
        nbTypes = 0;
        prochainIdent = 1;
    }

    void ajouterTypeDeBien (TypeDeBien type) {
        int ident;

        ident = genererIdentifiant();
        type.setIdentifiant(ident);
        tableauDeTypesDeBiens [nbTypes] = type;
        nbTypes ++;

    }

    void supprimerTypeDeBien (int ident) {
        //TODO : vérifier que le type de bien n'est plus utilisé

        boolean trouve = false;

        for (int i=0; i<nbTypes-1; i++) {
            if (tableauDeTypesDeBiens[i].getIdentifiant()==ident){
                trouve=true;
            }

            if (trouve) {
                tableauDeTypesDeBiens[i]=tableauDeTypesDeBiens[i+1];
            }
        }

        if (!trouve) {
            if (tableauDeTypesDeBiens[nbTypes-1].getIdentifiant()==ident) {
                trouve = true;
            }
        }

        if (trouve) {
            tableauDeTypesDeBiens[nbTypes-1] = null;
            nbTypes --;
            System.out.println ("Type supprimé.\n");
        }
        else {
            System.out.println ("Identifiant inconnu.\n");
        }
    }

    void modifierNom (int ident, String newNom) {

        for (int i=0; i<nbTypes; i++) {

            if (tableauDeTypesDeBiens[i].getIdentifiant()==ident) {
                tableauDeTypesDeBiens[i].setNom(newNom);
                return;
            }
        }
    }

    void afficherListeDesTypesDeBien () {

        for (int i=0; i<nbTypes; i++) {
            System.out.print (tableauDeTypesDeBiens[i].toString());
        }
    }

    void sauvegarderListe () throws IOException {
        String fichier = "TypesDeBiens.bin";
        DataOutputStream dos;
        dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichier)));

        dos.writeInt(prochainIdent);
        dos.writeInt(nbTypes);
        for (int i=0; i<nbTypes; i++){
            TypeDeBien type = tableauDeTypesDeBiens [i];
            type.sauvegarderTypeDeBien(dos);
        }
        dos.close();
    }

    void chargerListe () throws IOException {
        String fichier = "TypesDeBiens.bin";

        File file = new File(fichier);
        if (!file.exists()) {
            return;
        }

        DataInputStream dis;
        dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fichier)));

        prochainIdent = dis.readInt();
        nbTypes = dis.readInt();
        for (int i=0; i<nbTypes; i++) {
            TypeDeBien type = new TypeDeBien();
            type.chargerTypeDeBien(dis);
            tableauDeTypesDeBiens[i]=type;
        }
        dis.close();
    }

    int genererIdentifiant () {
        int identifiant;

        identifiant = prochainIdent;
        prochainIdent ++;
        return identifiant;
    }

    public int getNbTypes() {
        return nbTypes;
    }
}
