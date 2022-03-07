package apiPractise;

import base_url.TestBase;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.TestCouldNotBeSkippedException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class GetRequest07 extends TestBase {
    //Among the data there are someones whose first name is "Susan"
    //URL spec01 from TestBase

    @Test
    public void get01(){
        Response response=given().spec(spec01).get("/booking?firstname=Susan&depositpaid=true"); //iyi degil

        response.prettyPrint();

        assertTrue(response.getBody().asString().contains("bookingid"));


    }

    @Test
    public void get02(){
        spec01.queryParams("firstname","Susan",
                            "depositpaid", true);   //iyi

        Response response=given().
                            spec(spec01).
                            get("/booking");

        response.prettyPrint();
        assertTrue(response.getBody().asString().contains("bookingid"));
    }
}
