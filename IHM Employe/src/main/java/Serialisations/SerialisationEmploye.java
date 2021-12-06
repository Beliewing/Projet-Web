/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Employe;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migoz
 */
public class SerialisationEmploye extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Employe e = (Employe) request.getSession().getAttribute("employe");
        JsonObject  container = new  JsonObject ();
        container.addProperty("connexion", true);
        JsonObject  jsonEmploye = new  JsonObject ();
        jsonEmploye.addProperty("id", e.getId());
        jsonEmploye.addProperty("nom", e.getNom());
        jsonEmploye.addProperty("prenom", e.getPrenom());
        jsonEmploye.addProperty("mail", e.getMail());
        jsonEmploye.addProperty("tel", e.getNumTel());
        jsonEmploye.addProperty("mdp", e.getMotDePasse());
        jsonEmploye.addProperty("genre", e.getGenre());
        jsonEmploye.addProperty("nbConsultations", e.getNbConsultations());
        container.add("employe", jsonEmploye);
        System.out.println("----Json de l'employe");
               
        response.setContentType("application/json;charset=UTF -8");
        PrintWriter out = response.getWriter ();
        Gson  gson = new  GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
