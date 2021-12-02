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
 * @author migoz
 */
public class ActionDebuterAppel extends Action{
    
    public ActionDebuterAppel(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {        
        Consultation consultEnCours=service.recupererConsultCouranteDeEmploye((Employe)request.getSession().getAttribute("employe"));
        boolean test = service.debuterConsultation(consultEnCours);
        if (test){
            request.setAttribute("succes",true);
            request.setAttribute("heure",consultEnCours.getDateDeb());
            
        } else {
            request.setAttribute("succes",false);
        }
        
    }
}
