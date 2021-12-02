/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author natha
 */
public class EmployeDao {

    public void creer(Employe emp) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(emp);
    }
    
    public Employe modifier(Employe emp) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(emp);
    }
    
    public Employe chercherParId(Long employeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Employe.class, employeId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Employe chercherParMail(String mail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.mail = :mail", Employe.class);

        query.setParameter("mail", mail); // correspond au paramètre ":mail" dans la requête
        List<Employe> employes = query.getResultList();//si mail unique getSingleResult() qui renvoie un client

        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0);//nous donne l'employé qui a réalisé le moins de consultations pour équilibrer
        }
        return result;
    }
    
    public Employe chercherEmployeDispo(char unGenre) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.disponibilite = true AND e.genre= :genre order by e.nbConsultations asc", Employe.class);
        query.setParameter("genre", unGenre);
        List<Employe> employes = query.getResultList();
        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0);//nous donne l'employé qui a réalisé le moins de consultations pour équilibrer
        }
        return result;
    }

    public List<Employe> listerTousLesEmployes() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e", Employe.class);
        List<Employe> employes = query.getResultList();
        if (employes.isEmpty()) {
            employes = null;
        }
        return employes;

    }

}
