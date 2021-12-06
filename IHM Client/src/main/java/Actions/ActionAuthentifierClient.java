/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author migoz
 */
public class ActionAuthentifierClient extends Action{
    
    public ActionAuthentifierClient(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("execute en marche");
        String login= request.getParameter("login");
        String password= request.getParameter("password");
        HttpSession session = request.getSession ( true ); // Initialisation Session
        
        Client c = service.authentifierClient(login, password);
        System.out.println("----Client authentifié----"+c);
        
        if(c != null){
            System.out.println("connexion réussie");
            request.setAttribute("client",c);
            session.setAttribute ("user", c );
            request.setAttribute("succes",true);
        }else{
            System.out.println("Echec connexion");
            request.setAttribute("succes",false);
        }
        
    }
}
