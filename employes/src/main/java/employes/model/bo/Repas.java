package employes.model.bo;

public class Repas {

    public static String getTypeRestauration(Agences agence) {
        return agence.hasRestaurant() ? "Restaurant d'entreprise" : "Ticket restaurant" ;
    }
}
