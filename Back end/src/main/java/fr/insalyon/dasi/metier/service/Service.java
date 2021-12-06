package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.metier.service.technique.AstroNetApi;
import fr.insalyon.dasi.metier.service.technique.Message;
import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.dao.ProfilAstralDao;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DASI Team
 */
public class Service {

    ClientDao clientDao = new ClientDao();
    ProfilAstralDao profilAstralDao = new ProfilAstralDao();
    EmployeDao empDao = new EmployeDao();
    ConsultationDao consultationDao = new ConsultationDao();
    MediumDao medDao = new MediumDao();
    /**
     * ***********************************************************************
     */
    /*------------------------Services client---------------------------------*/
    /**
     * ***********************************************************************
     */

    public Client rechercherClientParId(Long id) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Employe rechercherEmployeParId(Long id) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = empDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherEmployeParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Medium chercherMediumParId(Long unId) {
        Medium m = null;
        JpaUtil.creerContextePersistance();

        try {
            MediumDao medDao = new MediumDao();
            m = medDao.chercherParId(unId);

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherClientParMail", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return m;
    }
    
    public Consultation chercherConsultationParId(Long consultationId) {
        Consultation c = null;
        JpaUtil.creerContextePersistance();
        try {
            c = consultationDao.chercherParId(consultationId);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherClientParMail", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }
    
    public Client chercherClientParMail(String unMail) {
        Client c = null;
        JpaUtil.creerContextePersistance();

        try {
            c = clientDao.chercherParMail(unMail);

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherClientParMail", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }
    
    public Employe chercherEmployeParMail(String unMail) {
        JpaUtil.creerContextePersistance();
        Employe e = null;
        try {
            e = empDao.chercherParMail(unMail);

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherClientParMail", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return e;
    }
 
    public Long inscrireClient(Client client) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            AstroNetApi astroNet = new AstroNetApi();
            List<String> result = astroNet.getProfil(client.getPrenom(), client.getDateNaissance());
            ProfilAstral profilAstral = new ProfilAstral(result.get(0), result.get(1), result.get(2), result.get(3));
            client.setProfilAstral(profilAstral);
            JpaUtil.ouvrirTransaction();
            profilAstralDao.creer(profilAstral);
            clientDao.creer(client);
            JpaUtil.validerTransaction();
            resultat = client.getId();
            Message.envoyerMail("contact@predict.if", client.getMail(), "Bienvenue chez PREDICT’IF", "Bonjour " + client.getPrenom() + ", nous vous confirmons votre inscription au service PREDICT’IF. Rendezvous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums.");
        } catch (IOException ex) {//l'exception IO va etre gérée 
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel à la création du profil astral", ex);
            resultat = null;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            Message.envoyerMail("contact@predict.if", client.getMail(), "Echec de l’inscription chez PREDICT’IF", "Bonjour " + client.getPrenom() + ", votre inscription au service PREDICT’IF a malencontreusement échoué... Merci de recommencer ultérieurement.");
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return resultat;
    }

    public Client authentifierClient(String mail, String motDePasse) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = client;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Employe authentifierEmploye(String mail, String motDePasse) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Employe employe = empDao.chercherParMail(mail);
            if (employe != null) {
                // Vérification du mot de passe
                if (employe.getMotDePasse().equals(motDePasse)) {
                    resultat = employe;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierEmploye(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public void modifierInfoClient(Client clientModif) {
        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            AstroNetApi astroNet = new AstroNetApi();
            List<String> result = astroNet.getProfil(clientModif.getPrenom(), clientModif.getDateNaissance());
            ProfilAstral profilAstral = new ProfilAstral(result.get(0), result.get(1), result.get(2), result.get(3));
            profilAstralDao.modifier(clientModif.getProfilAstral());
            clientModif = clientDao.modifier(clientModif);
            JpaUtil.validerTransaction();
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service creerConsultation", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public void modifierInfoEmploye(Employe employeModif) {
        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            employeModif = empDao.modifier(employeModif);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service creerConsultation", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public boolean creerConsultation(Client unClient, Medium unMedium) {

        boolean consultationCree = false;
        Consultation uneConsultation = new Consultation(unClient, unMedium);
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            Employe employeDispo;
            employeDispo = empDao.chercherEmployeDispo(unMedium.getGenre());
            if (employeDispo != null && consultationDao.verifierDispoClient(unClient)) {
                consultationDao.creer(uneConsultation);
                uneConsultation.setEmploye(employeDispo);
                employeDispo.setDisponibilite(false);
                empDao.modifier(uneConsultation.getEmploye());
                Message.envoyerNotification(employeDispo.getNumTel(), "Bonjour " + employeDispo.getPrenom() +
                        ". Consultation requise pour " + uneConsultation.getClient().getCivilite() + " " + 
                        uneConsultation.getClient().getPrenom() + " " + uneConsultation.getClient().getNom().toUpperCase()
                        + ". Médium à incarner :\n" + uneConsultation.getMedium().getDenomination());
                JpaUtil.validerTransaction();
                consultationCree=true;
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service creerConsultation", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return consultationCree;

    }
    
    public boolean envoyerConfirmation(Consultation consultation) {
        boolean envoye = false;
        if (consultation.getEtat().equals("en attente")){
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy à HH:mm");
            Message.envoyerNotification(consultation.getClient().getNumTel(), "Bonjour " + consultation.getClient().getPrenom()
                    + ". J’ai bien reçu votre demande de consultation du " + s.format(consultation.getDateDemande())
                    + ". Vous pouvez dès à présent me contacter au " + consultation.getEmploye().getNumTel()
                    + ". A tout de suite ! Médiumiquement vôtre, " + consultation.getMedium().getDenomination());

            JpaUtil.creerContextePersistance();
            try {
                JpaUtil.ouvrirTransaction();
                consultation.setEtat("confirmee");
                consultationDao.modifier(consultation);
                JpaUtil.validerTransaction();
                envoye = true;
            } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service confirmationConsultation", ex);
                JpaUtil.annulerTransaction();
            } finally {
                JpaUtil.fermerContextePersistance();
            }
        }
        return envoye;
    }

    public boolean debuterConsultation(Consultation consultation) {
        boolean debut = false;
        if (consultation.getEtat().equals("confirmee") && consultation.getDateDeb()==null) {
            debut = true;
            JpaUtil.creerContextePersistance();

            try {
                JpaUtil.ouvrirTransaction();
                Date date = new Date();
                consultation.setDateDeb(date);
                consultation.setEtat("en cours");
                consultationDao.modifier(consultation);
                JpaUtil.validerTransaction();

            } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service debuterConsultation", ex);
                JpaUtil.annulerTransaction();
            } finally {
                JpaUtil.fermerContextePersistance();
            }
        }
        return debut;
    }

    public boolean terminerConsultation(Consultation consultation) {
        boolean fin = false;
        if (consultation.getEtat().equals("en cours") && consultation.getDateFin()==null) {
            fin = true;
            JpaUtil.creerContextePersistance();
            try {
                JpaUtil.ouvrirTransaction();
                Date date = new Date();
                consultation.setDateFin(date);
                consultationDao.modifier(consultation);
                JpaUtil.validerTransaction();

            } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service terminerConsultation", ex);
                JpaUtil.annulerTransaction();
            } finally {
                JpaUtil.fermerContextePersistance();
            }
        }
        return fin;
    }
    
    public List<String> creerPredictionAleatoire(Consultation consultation, int amour, int sante, int travail) {
        JpaUtil.creerContextePersistance();
        List<String> predictions = null;
        try {
            AstroNetApi astroNet = new AstroNetApi();
            ProfilAstral profilClient = consultation.getClient().getProfilAstral();
            predictions = astroNet.getPredictions(profilClient.getCouleurBonheur(), profilClient.getCouleurBonheur(), amour, sante, travail);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service confirmationConsultation", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return predictions;
    }

    public void ecrireCommentaire(Consultation consultation, String commentaire) {
        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            consultation.setCommentaire(commentaire);
            consultationDao.modifier(consultation);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service terminerConsultation", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public boolean validerConsultation(Consultation consultation) {
        boolean fini = false;
        if (consultation.getDateDeb() != null && consultation.getDateFin() != null && consultation.getCommentaire() != null) {
            fini = true;
        }

        if (fini) {
            JpaUtil.creerContextePersistance();
            try {
                JpaUtil.ouvrirTransaction();
                consultation.getEmploye().setDisponibilite(true);
                consultation.getEmploye().incrNbConsultations();
                consultation.setEtat("terminee");
                consultationDao.modifier(consultation);
                empDao.modifier(consultation.getEmploye());
                JpaUtil.validerTransaction();

            } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service validerConsultation", ex);
                JpaUtil.annulerTransaction();
            } finally {
                JpaUtil.fermerContextePersistance();
            }
        }
        return fini;
    }
    
    public Consultation recupererConsultCouranteDeEmploye(Employe employe) {
        Consultation laConsultCourante = null;
        JpaUtil.creerContextePersistance();

        try {
            laConsultCourante = consultationDao.recupConsultCourante(employe);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service recupererConsultCouranteDeEmploye", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return laConsultCourante;
    }
    
    public List<Consultation> consulterHistoriqueClient(Client client) {
        List<Consultation> historique = null;
        JpaUtil.creerContextePersistance();

        try {
            historique = consultationDao.listerConsultationsDeClient(client);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service consulterHistoriqueClient", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
            }
            return historique;
    }

    public List<Spirite> listerSpirites() {
        List<Spirite> lesSpirites = null;
        JpaUtil.creerContextePersistance();

        try {
            lesSpirites = medDao.trouverLesSpirites();

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerSpirites", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return lesSpirites;
    }

    public List<Cartomancien> listerCartomanciens() {
        List<Cartomancien> lesCartomanciens = null;
        JpaUtil.creerContextePersistance();

        try {
            lesCartomanciens = medDao.trouverLesCartomanciens();

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerCartomanciens", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return lesCartomanciens;
    }

    public List<Astrologue> listerAstrologues() {
        List<Astrologue> lesAstrologues = null;
        JpaUtil.creerContextePersistance();

        try {
            lesAstrologues = medDao.trouverLesAstrologues();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service ListerAstrologues", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return lesAstrologues;
    }

    public List<Medium> listerTop5Medium() {
        List<Medium> notreTop5 = null;
        JpaUtil.creerContextePersistance();

        try {
            notreTop5 = consultationDao.chercherTop5Medium();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerTop5Medium", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return notreTop5;
    }

    public Map<Employe, Integer> denombrerClientsParEmploye() {
        JpaUtil.creerContextePersistance();
        Map nosCliParEmp = null;

        try {
            nosCliParEmp = consultationDao.compterNbClientparEmploye();
            List<Employe> tousLesEmp = empDao.listerTousLesEmployes();
            for (Employe employe : tousLesEmp) {
                if (!nosCliParEmp.containsKey(employe)) {
                    nosCliParEmp.put(employe, 0);
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service DenombrerClientsParEmploye", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return nosCliParEmp;
    }

    public Map<Medium, Integer> denombrerConsultationsParMedium() {
        JpaUtil.creerContextePersistance();
        Map nosConsulParMed = null;
        try {
            nosConsulParMed = consultationDao.compterNbConsultationParMedium();
            List<Spirite> tousLesSpirites = medDao.trouverLesSpirites();
            for (Spirite spirite : tousLesSpirites) {
                if (!nosConsulParMed.containsKey(spirite)) {
                    nosConsulParMed.put(spirite, 0);
                }
            }
            List<Cartomancien> tousLesCartomanciens = medDao.trouverLesCartomanciens();
            for (Cartomancien cartomancien : tousLesCartomanciens) {
                if (!nosConsulParMed.containsKey(cartomancien)) {
                    nosConsulParMed.put(cartomancien, 0);
                }
            }
            List<Astrologue> tousLesAstrologues = medDao.trouverLesAstrologues();
            for (Astrologue astrologue : tousLesAstrologues) {
                if (!nosConsulParMed.containsKey(astrologue)) {
                    nosConsulParMed.put(astrologue, 0);
                }
            }

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service DenombrerClientsParMedium", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return nosConsulParMed;
    }
    
}
