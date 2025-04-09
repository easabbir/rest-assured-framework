package api.test;

import api.endpoints.BookingEndPoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DeleteBookingTests extends BaseTest{

    int bookingId ;
    String token;

    @Test(groups = {"regression","smoke"})
    public void testDeleteBooking(){
        bookingId = CreateBookingTests.getBookingId();
        token = AuthTest.getToken();

        System.out.println("fetched booking id for delete " + bookingId);

        Response response = BookingEndPoints.deleteBooking (bookingId, token);
        response.then().statusCode(201);

//        response.prettyPrint();
//        response.then().log().all();

    }
}
