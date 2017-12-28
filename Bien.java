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

    private String type;
    private int numRue;
    private String rue;
    private int cp;
    private String ville;

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

        System.out.print ("Entrez la rue du bien : ");
        rue = sc.nextLine();

        System.out.print ("Entrez le code postal du bien : ");
        cp = sc.nextInt();

        System.out.print ("Entrez la ville du bien : ");
        ville = sc.nextLine();
    }
    
    /*
        Modifier le bien
        
        return le bien modifié
    */
    Bien setBien () {
        
        return null;
        
    }
    
    /*
        Supprimme le bien
    */
    void delBien () {
        
        
        
    }
    
    /*
        Dis si le bien est loué ou non
        
        return boolean true si le bien est loué, false si non
    */
    boolean estLoue () {
        return false;
        
    }
}