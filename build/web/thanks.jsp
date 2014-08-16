<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/normalize.css" rel="stylesheet" />
        <link href='http://fonts.googleapis.com/css?family=Amatic+SC|Lato:300|Source-Sans-Pro' rel='stylesheet' type='text/css'>
        <link href="css/confirm.css" rel="stylesheet" />
        <title>Confirmation</title>
    </head>
    <body>
     
        <form action="booking" method="post">
            <input type="hidden" name="action" value="edit">
            <h1>Thanks for your booking</h1>
            <div class="box">
                
                <div class="content">
                    <p class="centered">Booking created: ${datetime}</p>
                    
                    <p>Check-in date:   ${checkin}</p>
                    <input type="hidden" name="checkin" value="${checkin}">
                    
                    <p>Check-out date:  ${checkout}</p>
                    <input type="hidden" name="checkout" value="${checkout}">
                    
                    <p>Room type:       ${room_type}</p>
                    <input type="hidden" name="room_type" value="${room_type}">
                    
                    <p>Smoking:         ${smoking_choice}</p>
                    <input type="hidden" name="smoking_choice" value="${smoking_choice}">
                    
                    <p>Your Name:       ${firstname} ${surname} <p>
                    <input type="hidden" name="firstname" value="${firstname}">
                    <input type="hidden" name="surname" value="${surname}">
                    
                    <p>Your Email:      ${email}</p>
                    <input type="hidden" name="email" value="${email}">
                    
                    <p>Phone number:    ${phone}</p>
                    <input type="hidden" name="phone" value="${phone}">
                    
                    <p>Address:         ${address1} ${address2}</p>
                    <input type="hidden" name="address1" value="${address1}">
                    <input type="hidden" name="address2" value="${address2}">
                    
                    <p>State:           ${state}</p>
                    <input type="hidden" name="state" value="${state}">
                    
                    <p>Country:         ${country}</p><br/>
                    <input type="hidden" name="country" value="${country}">
                    
                    <input type="submit" value="Modify your booking">
                </div>
            </div>
        </form> 
            
    </body>
</html>
