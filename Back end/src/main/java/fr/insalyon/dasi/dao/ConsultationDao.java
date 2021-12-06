/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;import javax.persistence.TypedQuery;
/**
 *
 * @author natha
 */
public class ConsultationDao {
    public void creer(Consultation consult) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(consult);
    }
    
    public Consultation modifier(Consultation consult) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(consult);
    }

    public Consultation chercherParId(Long consultationId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Consultation.class, consultationId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Consultation recupConsultCourante(Employe unEmploye){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.employe = :unEmploye AND (c.etat='confirmee' OR c.etat='en cours' OR c.etat='en attente')", Consultation.class);
        query.setParameter("unEmploye", unEmploye);
        List<Consultation> consultationsCourantes = query.getResultList();
        Consultation result = null;
        if (!consultationsCourantes.isEmpty()) {
            result = consultationsCourantes.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Medium> chercherTop5Medium(){
        
        EntityManager em = JpaUtil.obtenirContextePersistance();
        List<Object[]> resultats = em.createQuery("SELECT c.medium,count(c) as nbConsultations FROM Consultation c WHERE c.etat='terminee' group by c.medium ORDER BY nbConsultations desc").setMaxResults(5).getResultList();
        List<Medium> top5Mediums=new ArrayList<>();
        for (Object[] result : resultats) {
            Medium leMedium = (Medium) result[0];
            top5Mediums.add(leMedium);      
        }
        if(top5Mediums.isEmpty()){
           top5Mediums=null;
        }
        return top5Mediums;
    }
    
    public Map<Employe,Integer> compterNbClientparEmploye(){ //Ne gère que les employes qui ont eu au moins une consultation                  
        EntityManager em = JpaUtil.obtenirContextePersistance();
        List<Object[]> resultats = em.createQuery("SELECT c.employe,count(c.client) as nbClients FROM Consultation c WHERE c.etat='terminee' group by c.employe").getResultList();
        Map cliParEmp=new HashMap();
        for (Object[] result : resultats) {
            Employe lEmp = (Employe) result[0];
            int leNbDeClients = ((Number) result[1]).intValue();
            cliParEmp.put(lEmp, leNbDeClients);
        }
        
        if(cliParEmp.isEmpty()){
            cliParEmp=null;
        }
        return cliParEmp;
        
    }
    
    public Map<Medium,Integer> compterNbConsultationParMedium(){                  
        EntityManager em = JpaUtil.obtenirContextePersistance();
        List<Object[]> resultats = em.createQuery("SELECT c.medium,count(c) as nbConsultations FROM Consultation c WHERE c.etat='terminee' group by c.medium").getResultList();
        Map consulParMed=new HashMap();
        for (Object[] result : resultats) {
            Medium med = (Medium) result[0];
            int leNbDeConsul = ((Number) result[1]).intValue();
            consulParMed.put(med, leNbDeConsul);
        }
        
        if(consulParMed.isEmpty()){
            consulParMed=null;
        }
        return consulParMed;
        
    }   
    
    public List<Consultation> listerConsultationsDeClient(Client unClient){                  
        EntityManager em = JpaUtil.obtenirContextePersistance();
       
        TypedQuery<Consultation> query = em.createQuery( "SELECT c FROM Consultation c WHERE c.client=:unClient AND c.etat='terminee'", Consultation.class);
        query.setParameter("unClient", unClient); 
        
        List<Consultation> consultParClient = query.getResultList();
        if (consultParClient.isEmpty()) {
            consultParClient = null; 
        }
        return consultParClient;
    }
    
    public boolean verifierDispoClient(Client unClient){  
        //un client ne peut pas demander une consultation s'il a déjà une autre consultation en attente ou en cours
        EntityManager em = JpaUtil.obtenirContextePersistance();
        boolean clientDispo = false;
       
        TypedQuery<Consultation> query = em.createQuery( "SELECT c FROM Consultation c WHERE c.client=:unClient AND c.etat!='terminee'", Consultation.class);
        query.setParameter("unClient", unClient); 
        
        List<Consultation> consultParClient = query.getResultList();
        if (consultParClient.isEmpty()) {
            clientDispo = true;
        }
        return clientDispo;
    }
    
}
