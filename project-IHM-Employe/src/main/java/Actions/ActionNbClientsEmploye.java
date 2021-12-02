/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionNbClientsEmploye extends Action {

    public ActionNbClientsEmploye(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Map<Employe, Integer> listeNbClientsEmploye = service.denombrerClientsParEmploye();

        if (listeNbClientsEmploye != null) {
            System.out.println("Récupération de la liste de nombre du clients par employe réussie");
            request.setAttribute("succes", true);
            request.setAttribute("listeNbClientsEmploye", listeNbClientsEmploye);
        } else {
            System.out.println("Echec de la récupération de la liste de nombre du clients par employe");
            request.setAttribute("succes", false);
        }

    }

}
