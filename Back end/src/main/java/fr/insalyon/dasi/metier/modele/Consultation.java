/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author natha
 */
@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDemande;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeb;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    private String commentaire;
    private String etat;//en attente, confirmee, en cours, terminee
    private Client client;
    private Employe employe;
    private Medium medium;

    //Constructeurs
    public Consultation() {
        /*SimpleDateFormat formatDate=new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatHeure=new SimpleDateFormat("HH:mm");*/ //à utiliser dans l'affichage
        //dateDeb=new Date();
        /*date = formatDate.;
        heure=formatHeure.parse();*/
    }
    
    public Consultation(Client unClient, Medium unMedium){
        client=unClient;
        medium=unMedium;
        etat="en attente";
        dateDemande = new Date();
    }
    
    //Getters
    public Long getId() {
        return id;
    }

    public String getCommentaire() {
        return commentaire;
    }
    
    public String getEtat() {
        return etat;
    }
    
    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Medium getMedium() {
        return medium;
    }
    public Date getDateDeb() {
        return dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public Date getDateDemande() {
        return dateDemande;
    }
    
    //Setters
    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }    
    
    //Méthode pour l'affichage
    @Override
    public String toString() {
        return "Consultation : id=" + id +", date de demande="+ dateDemande+ ", date de début =" + dateDeb + ", date de fin=" + dateFin + ", commentaire=" + commentaire + ", état =" + etat+ ", client=" + client + ", employé =" + employe+ ", medium =" + medium;
    }
}
