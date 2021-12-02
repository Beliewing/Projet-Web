/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionConsulterHistoriqueClient extends Action{

    public ActionConsulterHistoriqueClient(Service service) {
        super(service);
    }
    
     @Override
    public void execute(HttpServletRequest request) {
        System.out.println("execute en marche");
        Client monClient=(Client)request.getSession().getAttribute("user");
        System.out.println(monClient);
        List<Consultation> consultations = service.consulterHistoriqueClient(monClient);
        if(consultations != null){
            System.out.println("Récupération des consultations réussie");
            request.setAttribute("historique",consultations);
            request.setAttribute("historiqueExistant",true);
        }else{
            System.out.println("Pas de consultations");
            request.setAttribute("historiqueExistant",false);
        }
    }
    
}
