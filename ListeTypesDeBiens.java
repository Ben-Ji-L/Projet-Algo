import java.io.*;

/**
 * Classe qui référence tous les types de biens entrés dans l'application
 */
public class ListeTypesDeBiens {
    private TypeDeBien [] tableauDeTypesDeBiens;
    private int nbTypes;
    private int prochainIdent;

    /**
     * Constructeur d'un objet ListeTypesDeBiens
     */
    ListeTypesDeBiens () throws IOException {

        tableauDeTypesDeBiens = new TypeDeBien[20];
        nbTypes = 0;
        prochainIdent = 1;
        chargerListe();
    }

    /**
     * Ajoute un nouveau type à la liste
     * @param type Nouveau type à ajouter
     */
    void ajouterTypeDeBien (TypeDeBien type) {
        int ident;

        ident = genererIdentifiant();
        type.setIdentifiant(ident);
        tableauDeTypesDeBiens [nbTypes] = type;
        nbTypes ++;

    }

    /**
     * Supprime un type de la liste
     * @param ident Identifiant du type à supprimer
     */
    void supprimerTypeDeBien (int ident) {
        //TODO : vérifier que le type de bien n'est plus utilisé

        boolean trouve = false;

        /*
        Recherche de l'identifiant donné en paramètre dans les n-1 cases du tableau contenant les types
         */
        for (int i=0; i<nbTypes-1; i++) {
            if (tableauDeTypesDeBiens[i].getIdentifiant()==ident){
                trouve=true;
            }

            if (trouve) {
                tableauDeTypesDeBiens[i]=tableauDeTypesDeBiens[i+1];
            }
        }

        /*
        Recherche de l'identifiant donné en paramètre dans la dernière case
         */
        if (!trouve) {
            if (tableauDeTypesDeBiens[nbTypes-1].getIdentifiant()==ident) {
                trouve = true;
            }
        }

        /*
        Une fois l'identifiant localisé et les types décalés, met la dernière casse à "null" et cesse de la prendre en compte
         */
        if (trouve) {
            tableauDeTypesDeBiens[nbTypes-1] = null;
            nbTypes --;
            System.out.println ("Type supprimé.\n");
        }
        else {
            System.out.println ("Identifiant inconnu.\n");
        }
    }

    /**
     * Modifie le nom d'un type
     * @param ident Identifiant du type à modifier
     * @param newNom Le nouveau nom
     */
    void modifierNom (int ident, String newNom) {

        for (int i=0; i<nbTypes; i++) {

            if (tableauDeTypesDeBiens[i].getIdentifiant()==ident) {
                tableauDeTypesDeBiens[i].setNom(newNom);
                return;
            }
        }
    }

    /**
     * Affiche la liste de tous les types enregistrés (identifiant, nom)
     */
    void afficherListeDesTypesDeBien () {

        for (int i=0; i<nbTypes; i++) {
            System.out.print (tableauDeTypesDeBiens[i].toString());
        }
    }

    String trouverTypeparIdent (int ident) {
        String nomType = "";

        for (int i=0; i<nbTypes; i++) {
            if (tableauDeTypesDeBiens [i].getIdentifiant()==ident) {
                nomType = tableauDeTypesDeBiens [i].getNom ();
            }
        }
        return nomType;
    }

    /**
     * Sauvegarde la liste des types
     * @throws IOException
     */
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

    /**
     * Charge la liste des types
     * @throws IOException
     */
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

    /**
     * Génère un identifiant unique
     * @return un identifiant unique
     */
    int genererIdentifiant () {
        int identifiant;

        identifiant = prochainIdent;
        prochainIdent ++;
        return identifiant;
    }

    /**
     * Permet de recupérer le nombre total de types enregistrés
     * @return le nombre total de types
     */
    public int getNbTypes() {
        return nbTypes;
    }
}
