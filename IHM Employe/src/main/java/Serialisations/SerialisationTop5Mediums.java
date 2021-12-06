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
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migoz
 */
public class SerialisationTop5Mediums extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Medium> listeTop5Mediums = (List<Medium>) request.getAttribute("listeTop5Mediums");
        
        JsonObject container = new JsonObject();
        container.addProperty("succes", true);

        JsonObject jsonListe = new JsonObject();
        if(listeTop5Mediums!=null){
            int i = 1;
            for (Medium m : listeTop5Mediums) {
                jsonListe.addProperty("top"+Integer.toString(i),m.getDenomination());
                i++;
            }
            container.add("liste", jsonListe);
        }else{
            container.addProperty("succes",false);
        }
        response.setContentType("application/json;charset=UTF -8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
