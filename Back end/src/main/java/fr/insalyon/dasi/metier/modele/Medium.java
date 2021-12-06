/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author natha
 */
@Entity 
@Inheritance(strategy=InheritanceType.JOINED)
public class Medium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String denomination;
    protected char genre;
    protected String presentation;

    //Constructeurs
    public Medium() {
    }

    public Medium(String denomination, char genre, String presentation) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
    }
    
    //Getters
    public String getDenomination() {
        return denomination;
    }

    public char getGenre() {
        return genre;
    }

    public String getPresentation() {
        return presentation;
    }

    public Long getId() {
        return id;
    }
        
    //Setters
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
    
    //Méthode pour l'affichage
    @Override
    public String toString() {
        return "Medium : id=" + id +", denomination="+ denomination+ ", genre=" + genre + ", presentation=" + presentation;
    }
    
}
