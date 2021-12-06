/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author migoz
 */
public class ActionAuthentifierEmploye extends Action{
    
    public ActionAuthentifierEmploye(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        String login= request.getParameter("login");
        String password= request.getParameter("password");
        
        Employe e = service.authentifierEmploye(login, password);
        System.out.println("----Employe authentifié----"+e);
        
        if(e != null){
            System.out.println("connexion réussie");
            request.setAttribute("succes",true);
            HttpSession session = request.getSession(true);
            session.setAttribute("employe",e);
        }else{
            System.out.println("Echec connexion");
            request.setAttribute("succes",false);
        }
        
    }
}
