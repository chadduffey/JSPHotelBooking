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
            String datetime = request.getParameter("datetime"); 
            
            //parameters have already been validated (this is the edit path)
            String message;
            message = "Please make the desired changes to your booking";
            url = "/index.jsp";
            
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
            request.setAttribute("datetime", datetime);
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
            String datetime = request.getParameter("datetime");
            
           
            //--> Begin validation of parameters
            //-----------------------------------
            //-----------------------------------
            
            //This is the message we will pass back to the user
            String message;
            
            //First Check we have the required data
            //we are doing this separate to provide detailed messages via
            //the message parameter defined above.
            
            //In practice we may prefer javascript for detailed messages
            //on the client side
            //and a single if statement for server side validation
            //however, the task asked specifically to do server side.
            
            if((checkin == null) || (checkin.isEmpty())){
                message = "Please supply a check-in date";
                url = "/index.jsp";
            
            }
            
            else if((checkout == null) || (checkout.isEmpty())){
                message = "Please supply a check-out date";
                url = "/index.jsp";
            
            }
            
            else if((firstname == null) || (firstname.isEmpty())){
                message = "Please supply a first name";
                url = "/index.jsp";
            }
            
            else if((surname == null) || (surname.isEmpty())){
                message = "Please supply a last name";
                url = "/index.jsp";
            }
            
            else if((email == null) || (email.isEmpty())){
                message = "Please an email address";
                url = "/index.jsp";
            }
            
            else if((phone == null) || (phone.isEmpty())){
                message = "Please an phone number";
                url = "/index.jsp";
            }
            
            else if((address1 == null) || (address1.isEmpty())){
                message = "Please an Address";
                url = "/index.jsp";
            }

            //ensure at least one night is reserved
            //***
            //TO DO
            
            //ensure booking is less than 2 months
            //***
            //TO DO
            
            //ensure booking commerces less than a year from now
            //***
            //TO DO
            
            //ensure firstname and lastname are at least 2 characters each
            else if (firstname.length() < 2){
                message = "First name should be 2 characters or more";
                url = "/index.jsp";    
            }
            
            else if (surname.length() < 2){
                message = "Last name should be 2 characters or more";
                url = "/index.jsp";    
            }
            
            //ensure there are no digits in the name - characters may be ok
            else if (firstname.matches("\\d*")) {
                message = "Numbers are not allowed in the name field";
                url = "/index.jsp";    
            }
            
            else if (surname.matches("\\d*")) {
                message = "Numbers are not allowed in the name field";
                url = "/index.jsp";    
            }
            
            //ensure email address is valid something@something.com
            
            else if (!isValidEmailAddress(email)) {
                message = "The email address you provided appears to be invalid";
                url = "/index.jsp";    
            }
            
            //ensure phone number is valid, some characters allowed like -
            
            else if (!isValidPhone(phone)) {
                message = "The phone number you provided appears to be invalid";
                url = "/index.jsp";    
            }
            
            //ensure address is longer than 3 characters -
            
            else if (address1.length() < 3) {
                message = "The address you provided appears to be too short";
                url = "/index.jsp";    
            }
            
            //* Accept the booking *
            else {
                //customer details are valid, accept the booking
                message = "";
                url = "/thanks.jsp";
            }
            
            //*
            //--> End of validation code.
            
            //Pass values to the next jsp presented to the user
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
            request.setAttribute("datetime", datetime);
            
            //calculate the total cost of the booking
            //***
            //TO DO
            
        
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request,response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public boolean isValidEmailAddress(String e_mail) {
        String validPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(validPattern);
        java.util.regex.Matcher match = pattern.matcher(e_mail);
        return match.matches();
    }
    
    
    public boolean isValidPhone(String number) {
        String validPattern = "^[0-9.()-]{10,25}$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(validPattern);
        java.util.regex.Matcher match = pattern.matcher(number);

        return match.matches();
    }

    
}
