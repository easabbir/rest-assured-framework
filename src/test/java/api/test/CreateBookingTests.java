package api.test;
import api.endpoints.BookingEndPoints;
import api.payload.Auth;
import api.payload.Booking;
import api.payload.BookingDates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import lombok.Getter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static java.util.Calendar.DAY_OF_MONTH;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingTests extends BaseTest {

    Faker faker;

    @Getter
    static Booking bookingPayload;
    BookingDates bookingDatesPayload;
    Calendar calendar = Calendar.getInstance();

    @Getter
    static int bookingId;

    @BeforeClass
    public void setupData() throws JsonProcessingException {

        faker = new Faker();

        // Create BookingDates payload
        bookingDatesPayload = new BookingDates();
        bookingDatesPayload.setCheckin(calendar.getTime()); // Set checkin date to current date
        calendar.add(DAY_OF_MONTH, 1);
        bookingDatesPayload.setCheckout(calendar.getTime()); // Set checkout date to current date

        // Create Booking payload
        bookingPayload = new Booking();
        bookingPayload.setFirstname(faker.name().firstName());
        bookingPayload.setLastname(faker.name().lastName());
        bookingPayload.setTotalprice(faker.number().randomNumber());
        bookingPayload.setDepositpaid(faker.bool().bool());
        bookingPayload.setBookingdates(bookingDatesPayload);
        bookingPayload.setAdditionalneeds(faker.food().dish());

    }


    @Test(groups = {"regression","smoke"})
    public void testCreateBooking(){
        Response response = BookingEndPoints.createBooking(bookingPayload);
        response.then().statusCode(200);

        //response.prettyPrint();
        //response.then().log().all();
        bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("created booking id : " + bookingId);

    }

    @Test(groups = {"regression"})
    public void testCheckoutDateIsCurrentDatePlusOneDay() {
        // Get the current date plus one day
        Calendar calendar = Calendar.getInstance();
        calendar.add(DAY_OF_MONTH, 1);
        Date expectedCheckoutDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String expectedCheckoutDateStr = dateFormat.format(expectedCheckoutDate);

        bookingDatesPayload.setCheckout(calendar.getTime());
        // Send the API request
        Response response = BookingEndPoints.createBooking(bookingPayload);

        // Validate the response
        response.then()
                .statusCode(200) // Assuming the API returns 200 on success
                .body("booking.bookingdates.checkout", equalTo(expectedCheckoutDateStr)); // Validate the checkout date in the response
    }

    @Test(groups = {"regression"})
    public void testCheckoutDateHandlesLeapYear() {
        // Mock the current date to be February 28, 2024 (leap year)
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.FEBRUARY, 28); // February 28, 2024
        //Date currentDate = calendar.getTime();

        // Add one day to the current date
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date expectedCheckoutDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String expectedCheckoutDateStr = dateFormat.format(expectedCheckoutDate);

        bookingDatesPayload.setCheckout(calendar.getTime());

        // Send the API request
        Response response = BookingEndPoints.createBooking(bookingPayload);

        // Validate the response
        response.then()
                .statusCode(200)
                .body("booking.bookingdates.checkout", equalTo(expectedCheckoutDateStr)); // Should be February 29, 2024
    }

    @Test(groups = {"regression"})
    public void testCheckoutDateHandlesMonthTransition() {
        // Mock the current date to be the last day of the month
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 31); // January 31, 2023
        //Date currentDate = calendar.getTime();

        // Add one day to the current date
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date expectedCheckoutDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String expectedCheckoutDateStr = dateFormat.format(expectedCheckoutDate);

        bookingDatesPayload.setCheckout(calendar.getTime());

        // Send the API request
        Response response = BookingEndPoints.createBooking(bookingPayload);

        // Validate the response
        response.then()
                .statusCode(200)
                .body("booking.bookingdates.checkout", equalTo(expectedCheckoutDateStr));  // Should be February 1, 2023
    }


}
