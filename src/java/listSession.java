/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class listSession extends HttpServlet {
    private static int counter = 0; //how many views
    
    //increment value with concurrency
    private synchronized void Increment(){ counter++; }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //get session
        HttpSession session = request.getSession();
        int value = 1;
        
        if(session.getAttribute("counter")==null){
            session.setAttribute("counter", value);
        } else{
            value = (int)session.getAttribute("counter") + 1;
            session.setAttribute("counter", value);
        }
        
        //counter
        Increment();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<head>\n" +
                        "<title>Cookie Maker</title>" +
                        "<meta charset=\"UTF-8\">" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">" +
                        "</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            
            //title
            out.println("<div class=\"row\">" +
                        "<div class=\"jumbotron text-center\"><h2>List Cookie</h2></div>" +
                        "</div>");
            
            //return to index
            out.println("<div class=\"row\">" +
                        "<div class=\"alert alert-info col-sm-2\"><a href=\"index.html\">Return to index</a></div>" +
                        "</div><br>");
            
            //show views
            out.println("<div class=\"row\">" +
                        "<div class=\"alert alert-warning col-sm-2\">Views: " + counter + "</div>" +
                        "</div>");
            
            //show views per session
            out.println("<div class=\"row\">" +
                        "<div class=\"alert alert-warning col-sm-2\">Individual views: " + value + "</div>" +
                        "</div><br>");
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
