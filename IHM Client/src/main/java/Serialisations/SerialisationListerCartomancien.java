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
import fr.insalyon.dasi.metier.modele.Cartomancien;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author natha
 */
public class SerialisationListerCartomancien extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Cartomancien> cartomanciens = (List<Cartomancien>)request.getAttribute("cartomanciens");
        JsonObject  container = new  JsonObject ();
        
        JsonArray jsonListeCartomanciens = new JsonArray () ;
        for ( Cartomancien c : cartomanciens ) {
            JsonObject jsonCartomancien = new JsonObject () ;
            jsonCartomancien.addProperty("numero", c.getId());
            jsonCartomancien.addProperty("denomination", c.getDenomination());
            jsonCartomancien.addProperty("genre", c.getGenre());
            jsonCartomancien.addProperty("presentation", c.getPresentation());
            jsonListeCartomanciens.add( jsonCartomancien );
        }
        container.add("cartomanciens", jsonListeCartomanciens );
               
        response.setContentType("application/json;charset=UTF -8");
        PrintWriter  out = response.getWriter ();
        Gson  gson = new  GsonBuilder ().setPrettyPrinting ().serializeNulls ().create ();
        gson.toJson(container , out);
        out.close();
    }
    
}
