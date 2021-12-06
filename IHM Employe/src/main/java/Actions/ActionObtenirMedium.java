/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionObtenirMedium extends Action{

    public ActionObtenirMedium(Service service) {
        super(service);
    }
    
     @Override
    public void execute(HttpServletRequest request) {
        Consultation consult = service.recupererConsultCouranteDeEmploye((Employe)request.getSession().getAttribute("employe"));
        if (consult!=null){
            Medium medium = consult.getMedium();
            System.out.println(medium);
            request.setAttribute("medium",medium);
            request.setAttribute("succes",true);
       }else{
            System.out.println("Echec");
            request.setAttribute("succes",false);
        }
    }
    
}
