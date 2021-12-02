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
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migoz
 */
public class SerialisationGraphiqueConsulMedium extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<Medium, Integer> listeNbConsulMedium = (Map<Medium, Integer>) request.getAttribute("listeNbConsulMedium");

        JsonObject container = new JsonObject();
        container.addProperty("succes", true);

        JsonArray jsonListe = new JsonArray();
        Set<Medium> keys = listeNbConsulMedium.keySet();

        for (Medium key : keys) {
            JsonObject jsonLigne = new JsonObject();
            jsonLigne.addProperty("medium", key.getDenomination());
            jsonLigne.addProperty("nbConsul", listeNbConsulMedium.get(key));
            jsonListe.add(jsonLigne);
            System.out.println(" Le medium: " + key.getDenomination() + "a été sollicité pour " + listeNbConsulMedium.get(key) + " consultations.");
        }

        container.add("liste", jsonListe);

        response.setContentType("application/json;charset=UTF -8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
