import java.io.*;
import java.util.Arrays;

/**
 * Cette classe représente la liste des biens immobiliers.
 */
public class ListeBiens {

    private Bien[] tabBiens;
    private int  nbBiens;
    private int nextId;

    /**
     * Construit un bien
     * @throws IOException
     */
    ListeBiens () throws  IOException {

        // Le tableau qui contiendra les biens
        tabBiens = new Bien[100];
        nbBiens = 0;
        nextId = 1;
        chargerListe();
    }
    
    /**
     * Affiche la liste des biens
     */
    void afficherListeDesBiens () {
        String resultat = "";

        for (int i=0; i<nbBiens; i++) {

            resultat += tabBiens[i].toString() + "\n";
        }
        System.out.println (resultat);
    }

    /**
     * Affiche les biens de maniére plus simple
     */
    void afficherListeSimplifiee (){
        String resultat = "";

        for (int i=0; i<nbBiens; i++) {

            resultat += tabBiens[i].affichageSimplifieBien() + "\n";
        }
        System.out.println(resultat);
    }

    /**
     * Permetd'ajouter un bien à la liste.
     * @param bien le bien à ajouter
     */
    void ajouterBien(Bien bien) {

        int id;

        id = genererId();
        bien.setId(id);
        tabBiens [nbBiens] = bien;
        nbBiens++;
    }

    /**
     * Permet de supprimer un bien de la liste.
     * @param id l'identifiant du bien à supprimer
     */
    void supprimerBien (int id) {
        boolean trouve = false;

        for (int i = 0; i < nbBiens - 1; i++) {
            if (tabBiens[i].getId() == id) {
                trouve = true;
                if (tabBiens[i].getIdLoc() != -1) {
                    System.out.print ("Ce bien est toujours loué. Suppression impossible.\n");
                    return;
                }
            }
        }
        if (!trouve) {
            if (tabBiens[nbBiens-1].getId() == id) {
                trouve = true;
                if (tabBiens[nbBiens-1].getIdLoc() != -1) {
                    System.out.print ("Ce bien est toujours loué. Suppression impossible.\n");
                    return;
                }
            } else {
                System.out.println("Bien non trouvé.\n");
            }
        }

        if (trouve) {
            tabBiens[nbBiens-1] = null;
            nbBiens --;
            System.out.println ("Bien supprimé.\n");
        }
    }

    /**
     * Permet de modifier le type d'un bien
     * @param id l'identifiant du bien
     * @param newType le nouveau type du bien
     */
    void modifierType(int id, String newType) {

        for (int i = 0; i < nbBiens; i++) {
            if (tabBiens[i].getId() == id) {
                tabBiens[i].setType(newType);
                return;
            }
        }
    }

    /**
     * Permet de modifier le numero de rue d'un bien
     * @param id l'identifiant du bien
     * @param newNumRue le nouveau numero de rue
     */
    void modifierNumRue(int id, int newNumRue) {

        for (int i = 0; i < nbBiens; i++) {
            if (tabBiens[i].getId() == id) {
                tabBiens[i].setNumRue(newNumRue);
                return;
            }
        }
    }

    /**
     * Permet de modifier le nom de la rue d'un bien
     * @param id l'identifiant du bien
     * @param newRue le nouveau nom de rue
     */
    void modifierRue(int id, String newRue) {

        for (int i = 0; i < nbBiens; i++) {
            if (tabBiens[i].getId() == id) {
                tabBiens[i].setRue(newRue);
                return;
            }
        }
    }

    /**
     * Permet de modifier le code postal d'un bien
     * @param id l'identifiant du bien
     * @param newCp le nouveau code postal du bien
     */
    void modifierCp(int id, int newCp) {

        for (int i = 0; i < nbBiens; i++) {
            if (tabBiens[i].getId() == id) {
                tabBiens[i].setCp(newCp);
                return;
            }
        }
    }

    /**
     * Permet de modifier la ville du bien
     * @param id l'identifiant du bien
     * @param newVille le nouveau nom de la ville
     */
    void modifierVille(int id, String newVille) {

        for (int i = 0; i < nbBiens; i++) {
            if (tabBiens[i].getId() == id) {
                tabBiens[i].setVille(newVille);
                return;
            }
        }
    }

    /**
     * Méthode permettant de générer un identifiant
     * @return id l'identifiant crée
     */
    int genererId() {
        int id;

        id = nextId;
        nextId++;
        return id;
    }

    /**
     * Sauvegarde la liste dans un fichier binaire
     * @throws IOException
     */
    void sauvegarderListe () throws IOException {
        String fichier = "Biens.bin";
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

    /**
     * Charge une liste de biens depuis le fichier binaire
     * @throws IOException
     */
    void chargerListe () throws IOException {
        String fichier = "Biens.bin";

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

    /**
     * Affiche  un bien selon l'identifiant passé en paramétre.
     * @param id l'identifiant
     */
    public void afficherBienParId(int id) {
        for (int i=0; i< nbBiens; i++) {
            if (tabBiens[i].getId() == id) {
                System.out.print(tabBiens[i].toString());
            }
        }
    }

    /**
     * Renvoie tout les biens d'un meme type.
     * @param idType l'identifiant du type de bien
     * @return un tableau contenant les biens du type passé en paramétre
     */
    public int[] toutLesBiensPourUnType (int idType) {

        // on crée un tableau de 100 int car c'estla taille max
        int[] tabIdBiens = new int[100];
        int compteurBiensTrouve = 0;


        for (int i=0; i< nbBiens; i++) {
            if (tabBiens[i].getIdType() == idType) {
                tabIdBiens[compteurBiensTrouve] = tabBiens[i].getId();
                compteurBiensTrouve++;
            }
        }

        int[] tabResultat = new int[compteurBiensTrouve];
        for (int j = 0; j < tabResultat.length; j++) {
            tabResultat[j] = tabIdBiens[j];
        }
        return tabResultat;
    }
    
    /**
     * Affiche la liste des biens loués
     */
    void afficherLaListeDesBiensLoues() {

        for (int i=0; i<nbBiens; i++) {
            if (tabBiens[i].getIdLoc() != -1) {
                System.out.print (tabBiens[i].affichageSimplifieBien());
            }
        }
    }
    
    /**
     * Permet de louer un bien
     * @param idBien l'identifiant du bien
     * @param idLocataire l'identifiant du locataire
     */
    void louer (int idBien, int idLocataire) {
    	
    	for (int i=0; i < nbBiens; i++) {
            if (tabBiens[i].getId() == idBien) {
                tabBiens[i].setIdLoc(idLocataire);
            }
    	}
    }
    
    /**
     * Permet de libérer un bien
     * @param idBien l'identifiant du bien
     */
    void liberer(int idBien) {
    	for (int i=0; i < nbBiens; i++) {
            if (tabBiens[i].getId() == idBien) {
                tabBiens[i].setIdLoc(-1);
            }
        }
    }
    
    /**
     * Permet de trier la liste des biens
     * @return le tableau trié
     */
    public Bien[] trierListe() {
    	Bien [] aTrier = new Bien [nbBiens];
    	
    	for (int i = 0; i < nbBiens; i++) {
    		aTrier[i] = tabBiens[i];
    	}
    	Arrays.sort(aTrier);
    	return aTrier;
    }
    
    /**
     * Affiche la liste des biens par ordre alphabétique.
     * @param tabTrie le tableau trié par ordre alphabétique
     */
    public void afficherListeBiensAlpha(Bien [] tabTrie) {
    	
    	String resultat = "";

        for (int i = 0; i < nbBiens; i++) {

            resultat += tabTrie[i].affichageSimplifieBien() + "\n";
        }
        System.out.println(resultat);
    }
    
    /**
     * Permet de récuppérer le nombre de biens.
     * @return le nombre de biens
     */
    public int getNbBiens() {
        return nbBiens;
    }
    
    /**
     * Permet de récuperer l'identifiant du type du bien
     * @param identBien l'identifiant du bien
     * @return l'identifiant du type ou -1
     */
    public int getIDTypeDeBien (int identBien) {

        for (int i=0; i<nbBiens; i++) {
            if (tabBiens[i].getId() == identBien) {
                return tabBiens[i].getIdType();
            }
        }
        return -1;
    }

    public int getIdLocParIdentBien (int identBien) {
        for (int i=0; i<nbBiens; i++) {
            if (tabBiens [i].getId() == identBien) {
                return tabBiens [i].getIdLoc();
            }
        }
        return -1;
    }
}
