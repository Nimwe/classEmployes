package employes.model.bo;

import java.util.ArrayList;
import java.util.List;

// Attention : ne pas séparer les attributs dans ta classe (aRestaurant en bas de fichier)
public class Agences {

    private String nomAgence;
    private String adresse;
    private int codePostal;
    private String ville;
    private List<Employes> employesAgence = new ArrayList<>();

    // Constructor
    public Agences(String nomAgence, String adresse, int codePostal, String ville, boolean aRestaurant) {
        this.nomAgence = nomAgence;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.aRestaurant = aRestaurant;
    }

    // Getter - Setter
    public String getNomAgence() {
        return nomAgence;
    }

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getcodePostal() {
        return codePostal;
    }

    public void setcodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<Employes> getEmployes() {
        return employesAgence;
    }

    public boolean hasRestaurant() {
        return aRestaurant;
    }

    // toString
    @Override
    public String toString() {
        return "Agence de " + nomAgence
                + ", " + adresse
                + " " + codePostal
                + " " + ville
                + " dont la restauration est prise en charge via "
                + (aRestaurant ? "Restaurant d'entreprise, " : "Tickets restaurant, ") + "\n";
    }

    // Methodes

    // Employés par agences
    /**
     * Ajoute un employe à la liste des employés de l'agence si il n'y est pas deja
     * présent
     * 
     * @param employe Employé à ajouter à l'agence
     */
    public void ajoutEmploye(Employes employe) {
        if (!employesAgence.contains(employe)) {
            employesAgence.add(employe);
        }
    }

    /**
     * Retourne une liste des noms et prénoms des employés de l'agence
     * 
     * @return une liste de caractères représentant les employés de l'agence
     */
    public List<String> getListeEmployes() {
        List<String> noms = new ArrayList<>();
        for (Employes emp : employesAgence) {
            noms.add(emp.getPrenom() + " " + emp.getNom());
        }
        return noms;
    }

    // Restaurant d'entreprise ?
    private boolean aRestaurant;

}
