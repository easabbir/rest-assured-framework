package api.endpoints;

/*
Swagger URI -->         https://petstore.swagger.io/

Create user (Posts) :   https://petstore.swagger.io/v2/user
Get user (Get):         https://petstore.swagger.io/v2/user/{username}
Update user (Put):      https://petstore.swagger.io/v2/user/{username}
Delete user (Delete):   https://petstore.swagger.io/v2/user/{username}

 */
public class Routes {
   public static String base_url= "https://restful-booker.herokuapp.com" ;

   //Booking module

    public static String createToken_url = base_url + "/auth" ;
    public static String createBooking_url = base_url + "/booking" ;
    public static String getBooking_url = base_url + "/booking/{id}" ;
    public static String updateBooking_url = base_url + "/booking/{id}" ;
    public static String partialUpdateBooking_url = base_url + "/booking/{id}" ;
    public static String deleteBooking_url = base_url + "/booking/{id}" ;

    //Next Module
        //Here need to create module URL's



}
