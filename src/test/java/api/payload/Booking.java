package api.payload;

import lombok.Data;

@Data
public class Booking {

    String firstname;
    String lastname;
    double totalprice;
    boolean depositpaid;
    BookingDates bookingdates;
    String additionalneeds;



}
