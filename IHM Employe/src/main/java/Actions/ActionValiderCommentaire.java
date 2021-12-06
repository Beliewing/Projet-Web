/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author migoz
 */
public class ActionValiderCommentaire extends Action{
    
    public ActionValiderCommentaire(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        String commentaire= request.getParameter("commentaire");
        
        Consultation consultEnCours=service.recupererConsultCouranteDeEmploye((Employe)request.getSession().getAttribute("employe"));
        service.ecrireCommentaire(consultEnCours,commentaire);
        request.setAttribute("succes",true);
        
    }
}
