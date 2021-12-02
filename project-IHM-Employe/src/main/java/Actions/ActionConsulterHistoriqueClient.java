/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author natha
 */
public class ActionConsulterHistoriqueClient extends Action{

    public ActionConsulterHistoriqueClient(Service service) {
        super(service);
    }
    
     @Override
    public void execute(HttpServletRequest request) {
        System.out.println("Consulter Historique Client");
        Consultation consult = service.recupererConsultCouranteDeEmploye((Employe)request.getSession().getAttribute("employe"));
        System.out.println("xx");
        if (consult!=null){
            Client monClient = consult.getClient();
            System.out.println(monClient);
            List<Consultation> consultations = service.consulterHistoriqueClient(monClient);
            if(consultations != null){
                System.out.println("Récupération des consultations réussie");
                request.setAttribute("liste",true);
                request.setAttribute("historique",consultations);
            }else{
                System.out.println("Echec récupération des consultations");
                request.setAttribute("liste",false);
            }
        } else {
            request.setAttribute("liste",false);
        }
    }
    
}
