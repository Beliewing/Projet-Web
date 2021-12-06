/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionInfosClient extends Action{

    public ActionInfosClient(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("---Action InfosClients");
        Client monClient=(Client)request.getSession().getAttribute("user");
        System.out.println(monClient);
        if(monClient != null){
            System.out.println("Récupération du client réussie");
            request.setAttribute("infosClient",monClient);
        }else{
            System.out.println("Echec récupération du client");
        }

        
    }
    
}
