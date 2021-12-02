/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author natha
 */
@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private String numTel;
    private char genre;
    private boolean disponibilite=true;
    private int nbConsultations;
    
    
    //Constructeurs
    public Employe()  {
    }

    public Employe(String nom, String prenom, String mail, String motDePasse, String numTel, char genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.numTel = numTel;
        this.genre = genre;
        this.nbConsultations=0;
    }
    
    //Getters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNumTel() {
        return numTel;
    }

    public char getGenre() {
        return genre;
    }

    public boolean getDisponibilite() {
        return disponibilite;
    }
    
    public int getNbConsultations() {
        return nbConsultations;
    }
    
    //Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    public void setNbConsultations(int nbConsultations) {
        this.nbConsultations = nbConsultations;
    }
    
    //Méthode qui incrémente de 1 le nbConsultations
    public void incrNbConsultations() {
        this.nbConsultations++;
    }

    //Méthode pour l'affichage
    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motDePasse=" + motDePasse + ", numTel=" + numTel + ", genre=" + genre + ", disponibilite=" + disponibilite + '}';
    }

}
