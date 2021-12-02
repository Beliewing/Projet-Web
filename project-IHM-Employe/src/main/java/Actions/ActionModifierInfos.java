/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionModifierInfos extends Action {

    public ActionModifierInfos(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        System.out.println("execute en marche");
        Employe monEmploye = (Employe) request.getSession().getAttribute("employe");
        monEmploye.setNom(request.getParameter("nom"));
        monEmploye.setPrenom(request.getParameter("prenom"));
        monEmploye.setGenre(request.getParameter("genre").charAt(0));
        monEmploye.setNumTel(request.getParameter("tel"));
        monEmploye.setMail(request.getParameter("mail"));

        service.modifierInfoEmploye(monEmploye);
        System.out.println("nv employe "+ monEmploye);
        System.out.println("Modification du client réussie.");
        request.setAttribute("employe",monEmploye);

    }
}
