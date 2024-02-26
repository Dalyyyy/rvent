package org.test;

import org.services.SponService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        /*UserService userService = new UserService();
        try{
            userService.addOrganizer(new User("foulen11","falten111","f5oulen@falten","foulen007", LocalDate.of(1995, 8, 12)));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


        try{
            System.out.println(userService.getAllUsers());

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
        SponsoringService sponsoringService = new SponsoringService();
        int idEntreprise = 1;

        try {
            // Créer une demande de sponsoring
            SponsoringRequest newRequest = new SponsoringRequest();
            newRequest.setBudget(1000);
            newRequest.setDescription("Nouvelle demande de sponsoring");
            newRequest.setStatus(false);
            newRequest.setDate(LocalDate.now());
            sponsoringService.createSponsoringRequest(newRequest);

            // Annuler une demande de sponsoring
            int requestIdToCancel = 0;
            sponsoringService.cancelSponsoringRequest(requestIdToCancel);

            // Consulter l'historique des demandes de sponsoring
            List<SponsoringRequest> history = sponsoringService.SponsoringRequestHistory(idEntreprise);
            for (SponsoringRequest request : history) {
                System.out.println("Budget: " + request.getBudget() + ", Description: " + request.getDescription());
            }

            // Consulter les demandes de sponsoring validées
            List<SponsoringRequest> trueRequests = sponsoringService.SponsoringRequestTrue(idEntreprise);
            for (SponsoringRequest request : trueRequests) {
                System.out.println("Budget validé: " + request.getBudget() + ", Description: " + request.getDescription());
            }

            // Consulter les demandes de sponsoring en attente
            List<SponsoringRequest> falseRequests = sponsoringService.SponsoringRequestFalse(idEntreprise);
            for (SponsoringRequest request : falseRequests) {
                System.out.println("Budget en attente: " + request.getBudget() + ", Description: " + request.getDescription());
            }

            // Consulter les sponsors et la recette de sponsoring
            sponsoringService.consulterSponsorsEtRecette(idEntreprise);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'exécution des méthodes: " + e.getMessage());
        }*/


    }





}