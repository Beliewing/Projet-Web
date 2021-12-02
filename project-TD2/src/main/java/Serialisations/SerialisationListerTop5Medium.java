package Serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natha
 */
public class SerialisationListerTop5Medium extends Serialisation{
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Medium> mediums = (List<Medium>)request.getAttribute("mediums");
        JsonObject  container = new  JsonObject ();
        
        JsonArray jsonListeMediums = new JsonArray () ;
        for ( Medium m : mediums ) {
            JsonObject jsonMedium = new JsonObject () ;
            jsonMedium.addProperty("numero", m.getId());
            jsonMedium.addProperty("denomination", m.getDenomination());
            jsonMedium.addProperty("genre", m.getGenre());
            jsonMedium.addProperty("presentation", m.getPresentation());
            jsonListeMediums.add(jsonMedium );
        }
        container.add("mediums", jsonListeMediums );
               
        response.setContentType("application/json;charset=UTF -8");
        PrintWriter  out = response.getWriter ();
        Gson  gson = new  GsonBuilder ().setPrettyPrinting ().serializeNulls ().create ();
        gson.toJson(container , out);
        out.close();
    }
    
}
