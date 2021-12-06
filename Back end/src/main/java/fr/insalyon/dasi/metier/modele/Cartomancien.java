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
public class Cartomancien extends Medium{
    
    //Constructeurs
    public Cartomancien() {
    }

    public Cartomancien(String denomination, char genre, String presentation) {
        super(denomination, genre, presentation);
    }
    
    //Méthode pour l'affichage
    @Override
    public String toString() {
        return "Cartomancien{ : id=" + id +", denomination="+ denomination+ ", genre=" + genre + ", presentation=" + presentation+"}";
    }
}
