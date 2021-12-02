/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionNbConsulMedium extends Action {

    public ActionNbConsulMedium(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Map<Medium, Integer> listeNbConsulMedium = service.denombrerConsultationsParMedium();
        if (listeNbConsulMedium != null) {
            System.out.println("Récupération de la liste de nombre du consultations par medium réussie");
            request.setAttribute("succes", true);
            request.setAttribute("listeNbConsulMedium", listeNbConsulMedium);
        } else {
            System.out.println("Echec de la récupération de la liste du nombre de consultations par medium");
            request.setAttribute("succes", false);
        }

    }

}
