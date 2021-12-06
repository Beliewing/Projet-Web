/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;


import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author natha
 */
public class MediumDao {
    
    public void creer(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(medium);
    }
    
    public Medium chercherParId(Long mediumId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Medium.class, mediumId); // renvoie null si l'identifiant n'existe pas
    }
      
     public List<Spirite> trouverLesSpirites(){
         
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Spirite> query = em.createQuery("SELECT s FROM Spirite s", Spirite.class);
        List<Spirite> spirites = query.getResultList();
        if(spirites.isEmpty()){
            spirites = null;
        }
        return spirites;
    }
     public List<Cartomancien> trouverLesCartomanciens(){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Cartomancien> query = em.createQuery("SELECT c FROM Cartomancien c", Cartomancien.class);
        List<Cartomancien> cartomanciens = query.getResultList();
        if(cartomanciens.isEmpty()){
            cartomanciens = null;
        }
        return cartomanciens;
        
    }
    public List<Astrologue> trouverLesAstrologues(){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Astrologue> query = em.createQuery("SELECT a FROM Astrologue a", Astrologue.class);
        List<Astrologue> astrologues = query.getResultList();
        if(astrologues.isEmpty()){
            astrologues = null;
        }
        return astrologues;
    }
     
}
