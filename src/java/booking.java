// 
//    Document   : booking.java
//    Created on : Aug 11, 2014, 8:15:18 PM
//    Author     : Chad Duffey | Student 11372834
//

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
            String fdate = request.getParameter("fdate");
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
            
            //check we have dates
            if((checkin == null) || (checkin.isEmpty())){
                message = "Please supply a check-in date";
                url = "/index.jsp";
            }
            
            else if((checkout == null) || (checkout.isEmpty())){
                message = "Please supply a check-out date";
                url = "/index.jsp";
            }
            
            //ensure at least one night is reserved   
            else if(checkin.equals(checkout)){
                message = "Minimum stay is one night";
                url = "/index.jsp";
            }
            
            //ensure check in is greater than check out           
            else if (badDates(checkin, checkout))
            {
                message = "Your check-out date is before your check-in";
                url = "/index.jsp";
            }
            
            //check we have entry in name
            else if((firstname == null) || (firstname.isEmpty())){
                message = "Please supply a first name";
                url = "/index.jsp";
            }
            
            //check we have entry in name
            else if((surname == null) || (surname.isEmpty())){
                message = "Please supply a last name";
                url = "/index.jsp";
            }
           
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
            
            //check we have entry in email
            else if((email == null) || (email.isEmpty())){
                message = "Please an email address";
                url = "/index.jsp";
            }
            
            //ensure email address is valid something@something.com
            else if (!isValidEmailAddress(email)) {
                message = "The email address you provided appears to be invalid";
                url = "/index.jsp";    
            }
            
            //check we have entry in phone
            else if((phone == null) || (phone.isEmpty())){
                message = "Please an phone number";
                url = "/index.jsp";
            }
            
            //ensure phone number is valid, some characters allowed like -
            else if (!isValidPhone(phone)) {
                message = "The phone number you provided appears to be invalid";
                url = "/index.jsp";    
            }
            
            //check we have entry in address
            else if((address1 == null) || (address1.isEmpty())){
                message = "Please an Address";
                url = "/index.jsp";
            }

            //ensure address is longer than 3 characters
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

            //---> End of validation code.
            
            //Pass values to the next jsp presented to the user
            request.setAttribute("fdate", fdate);
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
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request,response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //email checker using regular expression
    public boolean isValidEmailAddress(String e_mail) {
        String validPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(validPattern);
        java.util.regex.Matcher match = pattern.matcher(e_mail);
        return match.matches();
    }
    
    //phone checker using regular expression
    public boolean isValidPhone(String number) {
        String validPattern = "^[0-9.()-]{10,25}$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(validPattern);
        java.util.regex.Matcher match = pattern.matcher(number);

        return match.matches();
    }
    
    //check that date 1 is before date 2 to validate checkin and checkout
    public boolean badDates(String checkin, String checkout) {
        
        //break up strings
        String[] checkinSplits = checkin.split("/");
        String[] checkoutSplits = checkout.split("/");
        
        //get day number
        String checkinDay = checkinSplits[0];
        String checkoutDay = checkoutSplits[0];
        
        //get month number
        String checkinMonth = checkinSplits[1];
        String checkoutMonth = checkoutSplits[1];
        
        //get year number
        String checkinYear = checkinSplits[2];
        String checkoutYear = checkoutSplits[2];

        //convert to integers
        int intCheckinDay = Integer.parseInt(checkinDay);
        int intCheckoutDay = Integer.parseInt(checkoutDay);
        
        int intCheckinMonth = Integer.parseInt(checkinMonth);
        int intCheckoutMonth = Integer.parseInt(checkoutMonth);
        
        int intCheckinYear = Integer.parseInt(checkinYear);
        int intCheckoutYear = Integer.parseInt(checkoutYear);
        
        //make sure check out year is greater or equal to check in
        if (intCheckoutYear < intCheckinYear)
        {
            return true;
        }
        
        //check if month is equal or greater
        if (intCheckoutMonth < intCheckinMonth)
        {
            return true;
        }
        
        //check if day is greater
        if (intCheckoutDay < intCheckinDay){
            return true;
        }
        
        //if all this is ok, we can return false.
        //because its not a bad date, its a valid date
        
        return false;
    }

    
}
