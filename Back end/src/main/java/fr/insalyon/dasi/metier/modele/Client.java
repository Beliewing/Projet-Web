package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DASI Team
 */
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String civilite;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private String numTel;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private ProfilAstral profilAstral;

    
    //Constructeurs
    public Client() {
    }

    public Client(String nom, String prenom, String mail, String motDePasse,String num,Date date,String uneCivilite) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.numTel=num;
        this.dateNaissance=date;
        this.profilAstral=null;
        this.civilite=uneCivilite;
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
    
    public String getCivilite() {
        return civilite;
    }
    
    public String getMotDePasse() {
        return motDePasse;
    }
    
    public String getNumTel() {
        return numTel;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }
    
    
    //Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }
    
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }
    
    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

     public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    //Méthode pour l'affichage
    @Override
    public String toString() {
        return "Client : id=" + id +", civilite="+ civilite+ ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motDePasse=" + motDePasse;
    }
    
}
