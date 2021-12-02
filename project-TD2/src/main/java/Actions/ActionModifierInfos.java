/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Client;
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
        Client monClient = (Client) request.getSession().getAttribute("user");
        monClient.setNom(request.getParameter("nom"));
        monClient.setPrenom(request.getParameter("prenom"));
        monClient.setNumTel(request.getParameter("tel"));
        monClient.setMail(request.getParameter("mail"));

        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date nvDate = simpleDate.parse(request.getParameter("dateNaissance"));
            monClient.setDateNaissance(nvDate);
        }catch(Exception e){
            System.err.println("Erreur lors de la conversion en date.");
        }
        service.modifierInfoClient(monClient);
        System.out.println("nv client "+ monClient);
        System.out.println("Modification du client réussie.");
        request.setAttribute("infosClient",monClient);

    }
}
