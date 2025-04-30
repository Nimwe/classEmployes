package employes.model.bo;

import java.time.LocalDate;

public class Directeur extends Employes {

    public Directeur(String nom, String prenom, LocalDate dateEmbauche, String poste, int salaire, String service,
            Agences agence) {
        super(nom, prenom, dateEmbauche, poste, salaire, service, agence);
    }

    @Override
    public double primeSalaire() {
        return this.getSalaire() * 0.07;
    }

    @Override
    public double primeAnciennete() {
        return this.getSalaire() * 0.03 * this.getAnciennete();
    }

    @Override
    public double primes() {
        return primeSalaire() + primeAnciennete();
    }

    @Override
    public String toString() {
        return "Directeur : " + getPrenom() + " " + getNom() + " ( " + getFonction() + " " + getAgence() + ") \n";
    }
}
