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
import fr.insalyon.dasi.metier.modele.Spirite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author natha
 */
public class SerialisationListerSpirite extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Spirite> spirites = (List<Spirite>)request.getAttribute("spirites");
        JsonObject  container = new  JsonObject ();
        
        JsonArray jsonListeSpirites = new JsonArray () ;
        for ( Spirite s : spirites ) {
            JsonObject jsonSpirite = new JsonObject () ;
            jsonSpirite.addProperty("numero", s.getId());
            jsonSpirite.addProperty("denomination", s.getDenomination());
            jsonSpirite.addProperty("genre", s.getGenre());
            jsonSpirite.addProperty("presentation", s.getPresentation());
            jsonSpirite.addProperty("support", s.getSupport());
            jsonListeSpirites.add( jsonSpirite );
        }
        container.add("spirites", jsonListeSpirites );
               
        response.setContentType("application/json;charset=UTF -8");
        PrintWriter  out = response.getWriter ();
        Gson  gson = new  GsonBuilder ().setPrettyPrinting ().serializeNulls ().create ();
        gson.toJson(container , out);
        out.close();
    }
}
