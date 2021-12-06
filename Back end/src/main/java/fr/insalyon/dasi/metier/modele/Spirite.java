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
public class Spirite extends Medium{
    private String support;

    //Constructeurs
    public Spirite() {
    }

    public Spirite(String support, String denomination, char genre, String presentation) {
        super(denomination, genre, presentation);
        this.support = support;
    }
    
    //Getter
    public String getSupport() {
        return support;
    }

    //Setter
    public void setSupport(String support) {
        this.support = support;
    }

    //Méthode pour l'affichage
    @Override
    public String toString() {
        return "Spirite{ : id=" + id +", denomination="+ denomination+ ", genre=" + genre + ", presentation=" + presentation+ ", support=" + support+"}";
    }
 
}
