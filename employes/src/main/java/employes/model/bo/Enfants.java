package employes.model.bo;

import java.time.LocalDate;
import java.time.Period;

public class Enfants {
    private String nom;
    private LocalDate dateNaissance;

    // Date du jour mis à jour automatique
    LocalDate today = LocalDate.now();

    // Constructor
    public Enfants(String nom, LocalDate dateNaissance) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    // Getter & Setter
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    // Calcul de l'age
    public int getAge() {
        return Period.between(this.dateNaissance, LocalDate.now()).getYears();
    }

}
