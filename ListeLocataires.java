/**
 * Classe gérant l'ensemble des objets "Locataire" enregistrés par l'utilisateur
 */

import java.io.*;
import java.util.Arrays;


public class ListeLocataires {

    private Locataire [] tableauDeLocataires;
    private int nbLocataires;
    private int prochainIdent;

    /**
     * Construit un objet "Liste"
     */
    ListeLocataires () throws IOException {

        tableauDeLocataires = new Locataire [500];
        nbLocataires = 0;
        prochainIdent = 1;

        //Charge les locataires précédement sauvegardés dans la liste créée à l'affichage du menu
        chargerListe();

    }

    /**
     * Ajoute un Locataire saisi dans un tableau de Locataire en lui attribuant un identifiant
     * @param loc Locataire à ajouter à la liste
     */
    void ajouterLocataire (Locataire loc) {
        int ident;

        ident = genererIdentifiant();
        loc.setIdentifiant(ident);
        tableauDeLocataires [nbLocataires] = loc;
        nbLocataires ++;

    }

    /**
     * Supprime un locataire du tableau de Locataires
     * @param ident Identifiant du locataire à supprimer
     */
    void supprimerLocataire (int ident) {

        boolean trouve = false;

        //On parcourt la liste des locataires jusqu'à l'avant dernier locataire pour trouver le locataire à supprimer
        for (int i=0; i<nbLocataires-1; i++) {
            if (tableauDeLocataires[i].getIdentifiant() == ident) {
                if (tableauDeLocataires[i].getNbBiensLoues()!=0) {
                    System.out.println ("Impossible de supprimer ce locataire car il a toujours des biens en location.\n");
                    return;
                }
                trouve = true;
            }
            //Si on a trouvé le locataire à supprimer, on l'écrase en le remplaçant
            // par le locataire qui suit, et ainsi de suite jusqu'à la fin de la boucle de recherche
            if (trouve) {
                tableauDeLocataires[i]=tableauDeLocataires[i+1];
            }
        }

        //Si on n'a pas trouvé le locataire à supprimer en parcourant la liste jusqu'à l'avant-dernier,
        //on cherche s'il ne se trouve pas en dernière position de la liste
        if (!trouve) {
            if (tableauDeLocataires[nbLocataires-1].getIdentifiant()==ident) {
                if (tableauDeLocataires[nbLocataires-1].getNbBiensLoues()!=0) {
                    System.out.println ("Impossible de supprimer ce locataire car il a toujours des biens en location.\n");
                    return;
                }
                trouve = true;
            }
        }

        //Une fois trouvé et les modifications à faire effectuées, on ignore la dernière case du tableau
        //et on diminue le nombre de locataires de 1 pour la "supprimer"
        if (trouve) {
            tableauDeLocataires[nbLocataires-1] = null;
            nbLocataires --;
            System.out.println ("Locataire supprimé.\n");
        }
        else {
            System.out.println ("Identifiant inconnu.\n");
        }
    }

    /**
     * Permet de modifier le nom d'un Locataire
     * @param ident Identifiant du locataire
     * @param newNom Nouveau nom
     */
    void modifierNom (int ident, String newNom) {

        for (int i=0; i<nbLocataires; i++) {
            if (tableauDeLocataires[i].getIdentifiant()==ident) {
                tableauDeLocataires[i].setNom(newNom);
                return;
            }
        }
    }

    /**
     * Permet de modifier le numéro de téléphone d'un Locataire
     * @param ident Identifiant du locataire
     * @param newTelephone Nouvau numéro de téléphone
     */
    void modifierTelephone (int ident, String newTelephone) {

        for (int i=0; i<nbLocataires; i++) {
            if (tableauDeLocataires[i].getIdentifiant()==ident) {
                tableauDeLocataires[i].setNumTel(newTelephone);
                return;
            }
        }
    }

    /**
     * Permet de modifier l'adresse d'un locataire
     * @param ident Identifiant du locataire
     * @param newAdresse Nouvelle adresse
     */
    void modifierAdresse (int ident, String newAdresse) {

        for (int i=0; i<nbLocataires; i++) {
            if (tableauDeLocataires[i].getIdentifiant()==ident) {
                tableauDeLocataires[i].setAdresse(newAdresse);
                return;
            }
        }
    }

    /**
     * Permet d'afficher la liste complète (Identifiant, nom, adresse, numéro de téléphone, nombre de biens loués, liste de biens loués) de tous les Locataires enregistrés
     */
    void afficherListeDesLocataires () {
        String resultat = "";

        if (nbLocataires==0) {
            System.out.print ("Il n'y a pas encore de locataire enregistré.\n");
        }
        else {
            for (int i = 0; i < nbLocataires; i++) {

                resultat += tableauDeLocataires[i].toString() + "\n";
            }
            System.out.println (resultat);
        }
    }

    /**
     * Permet d'afficher une liste simplifiée (Identifiant, nom) de tous les Locataires enregistrés
     */
    void afficherListeSimplifiee (){
        String resultat = "";

        for (int i=0; i<nbLocataires; i++) {

            resultat += tableauDeLocataires[i].affichageSimplifieLocataire() + "\n";
        }
        System.out.println(resultat);
    }

    /**
     * Permet d'afficher le contenu d'un objet Locataire à partir de son identifiant
     * @param ident Identifiant du locataire
     */
    void afficherUnLocataire (int ident) {

        for (int i=0; i<nbLocataires; i++) {

            if (tableauDeLocataires[i].getIdentifiant()==ident) {
                System.out.print (tableauDeLocataires [i].toString());
                return;
            }
        }
    }

    /**
     * Affiche la liste des biens loués par un locataire
     * @param ident L'identifiant du locataire concerné
     */
    void afficherListeDesBiensPourUnLocataire (int ident) throws IOException {
        int [] listeBiensTemporaire;
        ListeBiens listeBiens = new ListeBiens();

        //On parcourt la liste des locataires pour trouver le locataire demandé à partir de son identifiant
        for (int i=0; i<nbLocataires; i++) {
            if (tableauDeLocataires[i].getIdentifiant()==ident) {
                if (tableauDeLocataires[i].getNbBiensLoues()==0) {
                    System.out.print ("Ce locataire n'a pas de biens en cours de location.");
                }
                else {
                    //On stocke la liste des identifiants des biens du locataire identifié, dans une liste temporaire
                    listeBiensTemporaire = tableauDeLocataires[i].getListeBiens();
                    //En fonction du nombre de biens loués par ce locataire, on parcourt la liste
                    //et on affiche chaque bien à l'aide d'une méthode qui affiche un bien à partir de son identifiant
                    for (int j=0; j<tableauDeLocataires[i].getNbBiensLoues(); j++) {
                        listeBiens.afficherBienParId(listeBiensTemporaire[j]);
                        System.out.print ("\n");
                    }
                }
                return;
            }
        }
    }

    /**
     * Affiche les locataires (Identifiant, nom) qui louent au moins un bien
     * @param afficherBiens dit si on affiche ou pas la liste détaillée de ses biens, ou simplement le nombre total de biens loués
     * @throws IOException
     */
    void afficherLocatairesLouantAuMoinsUnBien (boolean afficherBiens) throws IOException {
        ListeBiens listeBiens = new ListeBiens();

        //On parcourt la liste des locataires
        for (int i=0; i<nbLocataires; i++) {
            //On regarde si le nombre de biens loués par le locataire à l'indice "i" est au moins égal à 1
            if (tableauDeLocataires[i].getNbBiensLoues()>=1) {
                System.out.print (tableauDeLocataires[i].affichageSimplifieLocataire() + "\n");
                System.out.print ("Nombre de biens loués : " + tableauDeLocataires[i].getNbBiensLoues() + "\n\n");

                //Si le booleen est à "true", on affiche aussi la liste de tous ses biens (pour répondre à une autre requête)
                if (afficherBiens) {
                    afficherListeDesBiensPourUnLocataire(tableauDeLocataires[i].getIdentifiant());
                }
            }
        }
    }

    /**
     * Affiche la liste simplifiée des locataire en fonction d'un identifiant de type de bien
     * @param identType l'identifiant du type de bien recherché
     * @throws IOException
     */
    void afficherTousLesLocatairesPourUnTypeDeBien (int identType) throws IOException {

        for (int i=0; i<nbLocataires; i++) {
            //On utilise une fonction booleenne de la classe locataire, disant s'il loue ou pas ce type de bien
            if (tableauDeLocataires [i].loueCeTypeDeBien(identType)) {
                System.out.print (tableauDeLocataires [i].affichageSimplifieLocataire() + "\n");
            }
        }
    }

    /**
     * Génère un identifiant unique
     * @return un identifiant unique
     */
    int genererIdentifiant () {
        int  identifiant;

        identifiant = prochainIdent;
        prochainIdent++;
        return identifiant;
    }

    /**
     * Sauvegarde la liste courante des objets Locataire
     * @throws IOException Erreur d'écriture dans le fichier
     */
    void sauvegarderListe () throws IOException {
        String fichier = "Locataires.bin";
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

    /**
     * Charge la liste courante des objets Locataire
     * @throws IOException
     */
    void chargerListe () throws IOException {
        String fichier = "Locataires.bin";

        File file = new File(fichier);
        if (!file.exists()) {
            return;
        }

        DataInputStream dis;
        dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fichier)));

        prochainIdent = dis.readInt();
        nbLocataires = dis.readInt();
        for (int i = 0; i < nbLocataires; i++) {
            Locataire loc = new Locataire();
            loc.chargerLocataire(dis);
            tableauDeLocataires[i] = loc;
        }
        dis.close();
    }
    
    void louer(int idBien, int idLocataire) {

        for (int i=0; i < nbLocataires; i++) {
            if (tableauDeLocataires[i].getIdentifiant() == idLocataire) {
                tableauDeLocataires[i].ajouterUnBien(idBien);
            }
        }
    }
    
    void liberer(int idBien, int idLocataire) {
    	
    	for (int i=0; i < nbLocataires; i++) {
            if (tableauDeLocataires[i].getIdentifiant() == idLocataire) {
                tableauDeLocataires[i].supprimerUnBien(idBien);
            }
        }
    }
    
    public Locataire[] trierListe() {
    	Locataire [] aTrier = new Locataire [nbLocataires];
    	
    	for (int i = 0; i < nbLocataires; i++) {
    		aTrier[i] = tableauDeLocataires[i];
    	}
    	Arrays.sort(aTrier);
    	return aTrier;
    }
    
    public void afficherListeLocAlpha(Locataire [] tabTrier) {
    	
    	String resultat = "";

        for (int i=0; i<nbLocataires; i++) {

            resultat += tabTrier[i].affichageSimplifieLocataire() + "\n";
        }
        System.out.println(resultat);
    }
    
    /**
     * Permet de récupérer le nombre total courant d'objets Locataire
     * @return le nombre total d'objets Locataire
     */
    public int getNbLocataires() {
        return nbLocataires;
    }

    
}
