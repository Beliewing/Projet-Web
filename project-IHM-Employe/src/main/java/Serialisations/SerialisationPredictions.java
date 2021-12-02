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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migoz
 */
public class SerialisationPredictions extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean test = (boolean) request.getAttribute("succes");
        JsonObject container = new JsonObject();
        container.addProperty("succes", test);
        if (test){
            List<String> predictions = (List<String>)request.getAttribute("predictions");
            JsonObject jsonPredictions = new JsonObject();
            jsonPredictions.addProperty("amour",predictions.get(0));
            jsonPredictions.addProperty("sante",predictions.get(1));
            jsonPredictions.addProperty("travail",predictions.get(2));
            container.add("predictions",jsonPredictions);
            System.out.println("----Json renvoyé : "+container);
        }
        
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
