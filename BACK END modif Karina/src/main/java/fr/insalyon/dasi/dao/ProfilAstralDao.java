/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.ProfilAstral;
import javax.persistence.EntityManager;

/**
 *
 * @author natha
 */
public class ProfilAstralDao {
   
    public static void creer(ProfilAstral profil) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(profil);
    }
    
    public ProfilAstral modifier(ProfilAstral profil) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(profil);
    }
    
    public ProfilAstral chercherParId(Long profilId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(ProfilAstral.class, profilId); // renvoie null si l'identifiant n'existe pas
    }
}