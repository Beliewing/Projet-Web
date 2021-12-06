/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author migoz
 */
public class ActionAfficherEmploye extends Action{
    
    public ActionAfficherEmploye(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        Employe e = (Employe) request.getSession().getAttribute("employe");
        e = service.rechercherEmployeParId(e.getId());
        if(e != null){
            request.setAttribute("succes",true);
            request.getSession().setAttribute("employe",e);
        }else{
            System.out.println("Echec info employe");
            request.setAttribute("succes",false);
        }
        
    }
}
