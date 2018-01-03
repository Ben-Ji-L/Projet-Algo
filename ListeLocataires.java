/**
 * Classe gérant l'ensemble des objets "Locataire" enregistrés par l'utilisateur
 */

import java.io.*;

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
       /*
        Charge les locataires précédement sauvegardés dans la liste créée à l'affichage du menu
         */
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

        for (int i=0; i<nbLocataires; i++) {

            resultat += tableauDeLocataires [i].toString() + "\n";
        }
        System.out.println (resultat);
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

    /**
     * Permet de récupérer le nombre total courant d'objets Locataire
     * @return le nombre total d'objets Locataire
     */
    public int getNbLocataires() {
        return nbLocataires;
    }
}
