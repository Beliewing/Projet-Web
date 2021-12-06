/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionObtenirConsultationEnCours extends Action{

    public ActionObtenirConsultationEnCours(Service service) {
        super(service);
    }
    
     @Override
    public void execute(HttpServletRequest request) {
        //if (request.getSession().getAttribute("consultEnCours")==null){
        Consultation consultation = service.recupererConsultCouranteDeEmploye((Employe)request.getSession().getAttribute("employe"));
        System.out.println(consultation);
        if(consultation != null){
            System.out.println("Récupération de la consultation réussie");
            request.setAttribute("consultEnCours",consultation);
            //request.getSession().setAttribute("consultEnCours",consultation);
        }else{
            System.out.println("Echec récupération de la consultation");
            request.setAttribute("consultEnCours",null);
            //request.getSession().setAttribute("consultEnCours",null);
        }
        //}
    }
    
}
