/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author natha
 */
@Entity
public class Astrologue extends Medium{
    private String formation;
    private String promotion;
    
    //Constructeurs
    public Astrologue() {
    }

    public Astrologue(String formation, String promotion, String denomination, char genre, String presentation) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }
    
    //Getters
    public String getFormation() {
        return formation;
    }

    public String getPromotion() {
        return promotion;
    }

    //Setters
    public void setFormation(String formation) {
        this.formation = formation;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
    
    //Méthode de l'affichage
    @Override
    public String toString() {
        return "Astrologue{ : id=" + id +", denomination="+ denomination+ ", genre=" + genre + ", presentation=" + presentation+ ", formation=" + formation+ ", promotion=" + promotion+"}";
    }
}
