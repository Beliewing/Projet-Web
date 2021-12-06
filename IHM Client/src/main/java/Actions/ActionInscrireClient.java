/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Actions.Action;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import fr.insalyon.dasi.metier.modele.Client;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migoz
 */
public class ActionInscrireClient extends Action{
    public ActionInscrireClient(Service service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        String civilite = request.getParameter("civilite");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String mail = request.getParameter("mail");
        String date = request.getParameter("dateNaissance");
        String tel = request.getParameter("tel");
        String mdp = request.getParameter("mdp");
        String confMdp = request.getParameter("confMdp");
        
        SimpleDateFormat simpleDate=new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = null;
        try {
            dateNaissance = simpleDate.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ActionInscrireClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Long id = null;
        if(mdp.equals(confMdp)){
            Client cnew = new Client(nom,prenom,mail,mdp,tel,dateNaissance,civilite);
            id = service.inscrireClient(cnew);
            System.out.println("----Client crée----"+cnew);
        }
        
        if(id != null){
            System.out.println("Inscription réussie");
            request.setAttribute("succes",true);
        }else{
            System.out.println("Echec inscription");
            request.setAttribute("succes",false);
        }
        
    }
}
