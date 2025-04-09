package api.test;

import api.endpoints.BookingEndPoints;
import api.payload.Booking;
import api.payload.BookingPartial;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UpdateBookingPartially extends BaseTest{

    int bookingId ;
    String token;
    Faker faker;
    BookingPartial bookingPayloadForPartialUpdate ;

    @BeforeClass
    public void setupData() throws JsonProcessingException {

        faker = new Faker();

        bookingPayloadForPartialUpdate = new BookingPartial();
        bookingPayloadForPartialUpdate.setFirstname("updated " + faker.name().firstName());

    }

    @Test(groups = {"regression","smoke"})
    public void testUpdateBookingPartially(){
        bookingId = CreateBookingTests.getBookingId();
        token = AuthTest.getToken();

        System.out.println("fetched booking id for partial update " + bookingId);

        Response response = BookingEndPoints.updateBookingPartially( bookingPayloadForPartialUpdate, bookingId, token);
        response.then().statusCode(200);

        //response.prettyPrint();
        //response.then().log().all();


        String expectedFirstName = bookingPayloadForPartialUpdate.getFirstname();

        Assert.assertEquals(response.jsonPath().getString("firstname"), expectedFirstName);


    }
}
