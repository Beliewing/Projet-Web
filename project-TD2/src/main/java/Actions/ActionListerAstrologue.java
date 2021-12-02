/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionListerAstrologue extends Action{

    public ActionListerAstrologue(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("execute en marche");
        
        List<Astrologue> astrologues = service.listerAstrologues();
        if(astrologues != null){
            System.out.println("Récupération des astrologues réussie");
            request.setAttribute("astrologues",astrologues);
        }else{
            System.out.println("Echec récupération des astrologues");
        }
        
    }
}
