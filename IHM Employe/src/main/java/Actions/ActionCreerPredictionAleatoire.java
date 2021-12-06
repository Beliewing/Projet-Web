/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author migoz
 */
public class ActionCreerPredictionAleatoire extends Action{
    
    public ActionCreerPredictionAleatoire(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        try {
            int amour = Integer.parseInt(request.getParameter("amour"));
            int sante = Integer.parseInt(request.getParameter("sante"));
            int travail = Integer.parseInt(request.getParameter("travail"));

            if (1<=amour && amour<=4 && 1<=sante && sante<=4 && 1<=travail && travail<=4){
                Consultation consultEnCours=service.recupererConsultCouranteDeEmploye((Employe)request.getSession().getAttribute("employe"));
                List<String> predictions = service.creerPredictionAleatoire(consultEnCours,amour,sante,travail);
                System.out.println();
                if (!predictions.isEmpty()){
                    request.setAttribute("succes",true);
                    request.setAttribute("predictions",predictions);
                } else {
                    request.setAttribute("succes",false);
                }
            } else {
                request.setAttribute("succes",false);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("succes",false);
        }
        
    }
}
