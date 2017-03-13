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

public class listCookie extends HttpServlet {
    private static int counter = 0; //how many views
    
    //increment value with concurrency
    private synchronized void Increment(){ counter++; }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //counter
        Increment();
        
        //create cookie
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        
        Cookie userCookie = new Cookie(name, value);
        userCookie.setMaxAge(60*60*24*365); //Store cookie for 1 year
        response.addCookie(userCookie);
        
        Cookie[] cookies = request.getCookies();
        
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
            
            //cookies
            out.println("<div class=\"row\">" +
                        "<div class=\"col-sm-2\">" + userCookie.getName() + "</div>" +
                        "<div class=\"col-sm-2\">" + userCookie.getValue() + "</div>" +
                        "</div>"); //new
            
            Cookie temp;
            if (cookies != null) //old
            {
                for(int i=0; i<cookies.length; i++) 
                {
                    temp = cookies[i];
                    
                    out.println("<div class=\"row\">" +
                                "<div class=\"col-sm-2\">" + temp.getName() + "</div>" +
                                "<div class=\"col-sm-2\">" + temp.getValue()+ "</div>" +
                                "</div>");
                    
                }
            }
            
            //create cookie
            out.println("<div class=\"row\">" +
                        "<div class=\"alert alert-info col-sm-2\"><a href=\"index.html\">Create Cookie</a></div>" +
                        "</div>");
            
            //show views
            out.println("<div class=\"row\">" +
                        "<div class=\"alert alert-warning col-sm-2\">Views: " + counter + "</div>" +
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
