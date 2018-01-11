import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/*
    Cette classe représente les biens immobiliers

    int id l'identifiant du bien.
    String type le type du bien (studio, appartement...)
    int numRue le numero de la rue
    String rue le nom de la rue
    int cp le code postal
    String ville le nom de la ville
    int idLoc l'identifiant du locataire du bien
    int idType l'identifiant du type du bien
*/
class Bien implements Comparable<Bien>{

    private int id;
    private String type;
    private int numRue;
    private String rue;
    private int cp;
    private String ville;
    private int idLoc;
    private int idType;

    /**
     * Construit un objet Bien
     */
    Bien () {
        type = "";
        numRue = -1;
        rue = "";
        cp = -1;
        ville = "";
        idLoc = -1;
        idType = -1;
    }

    /**
     * Permet de saisir un nouveau bien.
     * @throws IOException
     */
    void saisirBien () throws IOException {
        Scanner sc = new Scanner (System.in);

        ListeTypesDeBiens listeB = new ListeTypesDeBiens();
        
        listeB.afficherListeDesTypesDeBien();
    	System.out.print ("Entrez l'identifiant du type de bien : ");
    	idType = sc.nextInt();
    	
        if (idType != -1) {
        	
        	System.out.print ("Entrez le numero de rue du bien : ");
            numRue = sc.nextInt();
            sc.nextLine();

            System.out.print ("Entrez la rue du bien : ");
            rue = sc.nextLine();

            System.out.print ("Entrez le code postal du bien : ");
            cp = sc.nextInt();
            sc.nextLine();

            System.out.print ("Entrez la ville du bien : ");
            ville = sc.nextLine();

            type = listeB.trouverTypeparIdent(idType);
            
        } else {
        	System.out.println("Il n'y a aucun type de biens enregistré.");
        } 
    }

    /**
     * Affiche les attributs du bien sous forme de chaine de caractéres
     */
    public String toString () {

    	String resultat = "";
        resultat = "\nType : " + type + "\n"
                + "Numero de rue : " + numRue + "\n"
                + "Rue : " + rue + "\n"
                + "Code postal : " + cp + "\n"
                + "Ville : " + ville + "\n";
        
        if (idLoc != -1) {
        	resultat += "Loué\n";
        } else {
        	resultat += "Non loué\n";
        }
        return resultat;        
    }

    /**
     * Permet un affichage sur une ligne d'un bien.
     * @return la chaine contenant les attributs du bien
     */
    String affichageSimplifieBien () {

        return "\n" + id + " : " + type + " " + numRue + " " + rue + " " + cp + " " + ville + " ";
    }

    /**
     * Permet de sauvegarder un bien dans un fichier binaire.
     * @param dos le stream dans lequel on écrit les données
     * @throws IOException exception levée en cas d'erreur de saisie
     */
    void sauvegarderBien(DataOutputStream dos) throws IOException {
        dos.writeInt(id);
        dos.writeUTF(type);
        dos.writeInt(numRue);
        dos.writeUTF(rue);
        dos.writeInt(cp);
        dos.writeUTF(ville);
        dos.writeInt(idLoc);
        dos.writeInt(idType);
    }

    /**
     * Permet de charger un bien à partir du fichier binaire Biens.bin
     * @param dis le stream duquel on lit les données
     * @throws IOException exception levée en cas d'erreur de saisie
     */
    void chargerBien(DataInputStream dis) throws IOException {
        id = dis.readInt();
        type = dis.readUTF();
        numRue = dis.readInt();
        rue = dis.readUTF();
        cp = dis.readInt();
        ville = dis.readUTF();
        idLoc = dis.readInt();
        idType = dis.readInt();
    }
    
    /**
     * Permet de faire une comparaison sur les attributs d'un bien, notamment utile pour l'affichage par ordre alphabétique.
     */
    @Override
    public int compareTo(Bien autreBien) {
    	String nomBien = type + "-" + numRue + "-" + rue + "-" + cp + "-" + ville;
    	String nomAutreBien = autreBien.type + "-" + autreBien.numRue + "-" + autreBien.rue + "-" + autreBien.cp + "-" + autreBien.ville;

    	return nomBien.compareTo(nomAutreBien);
    }

    /**
     * Permet de modifier la valeur du type de bien
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Permet de modifier le numéro de rue du bien
     * @param numRue
     */
    public void setNumRue(int numRue) {
        this.numRue = numRue;
    }

    /**
     * Permet de modifier la rue du bien
     * @param rue
     */
    public void setRue(String rue) {
        this.rue = rue;
    }

    /**
     * Permet de modifier le code postal du bien
     * @param cp
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * Permet de modifier la ville du bien
     * @param ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Permet de récupérer l'identifiant du locataire du bien
     * @return
     */
    public int getIdLoc() {
        return idLoc;
    }

    /**
     * Permet de modifier l'identifiant du locataire du bien
     * @param idLoc
     */
    public void setIdLoc(int idLoc) {
        this.idLoc = idLoc;
    }

    /**
     * Permet de récupérer l'identifiant du type du bien
     * @return 
     */
    public int getIdType () {
        return idType;
    }
    
    /**
     * Permet de modifier l'identifiant du bien
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Permet de récupérer l'identifiant du bien
     * @return
     */
    public int getId() {
        return id;
    }
}
