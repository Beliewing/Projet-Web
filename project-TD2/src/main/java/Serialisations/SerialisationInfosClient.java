/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisations;

import Serialisations.Serialisation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migoz
 */
public class SerialisationInfosClient extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("---Serialisation InfosClients");
        Client c = (Client) request.getAttribute("infosClient");
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");

        JsonObject container = new JsonObject();
        container.addProperty("connexion", true);
        JsonObject jsonClient = new JsonObject();
        jsonClient.addProperty("id", c.getId());
        jsonClient.addProperty("nom", c.getNom());
        jsonClient.addProperty("prenom", c.getPrenom());
        jsonClient.addProperty("mail", c.getMail());

        String dateTemp = simpleDate.format(c.getDateNaissance());
        jsonClient.addProperty("date", dateTemp);

        jsonClient.addProperty("tel", c.getNumTel());

        JsonObject profilAstral = new JsonObject();
        profilAstral.addProperty("signeZodiaque", c.getProfilAstral().getSigneZodiac());
        profilAstral.addProperty("signeChinois", c.getProfilAstral().getSigneChinois());
        profilAstral.addProperty("couleurBonheur", c.getProfilAstral().getCouleurBonheur());
        profilAstral.addProperty("animalTotem", c.getProfilAstral().getAnimalTotem());
        jsonClient.add("profilAstral", profilAstral);

        container.add("client", jsonClient);

        response.setContentType("application/json;charset=UTF -8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
