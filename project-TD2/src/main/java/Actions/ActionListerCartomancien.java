/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionListerCartomancien extends Action{

    public ActionListerCartomancien(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("execute en marche");
        
        List<Cartomancien> cartomanciens = service.listerCartomanciens();
                
        if(cartomanciens != null){
            System.out.println("Récupération des cartomanciens réussie");
            request.setAttribute("cartomanciens",cartomanciens);
        }else{
            System.out.println("Echec récupération des cartomanciens");
        }
        
    }
    
}
