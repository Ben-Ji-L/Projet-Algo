import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/*
    Cette classe représente les biens immobiliers

    String type le type du bien (studio, appartement...)
    int numRue le numero de la rue
    String rue le nom de la rue
    int cp le code postal
    String ville le nom de la ville
*/
class Bien {

    private int id;
    private String type;
    private int numRue;
    private String rue;
    private int cp;
    private String ville;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    Bien () {
        type = "";
        numRue = -1;
        rue = "";
        cp = -1;
        ville = "";
    }

    void saisirBien () {
        Scanner sc = new Scanner (System.in);

        System.out.print ("Entrez le type du bien : ");
        type = sc.nextLine();

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
    }

    public String toString () {

        return "\nType : " + type + "\n"
                + "Numero de rue : " + numRue + "\n"
                + "Rue : " + rue + "\n"
                + "Code postal : " + cp + "\n"
                + "Ville : " + ville;
    }

    String affichageSimplifieBien () {

        return "\n" + id + " : " + type + numRue + " " + rue + " " + cp + " " + ville;
    }

    void sauvegarderBien(DataOutputStream dos) throws IOException {
        dos.writeInt(id);
        dos.writeUTF(type);
        dos.writeInt(numRue);
        dos.writeUTF(rue);
        dos.writeInt(cp);
        dos.writeUTF(ville);
    }

    void chargerBien(DataInputStream dis) throws IOException {
        id = dis.readInt();
        type = dis.readUTF();
        numRue = dis.readInt();
        rue = dis.readUTF();
        cp = dis.readInt();
        ville = dis.readUTF();
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumRue(int numRue) {
        this.numRue = numRue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    /*
        Dis si le bien est loué ou non

        return boolean true si le bien est loué, false si non
    */
    boolean estLoue () {
        return false;

    }
}
