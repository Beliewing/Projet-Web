/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionListerSpirite extends Action{

    public ActionListerSpirite(Service service) {
        super(service);
    }    
    
    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("execute en marche");
        
        List<Spirite> spirites = service.listerSpirites();
                
        if(spirites != null){
            System.out.println("Récupération des spirites réussie");
            request.setAttribute("spirites",spirites);
        }else{
            System.out.println("Echec récupération des spirites");
        }
        
    }
}
