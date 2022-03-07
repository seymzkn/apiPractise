package apiPractise;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest05 {
        /*
    Positive Scenario:
    When I send a GET request to REST API URL
    https://restful-booker.herokuapp.com/booking/5
     Then
    HTTP Status Code 200
    And Responce in format "application/JSON"
    And "firstname" should be "Susan"
    And "totalprice" should be 168
    And "checkin" should be "2017-09-11"
     */

    @Test
    public void get05(){
        String url="https://restful-booker.herokuapp.com/booking/7";

        Response response=given().when().get(url);

        response.prettyPrint();

        //HTTP Status Code 200
        //And Responce in format "application/JSON"

        // And "firstname" should be "Sally"
        // And "totalprice" should be 971
        // And "checkin" should be "2015-08-14"


        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo("Susan"),
                        "totalprice",equalTo(168),
                        "bookingdates.checkin",equalTo("2017-09-11"));


    }
}
