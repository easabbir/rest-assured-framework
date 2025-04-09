package api.endpoints;
import api.payload.Auth;
import api.payload.Booking;
import api.payload.BookingPartial;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

//UserEndpoints.java
// Created for perform Create, Read, Update, Delete requests the user API

public class BookingEndPoints {
    public static Response createToken(Auth payload)
    {
        Response response = given ()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(Routes.createToken_url);

        return response;
    }

    public static Response getBooking(int bookingId)
    {
        Response response = given ()
                .pathParam("id" , bookingId)
        .when()
                .get(Routes.getBooking_url);

        return response;
    }

    public static Response createBooking( Booking payload)
    {
        Response response = given ()
                .contentType(ContentType.JSON)
                //.accept(ContentType.JSON)
                .body(payload)

        .when()
                .post(Routes.createBooking_url);

        return response;
    }

    public static Response updateBooking( Booking payload, int bookingId, String token)
    {
        Response response = given ()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .accept("application/json")
                .header("Cookie", "token=" + token)
                .pathParam("id" , bookingId)
                .body(payload)
                .when()
                .put(Routes.updateBooking_url);

        return response;
    }

    public static Response updateBookingPartially(BookingPartial payload, int bookingId, String token)
    {
        Response response = given ()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .accept("application/json")
                .header("Cookie", "token=" + token)
                .pathParam("id" , bookingId)
                .body(payload)
                .when()
                .patch(Routes.partialUpdateBooking_url);

        return response;
    }


    public static Response deleteBooking(String bookingId)
    {
        Response response = given ()
                .pathParam("id" , bookingId)
                .when()
                .delete(Routes.deleteBooking_url);

        return response;
    }

}
