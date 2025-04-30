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
    private List<Enfants> enfants = new ArrayList<>();

    // Date du jour mis à jour automatique
    LocalDate today = LocalDate.now();

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

    /**
     * Vérification de l'anciennetè dans l'entreprise pour pouvoir prétendre aux
     * chèques vacances
     * Il faut minimun 1 an d'anciennetè pour y avoir le droit
     * 
     * @return La possibilité ou non d'avoir des cheques vacances
     */
    public String chqVacances() {
        if (getAnciennete() >= 1) {
            return "Vous avez le droit aux cheques vacances";
        }
        return "Il faut 1 an d'ancienneté pour pouvoir prétendre aux cheques vacances";
    }

    /**
     * Copie de la iste des enfants pour eviter l'ecrasement
     * Création d'une copie indépendante de la liste pour chaque employé
     * 
     * @param enfant
     */
    public void setEnfant(List<Enfants> enfants) {
        this.enfants = new ArrayList<>();
        for (Enfants enf : enfants) {
            this.enfants.add(new Enfants(enf.getNom(), enf.getDateNaissance()));
        }
    }

    /**
     * Une fois les enfants de chaque employés ajoutés
     * 
     * @return la liste des enfants
     */
    public List<Enfants> getEnfants() {
        return enfants;
    }

    /**
     * Verifie si l'employé a des enfants éligibles aux chèques de Noel et renvoie
     * un résumé du nombre d'enfants ainsi que du montant du ou des chèque(s)
     * 
     * - 20 € pour les enfants de 0 à 10 ans
     * - 30 € pour les enfants de 11 à 15 ans
     * - 50 € pour les enfants de 16 à 18 ans
     * 
     * @return un message indiquant si l'employé peut beneficier des chèques Noel et
     *         leur nombre par catégorie
     */
    public String chqNoel() {
        if (enfants == null || enfants.isEmpty()) {
            return this.prenom + this.nom + " n'a pas d'enfants.";
        }

        int chq20 = 0;
        int chq30 = 0;
        int chq50 = 0;

        for (Enfants enfant : enfants) {
            int age = enfant.getAge();
            if (age >= 0 && age <= 10)
                chq20++;
            else if (age >= 11 && age <= 15)
                chq30++;
            else if (age >= 16 && age <= 18)
                chq50++;
        }

        if (chq20 + chq30 + chq50 == 0) {
            return this.prenom + " " + this.nom + " a des enfants trop âgés pour les chèques de noel.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(this.prenom).append(" ").append(this.nom).append(" a des enfants. \n");
        if (chq20 > 0)
            sb.append(" - ").append(chq20).append(" chèque(s) de 20 €.\n");
        if (chq30 > 0)
            sb.append(" - ").append(chq30).append(" chèque(s) de 30 €.\n");
        if (chq50 > 0)
            sb.append(" - ").append(chq50).append(" chèque(s) de 50 €.\n");

        return sb.toString();
    }

}