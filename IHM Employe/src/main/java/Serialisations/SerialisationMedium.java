/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migoz
 */
public class SerialisationMedium extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        JsonObject  container = new  JsonObject ();
        if ((boolean)request.getAttribute("succes")){
            Medium m = (Medium) request.getAttribute("medium");
            container.addProperty("succes", true);
            JsonObject  jsonMedium = new  JsonObject ();
            jsonMedium.addProperty("denomination", m.getDenomination());
            jsonMedium.addProperty("genre", m.getGenre());
            jsonMedium.addProperty("presentation", m.getPresentation());
            container.add("medium", jsonMedium);
        }else{
            container.addProperty("succes", false);
        }
               
        response.setContentType("application/json;charset=UTF -8");
        PrintWriter out = response.getWriter ();
        Gson  gson = new  GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
