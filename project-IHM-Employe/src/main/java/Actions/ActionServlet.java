package Actions;


import Serialisations.Serialisation;
import Serialisations.SerialisationClient;
import Serialisations.SerialisationConsulterHistoriqueClient;
import Serialisations.SerialisationEmploye;
import Serialisations.SerialisationEtat;
import Serialisations.SerialisationGraphiqueClientsEmploye;
import Serialisations.SerialisationGraphiqueConsulMedium;
import Serialisations.SerialisationHeure;
import Serialisations.SerialisationMedium;
import Serialisations.SerialisationPredictions;
import Serialisations.SerialisationSuccesEchec;
import Serialisations.SerialisationTop5Mediums;
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
            
            //http://localhost:8080/project-TD2/ActionServlet?todo=connecter&login=claude.chappe1@insa-lyon.fr&password=HaCKeR
            
            Action action= null;
            Serialisation serialisation = null;
                        
            switch(todo) 
            {
                case "connecter": {
                    action= new ActionAuthentifierEmploye(service);
                    serialisation = new SerialisationSuccesEchec();
                    break;
                }
                
                case "deconnecter": {
                    action= new ActionDeconnecterEmploye(service);
                    serialisation = new SerialisationSuccesEchec();
                    break;
                }
                
                case "infos-employe": {
                    action = new ActionAfficherEmploye(service);
                    serialisation = new SerialisationEmploye();
                    break;
                }
                
                case "modifier-infos": {
                    action= new ActionModifierInfos(service);
                    serialisation = new SerialisationEmploye();
                    break;
                }
                
                case "obtenir-etat": {
                    action= new ActionObtenirConsultationEnCours(service);
                    serialisation = new SerialisationEtat();
                    break;
                }

                case "envoyer-confirmation":{
                    action= new ActionEnvoyerConfirmation(service);
                    serialisation = new SerialisationSuccesEchec();
                    break;
                }
                                
                case "consultation-medium":{
                    action= new ActionObtenirMedium(service);
                    serialisation= new SerialisationMedium();
                    break;
                }
                               
                case "consultation-infos-client":{
                    action= new ActionObtenirConsultationEnCours(service);
                    serialisation = new SerialisationClient();
                    break;
                }
                
                case "consultation-historique":{
                    action= new ActionConsulterHistoriqueClient(service);
                    serialisation= new SerialisationConsulterHistoriqueClient();
                    break;
                }
                
                case "debuter-appel":{
                    action= new ActionDebuterAppel(service);
                    serialisation = new SerialisationHeure();
                    break;
                }
                
                case "terminer-appel":{
                    action= new ActionTerminerAppel(service);
                    serialisation = new SerialisationHeure();
                    break;
                }
                
                case "valider-commentaire":{
                    action= new ActionValiderCommentaire(service);
                    serialisation = new SerialisationSuccesEchec();
                    break;
                } 
                
                case "valider-consultation":{
                    action= new ActionValiderConsultation(service);
                    serialisation = new SerialisationSuccesEchec();
                    break;
                }
                
                case "prediction-aleatoire":{
                    action= new ActionCreerPredictionAleatoire(service);
                    serialisation = new SerialisationPredictions();
                    break;
                }
                case "infos-nbConsulMedium":{
                    action= new ActionNbConsulMedium(service);
                    serialisation = new SerialisationGraphiqueConsulMedium();
                    break;
                }
                
                case "infos-nbClientsEmploye":{
                    action= new ActionNbClientsEmploye(service);
                    serialisation = new SerialisationGraphiqueClientsEmploye();
                    break;
                }
                
                case "infos-top5mediums":{
                    action= new ActionTop5Mediums(service);
                    serialisation = new SerialisationTop5Mediums();
                    break;
                }
                
            }
            
            if (action != null) {
                action.execute(request);
            }
            if (serialisation != null){
                serialisation.serialiser(request, response);
            }
            if (action == null && serialisation == null) {
                response.sendError(400, "Bad request (pas d'Action ou de Serialisation pour traiter cette request)");
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
