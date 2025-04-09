package api.test;

import api.endpoints.BookingEndPoints;
import api.payload.Auth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import lombok.Getter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthTest extends BaseTest{

    Faker faker;
    Auth authPayload;

    @Getter
    static String token;

    @BeforeClass
    public void setupData() throws JsonProcessingException {

        faker = new Faker();

        // Create auth payload
        authPayload = new Auth();
        authPayload.setUsername("admin");
        authPayload.setPassword("password123");
    }


    @Test(priority = 1,groups = {"regression","smoke"})
    public void testAuthTest(){

        Response response = BookingEndPoints.createToken(authPayload);
        token = response.jsonPath().getString("token");

        response.then().statusCode(200);

//        response.prettyPrint();
//        response.then().log().all();
        System.out.println("created token: " + token);

    }
}
