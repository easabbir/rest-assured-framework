package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Usertests {

    Faker faker;
    User userPayload;
    @BeforeClass
    public void setupData(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testPostUser(){
        Response response= UserEndPoints.createUser(userPayload);
        response.then()
                .log().all();

        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2)
    public void testGetUserByName(){
        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then()
                .log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUser(){

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then()
                .log().all();

        //checking data after update
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then()
                .log().all();

        }

    @Test(priority = 4)
    public void testDeleteUser(){
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then()
                .log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }


}
