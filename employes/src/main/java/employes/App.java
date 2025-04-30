package employes;

import employes.model.bo.Agences;
import employes.model.bo.Directeur;
import employes.model.bo.Employes;
import employes.model.bo.Enfants;
import employes.model.bo.Repas;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) {

        // Agences (Ex.5)

        // Crée une liste
        List<Agences> agences = new ArrayList<>();
        // Ajout des agences
        Agences agenceBrest = new Agences("Brest", "15 rue du Petit Spernot", 29200, "Brest", false);
        Agences agenceLanderneau = new Agences("Landerneau", "5 rue du Pontic", 29800, "Landerneau", true);
        Agences agenceQuimper = new Agences("Quimper", "Rue de Saint-Alor", 29000, "Quimper", true);
        Collections.addAll(agences, agenceBrest, agenceLanderneau, agenceQuimper);
        // Affichage de la liste des agences
        System.out.println("**Listes des agences**\n ");
        for (Agences agence : agences) {
            System.out.println(agence);
        }
        /*
         * => Collections.addAll -> Optimisation de :
         * agences.add(agenceBrest);
         * agences.add(agenceLanderneau);
         * agences.add(agenceQuimper);
         */

        // Employés (Ex.4)

        // Crée une liste
        List<Employes> employes = new ArrayList<>();
        // Ajout des employés
        employes.add(
                new Employes("Rawen", "Marie", LocalDate.of(2010, 2, 14), "ADV", 30000, "Direction", agenceLanderneau));
        employes.add(new Employes("Olya", "Lili", LocalDate.of(2012, 7, 1), "Logistique", 40000, "Général",
                agenceLanderneau));
        employes.add(new Employes("Niephaste", "Vincent", LocalDate.of(2024, 6, 1), "DévFullstack", 28000, "DSI",
                agenceBrest));
        employes.add(new Employes("Boulicaut", "Maui", LocalDate.of(2015, 9, 25), "Vendeur export", 50000, "Export",
                agenceBrest));
        employes.add(
                new Employes("Lelostec", "Joatan", LocalDate.of(2019, 12, 20), "DévJAVA", 38000, "DSI",
                        agenceQuimper));
        // Affichage de la liste des employés
        System.out.println("\n** Nombre d'employés : " + employes.size() + " **\n");
        // Determination d'un directeur
        Employes directeur = new Directeur("Lelostec", "Joatan", LocalDate.of(2019, 12, 20), "Directeur", 38000, "DSI",
                agenceQuimper);
        employes.add(directeur);
        System.out.println(directeur);
        // Affichage des primes
        for (Employes emp : employes) {
            double primes = emp.primes();
            System.out.println(
                    "\n ** Le 30 Novembre de cette année, " + emp.getPrenom() + " " + emp.getNom() + " recevra " +
                            String.format("%.2f", primes)
                            + " euros de primes, comprenant la prime sur salaire et la prime d'ancienneté.\n");
        } /*
           * String.format("%0.02f" args) => permet l'affichage de 2 chiffres après la
           * virgule
           */
        // Tri des employés par prénoms puis nom (Ordre alphabétique)
        Collections.sort(employes);
        System.out.println("** Tri par Prénom puis Nom ** \n");
        for (String employe : Employes.afficherEmployes(employes)) {
            System.out.println(employe);
        }
        /* Via Collections.sort -> Penser à importer Collections */

        // Tri personnalisé des employés par Services, Nom et Prénom
        employes.sort((emp1, emp2) -> { // => et du coup plus besoin de l'import Comparator avec cette optimisation
            int compare = emp1.getService().compareTo(emp2.getService());
            if (compare != 0)
                return compare;
            compare = emp1.getNom().compareTo(emp2.getNom());
            if (compare != 0)
                return compare;
            return emp1.getPrenom().compareTo(emp2.getPrenom());
        });
        /*
         * => Optimisation de :
         * 
         * Collections.sort(employes, new Comparator<Employes>() {
         * // Compare les services
         * public int compare(Employes emp1, Employes emp2) {
         * int serviceTri = emp1.getService().compareTo(emp2.getService());
         * if (serviceTri != 0) {
         * return serviceTri;
         * }
         * // Compare les noms puis les prénoms
         * int nomTri = emp1.getNom().compareTo(emp2.getNom());
         * if (nomTri != 0) {
         * return nomTri;
         * }
         * return emp1.getPrenom().compareTo(emp2.getPrenom());
         * }
         * });
         */

        // Affiche la liste triée par service, puis par nom, puis par prénom
        System.out.println("** Tri par service, Nom, et Prénom ** \n");
        for (String employe : Employes.afficherEmployes(employes)) {
            System.out.println(employe);
        }

        // Affiche les employés par agence
        for (Agences agence : agences) {
            System.out.println("** Voici les empployés de l'agence **" + agence.getNomAgence() + "\n");
            for (String nom : agence.getListeEmployes()) {
                System.out.println(nom + "\n");
            }
        }
        // Affiche la masse Salariale
        double totalMasseSalariale = Employes.masseSalariale(employes);
        System.out.println("\n** Masse salariale **");
        System.out.println("(salaires + primes) : " + totalMasseSalariale + " euros.");

        // Affiche le type de restauration en fonction des agences
        System.out.println("\n** Mode de restauration **\n");
        for (Employes emp : employes) {
            String restauration = Repas.getTypeRestauration(emp.getAgence());
            System.out.println(emp.getPrenom() + " " + emp.getNom() + " -> " + emp.getAgence().getNomAgence() + " -> "
                    + restauration);
        }

        // Affiche si l'employé a le droit ou non aux chèques vacances
        System.out.println("\n ** Chèques Vacances ** \n");
        for (Employes emp : employes) {
            System.out.println(emp.getPrenom() + " " + emp.getNom() + " : " + emp.chqVacances());
        }

        // Enfants
        // Ajout d'enfant à un employé
        List<Enfants> enfantsTemp = new ArrayList<>();
        enfantsTemp.add(new Enfants("Gospel", LocalDate.of(2013, 6, 1)));
        enfantsTemp.add(new Enfants("Gold", LocalDate.of(2009, 3, 12)));

        Employes joatan = employes.get(0);
        ajouterEnfant(joatan, enfantsTemp);

        // Affiche si l'employé à le droit ou non aux chèques noël et le cas echéant
        // combien de chèque par tranche de prix
        System.out.println(("\n ** Chèques de Noël ** \n"));
        for (Employes emp : employes) {
            System.out.println(emp.chqNoel());
        }
    }

    // Ajoute une copie de la liste de ses enfants à l'employé
    public static void ajouterEnfant(Employes employe, List<Enfants> enfantsListTemp) {
        List<Enfants> enfantsCopie = new ArrayList<>();
        for (Enfants enf : enfantsListTemp) {
            enfantsCopie.add(new Enfants(enf.getNom(), enf.getDateNaissance()));
        }
        employe.setEnfant(enfantsCopie);
    }
}

/*
 * Archive - Exercices 1, 2 et 3
 * Employes A = new Employes("Rawen", "Marie", LocalDate.of(2010, 2, 14), "ADV",
 * 30000, "Direction");
 * Employes B = new Employes("Olya", "Lili", LocalDate.of(2012, 7, 1),
 * "Logistique", 40000, "Général");
 * Employes C = new Employes("Niephaste", "Vincent", LocalDate.of(2023, 6, 1),
 * "Dév Fullstack", 28000, "DSI");
 * Employes D = new Employes("Boulicaut", "Maui", LocalDate.of(2015, 9, 25),
 * "Commercial export", 50000, "Export");
 * Employes E = new Employes("Lelostec", "Joatan", LocalDate.of(2019, 12, 20),
 * "DévJAVA", 38000, "DSI");
 * 
 * System.out.println(A);
 * System.out.println("Le 30 Novembre de cette année, vous recevrez " +
 * A.primes() + " euros de primes.");
 * System.out.println(A.testMsgTransfertOk()); // => Message pour tester le
 * transfert ok pour les primes
 * System.out.println(B);
 * System.out.println("Le 30 Novembre de cette année, vous recevrez " +
 * B.primes() + " euros de primes.");
 * System.out.println(B.testMsgTransfertOk()); // => Message pour tester le
 * transfert ok pour les primes
 * System.out.println(C);
 * System.out.println("Le 30 Novembre de cette année, vous recevrez " +
 * C.primes() + " euros de primes.");
 * System.out.println(C.testMsgTransfertOk()); // => Message pour tester le
 * transfert ok pour les primes
 * System.out.println(D);
 * System.out.println("Le 30 Novembre de cette année, vous recevrez " +
 * D.primes() + " euros de primes.");
 * System.out.println(D.testMsgTransfertOk()); // => Message pour tester le
 * transfert ok pour les primes
 * System.out.println(E);
 * System.out.println("Le 30 Novembre de cette année, vous recevrez " +
 * E.primes() + " euros de primes.");
 * System.out.println(E.testMsgTransfertOk()); // => Message pour tester le
 * transfert ok pour les primes
 * System.out.println();
 * System.out.println("Il y a actuellement : " + Employes.getNombreEmployes() +
 * " employés.");
 */
