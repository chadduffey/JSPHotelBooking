<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/normalize.css" rel="stylesheet" />
        <link href='http://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link href="css/main.css" rel="stylesheet" />
        <title>Confirmation</title>
    </head>
    <body>
     
        <form action="booking" method="post">
            <input type="hidden" name="action" value="edit">
            <h1>Thanks for your booking</h1>
            <h2>Your details are as follows:</h2>
            <p>Check-in date: ${checkin}</p>
            <p>Check-out date: ${checkout}</p>
            <p>Room type: ${room_type}</p>
            <p>Smoking: ${smoking_choice}</p>
            <p>Your Name: ${firstname} ${surname} <p>    
            <p>Your Email: ${email}</p>
            <p>Phone number: ${phone}</p>
            <p>Address: ${address1} ${address2}</p>
            <p>State: ${state}</p>
            <p>Country: ${country}</p><br/>
            <input type="submit" value="Modify your booking">
        </form> 
            
    </body>
</html>
