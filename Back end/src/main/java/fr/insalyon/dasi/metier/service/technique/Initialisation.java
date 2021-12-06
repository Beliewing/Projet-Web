/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.service.technique;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natha
 */
public class Initialisation {
    
    
    public static void InitClients() throws ParseException{
        SimpleDateFormat simpleDate=new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = simpleDate.parse("10/12/1815");
        ClientDao cliDao=new ClientDao();
        Client cli0=new Client("SING","Ainhoa","asing8183@free.fr","mdp","0705224200",dateNaissance,"Mme");
        Client cli1=new Client("RINERD","Julien","julien8183@free.fr","mdp","0727252485",dateNaissance,"M.");
        Client cli2=new Client("SING","Olivier","olivier8183@free.fr","mdp","0727252486",dateNaissance,"M.");
        Client cli3=new Client("SING","Alice","aalice8183@free.fr","mdp","0705224201",dateNaissance,"Mme");
        
        Service service = new Service();
        service.inscrireClient(cli0);
        service.inscrireClient(cli1);
        service.inscrireClient(cli2);
        service.inscrireClient(cli3);
        
    }
    public static void InitEmployes(){
        EmployeDao empDao=new EmployeDao();
        Employe emp0=new Employe("Martin","Camille","camille.martin@predictif.fr","mdp","0655447788",'F');
        Employe emp1=new Employe("Potter","Harry","harry.potter@predictif.fr","mdp","1234567890",'H');
        Employe emp2=new Employe("Unlu","Adrien","adrien.unlu@predictif.fr","mdp","1234567891",'H');
        Employe emp3=new Employe("Gael","Louise","louise.gael@predictif.fr","mdp","1234567891",'F');
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            empDao.creer(emp0);
            empDao.creer(emp1);
            empDao.creer(emp2);
            empDao.creer(emp3);
            JpaUtil.validerTransaction();
        }catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service InitEmployes", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public static void InitMedium(){
        MediumDao medDao=new MediumDao();
        Cartomancien med0=new Cartomancien("Mme Irma",'F',"Comprenez votre entourage grace à mes cartes! Résultats rapides.");
        Spirite med1=new Spirite("Boule de cristal","Gwenaelle",'F',"Spécialiste des grandes conversations au dela de TOUTES les frontières.");
        Astrologue med2=new Astrologue("Ecole Normale Supérieure d'Astrologie (ENS-ASTRO)","2006","Serena",'F',"Basée à Champigny sur Marne, Serena vous revelera votre avenir pour eclairer votre passé.");
        Cartomancien med3=new Cartomancien("Endora",'F',"Mes cartes répondront à toutes vos questions personnelles.");
        Spirite med4=new Spirite("Marc de café, boule de cristal, oreilles de lapin","Professeur Tran",'H',"Votre avenir est devant vous: regardons-le ensemble!");
        Astrologue med5=new Astrologue("Institut des Nouveaux Savoirs Astrologiques","2010","Mr M",'H',"Avenir, avenir que nous réserves-tu? N'attendez plus, demandez à me consulter!");
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            medDao.creer(med0);
            medDao.creer(med1);
            medDao.creer(med2);
            medDao.creer(med3);
            medDao.creer(med4);
            medDao.creer(med5);
            JpaUtil.validerTransaction();
        }catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service InitEmployes", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public static void InitConsultations(){
        
        Service service = new Service();
        service.creerConsultation(service.rechercherClientParId(Long.valueOf(1)),service.chercherMediumParId(Long.valueOf(1)));
        Consultation c1 = service.chercherConsultationParId(Long.valueOf(1));
        service.envoyerConfirmation(c1);
        service.debuterConsultation(c1);
        service.terminerConsultation(c1);
        service.ecrireCommentaire(c1,"Très malchanceuse");
        service.validerConsultation(c1);
                
        service.creerConsultation(service.rechercherClientParId(Long.valueOf(2)),service.chercherMediumParId(Long.valueOf(1)));
        Consultation c2 = service.chercherConsultationParId(Long.valueOf(2));
        service.envoyerConfirmation(c2);
        service.debuterConsultation(c2);
        service.terminerConsultation(c2);
        service.ecrireCommentaire(c2,"Bidon");
        service.validerConsultation(c2);       
        
        service.creerConsultation(service.rechercherClientParId(Long.valueOf(3)),service.chercherMediumParId(Long.valueOf(1)));
        Consultation c3 = service.chercherConsultationParId(Long.valueOf(3));
        service.envoyerConfirmation(c3);
        service.debuterConsultation(c3);
        service.terminerConsultation(c3);
        service.ecrireCommentaire(c3,"Crédule !");
        service.validerConsultation(c3);     
        
        service.creerConsultation(service.rechercherClientParId(Long.valueOf(1)),service.chercherMediumParId(Long.valueOf(2)));
        Consultation c4 = service.chercherConsultationParId(Long.valueOf(4));
        service.envoyerConfirmation(c4);
        service.debuterConsultation(c4);
        service.terminerConsultation(c4);
        service.ecrireCommentaire(c4,"vive l'argent gratuit");
        service.validerConsultation(c4);     
        
        service.creerConsultation(service.rechercherClientParId(Long.valueOf(2)),service.chercherMediumParId(Long.valueOf(2)));
        Consultation c5 = service.chercherConsultationParId(Long.valueOf(5));
        service.envoyerConfirmation(c5);
        service.debuterConsultation(c5);
        service.terminerConsultation(c5);
        service.ecrireCommentaire(c5,"tous des moutons");
        service.validerConsultation(c5);   
        
        service.creerConsultation(service.rechercherClientParId(Long.valueOf(4)),service.chercherMediumParId(Long.valueOf(6)));
        Consultation c6 = service.chercherConsultationParId(Long.valueOf(6));
        service.envoyerConfirmation(c6);
        service.debuterConsultation(c6);
        service.terminerConsultation(c6);
        service.ecrireCommentaire(c6,"déception totale");
        service.validerConsultation(c6);   
        
        service.creerConsultation(service.rechercherClientParId(Long.valueOf(3)),service.chercherMediumParId(Long.valueOf(3)));
        Consultation c7 = service.chercherConsultationParId(Long.valueOf(7));
        service.envoyerConfirmation(c7);
        service.debuterConsultation(c7);
        service.terminerConsultation(c7);
        service.ecrireCommentaire(c7,"à quand les vacances?");
        service.validerConsultation(c7);   
        
        service.creerConsultation(service.rechercherClientParId(Long.valueOf(1)),service.chercherMediumParId(Long.valueOf(4)));
        Consultation c8 = service.chercherConsultationParId(Long.valueOf(8));
        service.envoyerConfirmation(c8);
        service.debuterConsultation(c8);
        service.terminerConsultation(c8);
        service.ecrireCommentaire(c8,"On veut une bonne note :)");
        service.validerConsultation(c8);   
        
        service.creerConsultation(service.rechercherClientParId(Long.valueOf(1)),service.chercherMediumParId(Long.valueOf(4)));
        service.creerConsultation(service.rechercherClientParId(Long.valueOf(2)),service.chercherMediumParId(Long.valueOf(3)));
        
    }
}

