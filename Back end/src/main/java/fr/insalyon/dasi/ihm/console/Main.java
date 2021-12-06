package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.service.Service;
import fr.insalyon.dasi.metier.service.technique.Initialisation;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DASI Team
 */
public class Main {

    public static void main(String[] args) throws Exception {
        JpaUtil.init();

        Initialisation.InitClients();
        Initialisation.InitEmployes();
        Initialisation.InitMedium();
        Initialisation.InitConsultations();

        /*
        
        Service service = new Service();
        SimpleDateFormat simpleDate=new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = simpleDate.parse("10/12/1815");
        Client claude = new Client("Chappe", "Claude", "claude.chappe1@insa-lyon.fr", "HaCKeR", "num", dateNaissance,"M.");
        Client sympa = new Client("Sympa", "Monsieur", "mail", "mdp", "num", dateNaissance,"M.");
        
        testerInscriptionClient(claude);
        testerInscriptionClient(sympa);

        testerAuthentificationClient(); 
        testerAuthentificationEmploye();

        testerModifClient(claude);

        testerModifEmploye(service.chercherEmployeParMail("camille.martin@predictif.fr"));
        
        //testerCreerConsultation(claude);
        testerCreerConsultation(sympa);
        
        //testerEnvoyerConfirmation(service.chercherConsultationParId(Long.valueOf(1)));
        //testerDebutConsultation(service.chercherConsultationParId(Long.valueOf(1)));
        //testerCreerPredictions(service.chercherConsultationParId(Long.valueOf(1)));
        testerRecupererConsultCouranteDeEmploye();
        //testerFinConsultation(service.chercherConsultationParId(Long.valueOf(1)));
        //testerEcrireCommentaire(service.chercherConsultationParId(Long.valueOf(1)),"parfait");
        //testerValiderConsultation(service.chercherConsultationParId(Long.valueOf(1)));
        
        testerConsulterHistoriqueClient(claude);
         
        testerListerSpirites();
        testerListerCartomanciens();
        testerListerAstrologues();
        testerlisterTop5Medium();
        testerDenombrerClientsParEmploye();
        testerDenombrerConsultationsParMedium();
        
         */
        JpaUtil.destroy();
    }

    public static void afficherClient(Client client) {
        System.out.println("-> " + client);
    }

    public static void afficherEmploye(Employe employe) {
        System.out.println("-> " + employe);
    }

    public static void afficherMedium(Medium medium) {
        System.out.println("-> " + medium);
    }

    public static void afficherConsultation(Consultation consultation) {
        System.out.println("-> " + consultation);
    }

    public static void testerInscriptionClient(Client unClient) throws Exception {

        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();

        Service service = new Service();

        try {
            Long idClaude = service.inscrireClient(unClient);
            //Long idProfil = service.creerProfilAstral(claude);
            if (idClaude != null) {
                System.out.println("> Succès inscription");
            } else {
                System.out.println("> Échec inscription");
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
        }

        afficherClient(unClient);
        System.out.println();
        System.out.println();

    }

    public static void testerAuthentificationClient() {

        System.out.println();
        System.out.println("**** testerAuthentificationClient() ****");
        System.out.println();

        Service service = new Service();
        Client client;
        String mail = "claude.chappe1@insa-lyon.fr";
        String motDePasse = "HaCKeR";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
        System.out.println();
        System.out.println();
    }

    public static void testerAuthentificationEmploye() {

        System.out.println();
        System.out.println("**** testerAuthentificationEmploye() ****");
        System.out.println();

        Service service = new Service();
        Employe employe;
        String mail;
        String motDePasse;

        mail = "harry.potter@predictif.fr";
        motDePasse = "voldemort";
        employe = service.authentifierEmploye(mail, motDePasse);
        if (employe != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherEmploye(employe);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
        System.out.println();
        System.out.println();
    }

    public static void testerModifClient(Client unClient) throws ParseException {
        try {

            Service service = new Service();

            System.out.println("**** testerModifClient() ****");
            unClient.setPrenom("Pierre");
            service.modifierInfoClient(unClient);
            System.out.println();
            System.out.println();
            System.out.println(" Client modifié");
        } catch (Exception ex) {
            System.out.println(" Client non modifié");
        }
        System.out.println();
        System.out.println();
    }

    public static void testerModifEmploye(Employe unEmploye) throws ParseException {
        try {

            Service service = new Service();

            System.out.println("**** testerModifEmploye() ****");
            unEmploye.setNom("Migozzi");
            service.modifierInfoEmploye(unEmploye);
            System.out.println();
            System.out.println();
            System.out.println(" Employe modifié");
        } catch (Exception ex) {
            System.out.println(" Employe non modifié");
        }
        System.out.println();
        System.out.println();
    }

    public static void testerCreerConsultation(Client unClient) throws ParseException {

        try {

            Service service = new Service();

            System.out.println("**** testerCreerConsultation(" + unClient.getNom() + ") ****");

            if (service.creerConsultation(unClient, service.chercherMediumParId(Long.valueOf(1)))) { //f
                System.out.println("*** Consultation créée. Votre medium vous contactera prochainement.");
            } else {
                System.out.println("*** Le medium que vous avez choisi n'est pas disponible pour le moment ou vous avez déjà une consultation en cours. Rééssayez plus tard.");
            }
            /*
           if( service.creerConsultation(unClient,service.chercherMediumParId( Long.valueOf(2))) ){//f
                 System.out.println("*** Consultation créée. Votre medium vous contactera prochainement.");
            }else{
                System.out.println("*** Le medium que vous avez choisi n'est pas disponible pour le moment ou vous avez déjà une consultation en cours. Rééssayez plus tard.");
            }
           
           if( service.creerConsultation(unClient,service.chercherMediumParId( Long.valueOf(1)))){//f
                 System.out.println("*** Consultation créée. Votre medium vous contactera prochainement.");
            }else{
                System.out.println("*** Le medium que vous avez choisi n'est pas disponible pour le moment ou vous avez déjà une consultation en cours. Rééssayez plus tard.");
            }*/

        } catch (Exception ex) {
            System.out.println("*** Consultation non créée");
            System.out.println();
        }
        System.out.println();
        System.out.println();

    }

    public static void testerEnvoyerConfirmation(Consultation consultation) {
        Service service = new Service();

        System.out.println("**** testerEnvoyerConfirmation() ****");

        service.envoyerConfirmation(consultation);

        /*
        boolean test = service.envoyerConfirmation(consultation);
        if (test){
            System.out.println("Consultation confirmée");
        }*/
        System.out.println();
        System.out.println();
    }

    public static void testerDebutConsultation(Consultation consultation) {

        Service service = new Service();

        System.out.println("**** testerDebutConsultation() ****");

        boolean test = service.debuterConsultation(consultation);

        if (test) {
            System.out.println("Consultation débutée");
        } else {
            System.out.println("Consultation non débutée");
        }
        System.out.println();
        System.out.println();
    }

    public static void testerCreerPredictions(Consultation consultation) {

        Service service = new Service();

        System.out.println("**** testerCreerPredictions() ****");

        List<String> predictions = service.creerPredictionAleatoire(consultation, 1, 3, 2);

        for (String prediction : predictions) {
            System.out.println(prediction);
        }
        System.out.println();
        System.out.println();
    }

    public static void testerRecupererConsultCouranteDeEmploye() {

        Service service = new Service();
        Employe employe = service.chercherEmployeParMail("harry.potter@predictif.fr");
        System.out.println("**** testerRecupererConsultCouranteDeEmploye ****");
        System.out.println();
        Consultation consultation = service.recupererConsultCouranteDeEmploye(employe);

        System.out.println("La consultation courante de cette employé est: ");
        afficherConsultation(consultation);
        System.out.println();
        System.out.println();

    }

    public static void testerFinConsultation(Consultation consultation) {

        Service service = new Service();

        System.out.println("**** testerFinConsultation() ****");
        System.out.println();
        boolean test = service.terminerConsultation(consultation);

        if (test) {
            System.out.println("Consultation terminée");
        } else {
            System.out.println("Consultation non terminée");
        }
        System.out.println();
        System.out.println();

    }

    public static void testerEcrireCommentaire(Consultation consultation, String commentaire) {

        Service service = new Service();

        System.out.println("**** testerEcrireConsultation() ****");
        System.out.println();
        service.ecrireCommentaire(consultation, commentaire);

        System.out.println("Commentaire écrit");
        System.out.println();
        System.out.println();
    }

    public static void testerValiderConsultation(Consultation consultation) {

        Service service = new Service();

        System.out.println("**** testerValiderConsultation() ****");

        boolean fini = service.validerConsultation(consultation);

        System.out.println();
        if (fini) {
            System.out.println("Consultation validée");
        } else {
            System.out.println("Consultation non validée");
        }
        System.out.println();
        System.out.println();
    }

    public static void testerConsulterHistoriqueClient(Client client) {

        System.out.println();
        System.out.println("**** testerConsulterHistoriqueClient() ****");
        System.out.println();

        Service service = new Service();
        List<Consultation> historique = service.consulterHistoriqueClient(client);
        System.out.println("Historique du client :");
        if (historique != null) {
            for (Consultation consultation : historique) {
                afficherConsultation(consultation);
            }
        } else {
            System.out.println("=> ERREUR...");
        }
        System.out.println();
        System.out.println();
    }

    public static void testerListerSpirites() throws ParseException {

        try {

            Service service = new Service();

            System.out.println("**** testerListerSpirites() ****");

            List<Spirite> nosSpirites = service.listerSpirites();
            System.out.println();
            System.out.println();

            for (Spirite spirite : nosSpirites) {
                afficherMedium(spirite);
            }
        } catch (Exception ex) {
            System.out.println(" Erreur d'affichage de la liste des spirites");
        }
        System.out.println();
        System.out.println();
    }

    public static void testerListerCartomanciens() throws ParseException {

        try {

            Service service = new Service();

            System.out.println("**** testerListerCartomanciens() ****");

            List<Cartomancien> nosCartomanciens = service.listerCartomanciens();
            System.out.println();
            System.out.println();

            for (Cartomancien cartomancien : nosCartomanciens) {
                afficherMedium(cartomancien);
            }

        } catch (Exception ex) {
            System.out.println("Erreur d'affichage de la liste des cartomanciens");
        }
        System.out.println();
        System.out.println();
    }

    public static void testerListerAstrologues() throws ParseException {

        try {

            Service service = new Service();

            System.out.println("**** testerListerAstrologues() ****");

            List<Astrologue> nosAstrologues = service.listerAstrologues();
            System.out.println();
            System.out.println();

            for (Astrologue astrologue : nosAstrologues) {
                afficherMedium(astrologue);
            }

        } catch (Exception ex) {
            System.out.println("*** Erreur d'affichage de la liste des astrologues");
        }
        System.out.println();
        System.out.println();

    }

    public static void testerlisterTop5Medium() throws ParseException {

        try {

            Service service = new Service();

            System.out.println("**** testerlisterTop5Medium() ****");

            List<Medium> notreTop5 = service.listerTop5Medium();
            System.out.println();
            System.out.println();

            for (int i = 0; i < notreTop5.size(); i++) {
                afficherMedium((Medium) notreTop5.get(i));
            }

        } catch (Exception ex) {
            System.out.println("*** Erreur d'affichage de la liste du top 5 des mediums");
        }
        System.out.println();
        System.out.println();
    }

    public static void testerDenombrerClientsParEmploye() throws ParseException { //prend en comte les employés qui n'ont pas eu de consultations également        
        try {
            Service service = new Service();

            System.out.println("**** testerDenombrerClientsParEmploye() ****");

            Map<Employe, Integer> nosCliParEmploye = service.denombrerClientsParEmploye();

            System.out.println();

            System.out.println();

            Set<Employe> keys = nosCliParEmploye.keySet();

            for (Employe key : keys) {
                System.out.println(" L'employé: " + key + "s'est occupé de " + nosCliParEmploye.get(key) + " clients.");
            }

        } catch (Exception ex) {
            System.out.println("*** Erreur de dénombrement de clients/ Employé");
        }
        System.out.println();
        System.out.println();

    }

    public static void testerDenombrerConsultationsParMedium() throws ParseException {
        try {

            Service service = new Service();

            System.out.println("**** testerDenombrerConsultationsParMedium() ****");

            Map<Medium, Integer> nosConsParMed = service.denombrerConsultationsParMedium();

            System.out.println();

            System.out.println();

            Set<Medium> keys = nosConsParMed.keySet();

            for (Medium key : keys) {
                System.out.println(" Le medium: " + key + "a été sollicité pour " + nosConsParMed.get(key) + " consultations.");
            }

        } catch (Exception ex) {
            System.out.println("*** Erreur de dénombrement de consultations/ Medium");
        }
        System.out.println();
        System.out.println();
    }
}
