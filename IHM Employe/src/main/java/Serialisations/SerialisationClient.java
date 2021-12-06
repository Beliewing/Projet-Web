/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migoz
 */
public class SerialisationClient extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //Consultation consult = (Consultation) request.getSession().getAttribute("consultEnCours");
        Consultation consult = (Consultation) request.getAttribute("consultEnCours");
        System.out.println("Serialisation en cours");
        JsonObject container = new JsonObject ();
        if (consult!=null){
            Client c = consult.getClient();
            container.addProperty("consultEnCours", true);
            JsonObject client = new JsonObject ();
            client.addProperty("id", c.getId());
            client.addProperty("nom", c.getNom());
            client.addProperty("prenom", c.getPrenom());
            client.addProperty("civilite", c.getCivilite());
            SimpleDateFormat simpleDate=new SimpleDateFormat("dd/MM/yyyy");
            String dateTemp = simpleDate.format(c.getDateNaissance());
            client.addProperty("dateNaissance", dateTemp );
            client.addProperty("mail", c.getMail());
            client.addProperty("tel", c.getNumTel());
            JsonObject profilAstral = new JsonObject ();
            profilAstral.addProperty("signeZodiac",c.getProfilAstral().getSigneZodiac());
            profilAstral.addProperty("signeChinois",c.getProfilAstral().getSigneChinois());
            profilAstral.addProperty("couleurBonheur",c.getProfilAstral().getCouleurBonheur());
            profilAstral.addProperty("animalTotem",c.getProfilAstral().getAnimalTotem());
            container.add("profilAstral", profilAstral);
            container.add("client", client);
        } else {
            container.addProperty("consultEnCours", false);
        }
        System.out.println("Fin de serialisation");
        response.setContentType("application/json;charset=UTF -8");
        PrintWriter out = response.getWriter ();
        Gson  gson = new  GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
