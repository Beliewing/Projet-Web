/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project.td2;

import Serialisations.Serialisation;
import Actions.ActionAuthentifierClient;
import Actions.Action;
import Actions.ActionConsulterHistoriqueClient;
import Actions.ActionCreerConsultation;
import Actions.ActionDeconnexion;
import Actions.ActionInfosClient;
import Actions.ActionInscrireClient;
import Actions.ActionListerAstrologue;
import Actions.ActionListerCartomancien;
import Actions.ActionListerSpirite;
import Actions.ActionListerTop5Medium;
import Actions.ActionModifierInfos;
import Serialisations.SerialisationInfosClient;
import Serialisations.SerialisationConsulterHistoriqueClient;
import Serialisations.SerialisationConsulterHistoriqueClient;
import Serialisations.SerialisationListerAstrologue;
import Serialisations.SerialisationListerCartomancien;
import Serialisations.SerialisationListerSpirite;
import Serialisations.SerialisationListerTop5Medium;
import Serialisations.SerialisationSuccesEchec;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migoz
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
      super.init();
      JpaUtil.init();
    }

    @Override
    public void destroy() {
      JpaUtil.destroy();
      super.destroy();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActionServlet WAWWWWWat " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
            
            System.out.println("ActionServet en marche");
            
            Service service = new Service();
            String todo= request.getParameter("todo");
            response.setContentType("application/json;charset=UTF-8");
            //http://localhost:8080/project-TD2/ActionServlet?todo=connecter&login=claude.chappe1@insa-lyon.fr&password=HaCKeR
            
            Action action= null;
            Serialisation serialisation = null;
            switch(todo) 
            {
                case "connecter": {
                    action= new ActionAuthentifierClient(service);
                    serialisation = new SerialisationSuccesEchec();
                    break;
                }
                case "inscrire": {
                    action= new ActionInscrireClient(service);
                    serialisation = new SerialisationSuccesEchec();
                    break;
                }
                case "listerSpirites": {
                    action = new ActionListerSpirite(service);
                    serialisation = new SerialisationListerSpirite();
                    break;
                }

                case "listerAstrologues":{
                    action=new ActionListerAstrologue(service);
                    serialisation=new SerialisationListerAstrologue();
                    break;
                }

                case "listerCartomanciens":{
                    action=new ActionListerCartomancien(service);
                    serialisation=new SerialisationListerCartomancien();
                    break;
                }

                case "listertop5Mediums":{
                    action=new ActionListerTop5Medium(service);
                    serialisation=new SerialisationListerTop5Medium();
                    break;
                }
                
                case "historiqueConsultations":{
                    action=new ActionConsulterHistoriqueClient(service);
                    serialisation=new SerialisationConsulterHistoriqueClient();
                    break;
                }
                
                case "infosClient":{
                    action=new ActionInfosClient(service);
                    serialisation=new SerialisationInfosClient();
                    break;
                }
                
                case "profilAstral":{
                    action=new ActionInfosClient(service);
                    serialisation=new SerialisationInfosClient();
                    break;
                }
                
                case "creerConsultation":{
                    action=new ActionCreerConsultation(service);
                    serialisation=new SerialisationSuccesEchec();
                    break;
                }
                
                case "modifierInfos":{
                    action=new ActionModifierInfos(service);
                    serialisation=new SerialisationInfosClient();
                    break;
                }
                
                case "deconnecter": {
                    action= new ActionDeconnexion(service);
                    serialisation = new SerialisationSuccesEchec();
                    break;
                }
            }
            
            
            if (action == null && serialisation == null) {
                response.sendError(400, "Bad request (pas d'Action ou de Serialisation pour traiter cette request)");
            }else{
                action.execute(request);
                serialisation.serialiser(request, response);
            }
        }
    }
    


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
