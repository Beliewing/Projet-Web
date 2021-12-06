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
import fr.insalyon.dasi.metier.modele.Astrologue;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author natha
 */
public class SerialisationListerAstrologue extends Serialisation{
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Astrologue> astrologues = (List<Astrologue>)request.getAttribute("astrologues");
        JsonObject  container = new  JsonObject ();
        
        JsonArray jsonListeAstrologues = new JsonArray () ;
        for ( Astrologue a : astrologues ) {
            JsonObject jsonAstrologue = new JsonObject () ;
            jsonAstrologue.addProperty("numero", a.getId());
            jsonAstrologue.addProperty("denomination", a.getDenomination());
            jsonAstrologue.addProperty("genre", a.getGenre());
            jsonAstrologue.addProperty("presentation", a.getPresentation());
            jsonAstrologue.addProperty("formation", a.getFormation());
            jsonAstrologue.addProperty("promotion", a.getPromotion());
            jsonListeAstrologues.add(jsonAstrologue );
        }
        container.add("astrologues", jsonListeAstrologues );
               
        response.setContentType("application/json;charset=UTF -8");
        PrintWriter  out = response.getWriter ();
        Gson  gson = new  GsonBuilder ().setPrettyPrinting ().serializeNulls ().create ();
        gson.toJson(container , out);
        out.close();
    }
    
}
