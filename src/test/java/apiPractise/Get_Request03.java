package apiPractise;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.junit.Assert.assertEquals;

public class Get_Request03 {
    /*
    Positive Scenario:
    When I send a GET request to REST API URL
    https://restful-booker.herokuapp.com/booking/1
    And Accept type "application/JSON" dir
    Then
    HTTP Status Code 200
    And Responce format "application/JSON"
    And firstname "Sally"
    And lastname "Jones"
    And checkin date "2016-10-25"
    And checkout date "2020-06-22"
     */

    @Test
    public void get01(){
        String url="https://restful-booker.herokuapp.com/booking/1";
        Response response=given().accept(ContentType.JSON).when().get(url);

        response.prettyPrint();

        //Status code icin 1. Yol;
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname", Matchers.equalTo("Sally")).
                body("lastname",Matchers.equalTo("Jones")).
                body("totalprice",Matchers.equalTo(738)).
                body("bookingdates.checkin",Matchers.equalTo("2016-10-25"));

        //Status code icin 2.Yol
        assertEquals(200,response.getStatusCode());

        //Tekrarli body kullanmadan nasil yapilir
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname", Matchers.equalTo("Sally"),
                        "lastname",Matchers.equalTo("Jones"),
                        "totalprice",Matchers.equalTo(738),
                        "bookingdates.checkin",Matchers.equalTo("2016-10-25"));


    }


}
