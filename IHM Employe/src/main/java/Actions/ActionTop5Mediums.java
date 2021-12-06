/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionTop5Mediums extends Action {

    public ActionTop5Mediums(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        List<Medium> listeTop5Mediums = service.listerTop5Medium();
        if (listeTop5Mediums != null) {
            System.out.println("Récupération de la liste des 5 meilleurs médiums réussie");
            request.setAttribute("succes", true);
            request.setAttribute("listeTop5Mediums", listeTop5Mediums);
        } else {
            System.out.println("Echec de la Récupération de la liste des 5 meilleurs médiums");
            request.setAttribute("succes", false);
        }

    }

}
