/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author natha
 */
public class SerialisationConsulterHistoriqueClient extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();      
        SimpleDateFormat simpleDate=new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat simpleHeure=new SimpleDateFormat("HH:mm");
        JsonArray jsonListeConsultations = new JsonArray();
        if ((boolean)request.getAttribute("historiqueExistant")){
            List<Consultation> historique = (List<Consultation>) request.getAttribute("historique");
            for (Consultation c : historique) {
                JsonObject jsonConsult = new JsonObject();
                jsonConsult.addProperty("id", c.getId());
                
                jsonConsult.addProperty("etat", c.getEtat());
                
                String dateTemp = simpleDate.format(c.getDateDemande());
                jsonConsult.addProperty("dateDemande", dateTemp );
                
                if (c.getDateDeb()!=null){
                    dateTemp = simpleDate.format(c.getDateDeb());
                    jsonConsult.addProperty("dateDeb",dateTemp);
                } else {
                    jsonConsult.addProperty("dateDeb","");
                }
                if (c.getDateDeb()!=null){
                    dateTemp = simpleHeure.format(c.getDateDeb());
                    jsonConsult.addProperty("heureDeb",dateTemp);
                }else {
                    jsonConsult.addProperty("heureDeb","");
                }
                if (c.getDateFin()!=null){
                    dateTemp = simpleHeure.format(c.getDateFin());
                    jsonConsult.addProperty("heureFin",dateTemp);
                }else {
                    jsonConsult.addProperty("heureFin","");
                }
                
                if (c.getCommentaire()!=null){
                    jsonConsult.addProperty("commentaire", c.getCommentaire());
                }else {
                    jsonConsult.addProperty("commentaire", "");
                }
                
                
                //on prend aussi les informations du client dans le json de la consultation
                JsonObject jsonClient = new JsonObject();
                jsonClient.addProperty("id", c.getClient().getId());
                jsonClient.addProperty("nom", c.getClient().getNom());
                jsonClient.addProperty("prenom", c.getClient().getPrenom());
                jsonClient.addProperty("mail", c.getClient().getMail());
                jsonConsult.add("client", jsonClient);

                //on prend aussi les informations de l'employe dans le json de la consultation
                JsonObject jsonEmp = new JsonObject();
                jsonEmp.addProperty("id", c.getEmploye().getId());
                jsonEmp.addProperty("nom", c.getEmploye().getNom());
                jsonEmp.addProperty("prenom", c.getEmploye().getPrenom());
                jsonEmp.addProperty("mail", c.getEmploye().getMail());
                jsonEmp.addProperty("disponibilite", c.getEmploye().getDisponibilite());
                jsonEmp.addProperty("genre", c.getEmploye().getGenre());
                jsonEmp.addProperty("nombre de consultations", c.getEmploye().getNbConsultations());
                jsonEmp.addProperty("telephone", c.getEmploye().getNumTel());
                jsonConsult.add("employe", jsonEmp);
                
                JsonObject jsonMedium = new JsonObject () ;
                jsonMedium.addProperty("numero", c.getMedium().getId());
                jsonMedium.addProperty("denomination", c.getMedium().getDenomination());
                jsonMedium.addProperty("genre", c.getMedium().getGenre());
                jsonMedium.addProperty("presentation", c.getMedium().getPresentation());
                jsonConsult.add("medium", jsonMedium);

                //jsonConsult.addProperty("client", (Object) c.getClient());
                //jsonConsult.addProperty("employe", (Object) c.getEmploye());
                //jsonConsult.addProperty("medium", (Object) c.getMedium());
                jsonListeConsultations.add(jsonConsult);
            }
            //container.addProperty("succes", true);
            container.add("historique", jsonListeConsultations);
        }else {
            container.addProperty("historique", false);
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
