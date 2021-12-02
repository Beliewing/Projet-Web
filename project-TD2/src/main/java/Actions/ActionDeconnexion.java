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
 * @author natha
 */
public class ActionDeconnexion extends Action{

    public ActionDeconnexion(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
            request.setAttribute("succes",true);
            request.getSession().removeAttribute("user");
            request.getSession().invalidate();
    }
}

