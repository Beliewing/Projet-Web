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

/**
 *
 * @author natha
 */
@Entity
public class ProfilAstral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String signeZodiac;
    private String signeChinois;
    private String couleurBonheur;
    private String animalTotem;
    
    //Constructeurs
    public ProfilAstral() {
        this.signeZodiac = "";
        this.signeChinois = "";
        this.couleurBonheur = "";
        this.animalTotem = "";
    }

    public ProfilAstral(String signeZodiac, String signeChinois, String couleurBonheur, String animalTotem) {
        this.signeZodiac = signeZodiac;
        this.signeChinois = signeChinois;
        this.couleurBonheur = couleurBonheur;
        this.animalTotem = animalTotem;
    }
    
    //Getters
    public Long getId() {
        return id;
    }

    public String getSigneZodiac() {
        return signeZodiac;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleurBonheur() {
        return couleurBonheur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }
    
    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setSigneZodiac(String signeZodiac) {
        this.signeZodiac = signeZodiac;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setCouleurBonheur(String couleurBonheur) {
        this.couleurBonheur = couleurBonheur;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }


    //Méthode qui permet de copier les attributs d'un autre profil Astral dans le profil Astral qu'on veut modifier
    public void copie(ProfilAstral unAutreProfilAstral){
        this.signeZodiac = unAutreProfilAstral.getSigneZodiac();
        this.signeChinois = unAutreProfilAstral.getSigneChinois();
        this.couleurBonheur = unAutreProfilAstral.getCouleurBonheur();
        this.animalTotem = unAutreProfilAstral.getAnimalTotem();

    }
}
