package api.test;

import api.endpoints.BookingEndPoints;
import api.payload.Auth;
import api.payload.Booking;
import api.payload.BookingDates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import lombok.Getter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Calendar;

import static java.util.Calendar.DAY_OF_MONTH;

public class UpdateBookingTests extends BaseTest{

    int bookingId ;
    String token;
    Faker faker;
    Booking BookingPayload ;
    @BeforeClass
    public void setupData() throws JsonProcessingException {

        faker = new Faker();
        BookingPayload = CreateBookingTests.getBookingPayload();

        // Updated Booking payload
        BookingPayload.setFirstname("updated " + BookingPayload.getFirstname());
        BookingPayload.setLastname("updated " + BookingPayload.getLastname());
    }

    @Test(groups = {"regression","smoke"})
    public void testUpdateBooking(){
        bookingId = CreateBookingTests.getBookingId();
        token = AuthTest.getToken();

        System.out.println("fetched booking id for update " + bookingId);

        Response response = BookingEndPoints.updateBooking( BookingPayload, bookingId, token);
        response.then().statusCode(200);

        //response.prettyPrint();
        //response.then().log().all();

        Booking BookingPayload = CreateBookingTests.getBookingPayload();
        String expectedFirstName = BookingPayload.getFirstname();
        String expectedLastName =  BookingPayload.getLastname();

        Assert.assertEquals(response.jsonPath().getString("firstname"), expectedFirstName);
        Assert.assertEquals(response.jsonPath().getString("lastname"), expectedLastName);

    }


}
