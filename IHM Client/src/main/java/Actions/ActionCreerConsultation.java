/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionCreerConsultation extends Action{

    public ActionCreerConsultation(Service service) {
        super(service);
    }
    
     @Override
    public void execute(HttpServletRequest request) {
        System.out.println("execute en marche");
        Client monClient=(Client)request.getSession().getAttribute("user");
        Medium notreMedium=service.chercherMediumParId(Long.parseLong(request.getParameter("medium")));
         System.out.println(monClient);
         System.out.println(notreMedium);
        boolean success = service.creerConsultation(monClient,notreMedium);
         System.out.println(success);
        if(success != false){
            System.out.println("création de consultation réussie");
            request.setAttribute("medium",notreMedium);
            request.setAttribute("succes", success);
        }else{
            request.setAttribute("succes", success);
            System.out.println("Echec de création de consultation");
        }
    }
    
}
