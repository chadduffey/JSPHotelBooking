import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class booking extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet booking</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet booking at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/index.jsp";
        
        //get current action - in case we offer others such as edit 
        //when we send confirmation
        String action = request.getParameter("action");
        if (action == "null"){
            action = "book";
        }
        
        else if (action.equals("edit")){
            //get parameters from the request
            String firstname = request.getParameter("firstname");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email"); 
            
            //parameters have already been validated (this is the edit path)
            String message;
            message = "making a change to your Name";
            url = "/index.jsp";
            
            request.setAttribute("firstname", firstname);
            request.setAttribute("surname", surname);
            request.setAttribute("message", message);
            request.setAttribute("email", email);
        }
        
        
        else if (action.equals("book")){
            //get parameters from the request
            String checkin = request.getParameter("checkin");
            String checkout = request.getParameter("checkout");
            String room_type = request.getParameter("room_type");
            String smokingchoice = request.getParameter("smokingchoice");
            String firstname = request.getParameter("firstname");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address1 = request.getParameter("address1");
            String address2 = request.getParameter("address2");
            String state = request.getParameter("state");
            String country = request.getParameter("country");
            
            //store data in a user object
            //User user = new User(fullname); --> need user class first
            
            //validate the parameters
            String message;
            if ((firstname == null) || (firstname.isEmpty()) || (surname == null) || 
                    (surname.isEmpty()) || (email == null) || (email.isEmpty())){
                message = "You have missed some of the required information";
                url = "/index.jsp";
            }
            else {
                //valid, accept the booking
                message = "";
                url = "/thanks.jsp";
            }
            
            request.setAttribute("checkin", checkin);
            request.setAttribute("checkout", checkout);
            request.setAttribute("room_type", room_type);
            request.setAttribute("smokingchoice", smokingchoice);
            request.setAttribute("firstname", firstname);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address1", address1);
            request.setAttribute("address2", address2);
            request.setAttribute("state", state);
            request.setAttribute("country", country);
            request.setAttribute("message", message);          
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request,response);
        
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
