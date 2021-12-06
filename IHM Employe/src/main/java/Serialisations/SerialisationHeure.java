/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migoz
 */
public class SerialisationHeure extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean test = (boolean) request.getAttribute("succes");
        Date heure = (Date) request.getAttribute("heure");
        
        SimpleDateFormat simpleHeure=new SimpleDateFormat("HH:mm");
        String h = simpleHeure.format(heure);
        JsonObject container = new JsonObject();
        container.addProperty("heure",h);
        container.addProperty("succes", test);
        System.out.println("----Json renvoyé : "+container);
        
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
