package api.payload;

import lombok.Data;

import java.util.Date;

@Data
public class BookingDates {
    Date checkin;
    Date checkout;

}
