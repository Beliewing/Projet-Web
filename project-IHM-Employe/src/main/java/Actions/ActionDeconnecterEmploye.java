/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author migoz
 */
public class ActionDeconnecterEmploye extends Action{
    
    public ActionDeconnecterEmploye(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
            request.setAttribute("succes",true);
            request.getSession().removeAttribute("employe");
            request.getSession().removeAttribute("consultEnCours");
            request.getSession().removeAttribute("consultation");
            request.getSession().invalidate();      
    }
}
