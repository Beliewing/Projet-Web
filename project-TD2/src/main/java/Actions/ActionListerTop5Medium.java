/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionListerTop5Medium extends Action{

    public ActionListerTop5Medium(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("execute en marche");
        
        List<Medium> mediums = service.listerTop5Medium();
        if(mediums != null){
            System.out.println("Récupération du top 5 mediums réussie");
            request.setAttribute("mediums",mediums);
        }else{
            System.out.println("Echec récupération du top 5 médiums");
        }
        
    }
}
