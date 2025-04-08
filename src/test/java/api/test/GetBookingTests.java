package api.test;

import api.endpoints.BookingEndPoints;
import api.payload.Booking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetBookingTests extends BaseTest{


    int bookingId ;

    @Test(groups = {"regression","smoke"})
    public void testGetBooking(){
        bookingId = CreateBookingTests.getBookingId();

        System.out.println("fetched booking id " + bookingId);
        Response response = BookingEndPoints.getBooking(bookingId);
        response.then().statusCode(200);

        //response.prettyPrint();
        //response.then().log().all();

        Booking BookingPayload = CreateBookingTests.getBookingPayload();
        String expectedFirstName = BookingPayload.getFirstname();
        String expectedLastName = BookingPayload.getLastname();

        Assert.assertEquals(response.jsonPath().getString("firstname"), expectedFirstName);
        Assert.assertEquals(response.jsonPath().getString("lastname"), expectedLastName);

    }

}
