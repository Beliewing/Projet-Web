/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migoz
 */
public class SerialisationEtat extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Consultation consult = (Consultation) request.getAttribute("consultEnCours");
        JsonObject container = new JsonObject();
        if (consult != null){
            container.addProperty("succes", true);
            if (consult.getEtat().equals("en attente")){
                container.addProperty("etat", "en attente");
            } else if (consult.getDateDeb()==null) {
                container.addProperty("etat", "confirmee");
            } else if (consult.getDateFin()==null) {
                container.addProperty("etat", "en cours");
            } else if (consult.getCommentaire()==null){
                container.addProperty("etat", "terminee");
            } else {
                container.addProperty("etat", "validee");
            }
        }else {
            container.addProperty("succes", false);
        }
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
