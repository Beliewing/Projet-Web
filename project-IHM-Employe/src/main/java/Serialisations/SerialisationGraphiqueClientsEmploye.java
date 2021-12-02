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
public class SerialisationGraphiqueClientsEmploye extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<Employe, Integer> listeNbClientsEmploye = (Map<Employe, Integer>) request.getAttribute("listeNbClientsEmploye");
        
        JsonObject container = new JsonObject();
        container.addProperty("succes", true);

        JsonArray jsonListe = new JsonArray();
        if(listeNbClientsEmploye!=null){
            Set<Employe> keys = listeNbClientsEmploye.keySet();

            for (Employe key : keys) {
                JsonObject jsonLigne = new JsonObject();
                jsonLigne.addProperty("employe", key.getPrenom()+" "+key.getNom());
                jsonLigne.addProperty("nbClients", listeNbClientsEmploye.get(key));
                jsonListe.add(jsonLigne);
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
