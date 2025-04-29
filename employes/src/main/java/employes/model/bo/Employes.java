package employes.model.bo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Employes implements Comparable<Employes> {

    private String nom;
    private String prenom;
    private LocalDate dateEmbauche;
    private String fonction;
    private double salaire;
    private String service;
    private static int nombreEmployes = 0;
    private Agences agence;

    // Date du jour mis à jour automatique
    LocalDate today = LocalDate.now();

    // Attention :
    // Pour enfant => dupliquer la liste ici en plus de la creer pour eviter les
    // ecrasement
    // celui du main sert de tableau temporaire pour le formulaire et le dupliquer
    // sera le tableau'final' qui correspond à la bdd

    // Constructeur
    public Employes(String nom, String prenom, LocalDate dateEmbauche, String fonction, int salaire, String service,
            Agences agence) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateEmbauche = dateEmbauche;
        this.fonction = fonction;
        this.salaire = salaire;
        this.service = service;
        this.agence = agence;
        nombreEmployes++;

        if (agence != null) {
            agence.ajoutEmploye(this);
        }
    }

    /**
     * Retourne une liste des employés en String
     * 
     * @param employes de la liste des employés à afficher
     * @return une liste de String représentant chaque employé
     */
    public static List<String> afficherEmployes(List<Employes> employes) {
        List<String> employesTri = new ArrayList<>();
        for (Employes emp : employes) {
            employesTri.add(emp.toString());
        }
        return employesTri;
    }

    // Getteurs et Setteurs
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getAnciennete() {
        return Period.between(this.dateEmbauche, LocalDate.now()).getYears();
    }

    public static int getNombreEmployes() {
        return nombreEmployes;
    }

    public void setAgence(Agences agence) {
        this.agence = agence;
    }

    public Agences getAgence() {
        return agence;
    }

    // Generate to.string
    @Override
    public String toString() {
        return nom + " " + prenom + ", de l' " + agence + " au service " + service
                + ", est entré dans l'entreprise le " + dateEmbauche + ", (ancienneté : " + getAnciennete() + " ans.),"
                + " au poste de " + fonction + ", pour un salaire de " + salaire + " euros \n";
    }

    @Override
    public int compareTo(Employes emp) {
        int triPrenom = this.prenom.compareTo(emp.getPrenom());
        if (triPrenom != 0) {
            return triPrenom; // Tri par prénom quand non identiques
        }
        return this.nom.compareTo(emp.getNom()); // Si prénoms identiquess tri par nom
    }

    /**
     * Calcul de la prime annuelle de 5% du salaire brut
     * 
     * @return le montant de la prime en fonction du salaire
     */
    public double primeSalaire() {
        return this.salaire * 0.05;
    }

    /**
     * Calcul de la prime de 2% ppour chaque année d'ancienneté
     * 
     * @return Le montant de la prime
     */
    public double primeAnciennete() {
        return this.salaire * 0.02 * getAnciennete();
    }

    /**
     * Calcul du montant total des primes de l'employé, la prime sur salaire et
     * la prime d'ancienneté
     * 
     * @return Le montant des primes
     */
    public double primes() {
        return primeSalaire() + primeAnciennete();
    }

    /**
     * Retourne la date du versement des primes, fixée au 30 Novembre de chaque
     * année
     * 
     * @return La date de versement des primes
     */
    public LocalDate versementPrimes() {
        return LocalDate.of(LocalDate.now().getYear(), 11, 30);
    }

    //
    /**
     * Verifie si la date du jour correspond à la date d'envoi du versement des
     * primes (30 Novembre).
     * Indique que le virement à été effectué si c'est le cas
     * 
     * @return Un message indiquant si le virement a été effectué ou non
     */
    public String transfertEffectue() {
        if (LocalDate.now().isEqual(versementPrimes())) {
            return "Le virement a été effectué";
        }
        return "Nous ne sommes pas à la date echue du versement des primes";
    }

    // Test de l'envoi du message du transfert effectué => Simulation dans le
    // terminal
    public String testMsgTransfertOk() {

        LocalDate today = LocalDate.of(2025, 11, 30);
        LocalDate dateVersement = LocalDate.of(today.getYear(), 11, 30);

        if (today.isEqual(dateVersement)) {
            return "Le virement a été effectué";
        }
        return "Nous ne sommes pas à la date echue du versement des primes";
    }

    /**
     * Calcul de la masse salariale totale de l'entreprise en additionnant les
     * salaires et les primes de tous les employés de la liste.
     * 
     * @param employes de la liste des employés
     * @return la masse salariale totale
     */
    public static double masseSalariale(List<Employes> employes) {
        double total = 0;
        for (Employes employe : employes) {
            total += employe.getSalaire() + employe.primes();
        }
        return total;
    }

}