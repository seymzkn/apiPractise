package apiPractise;

import base_url.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest06 extends TestBase {
    //TestBase in cocugu oldugu icin ordaki tum methodlari inherit edebilir
    //TestBase Class olusturup her teste kullanilan datalari TestBase class'a koyup
    //tekrar tekrar ayni seyleri yazmaktan kurtulacagiz. Selenium da webDriver gibi,
    // her test class da ayni seyleri tekrardan yazmayacagiz

    /*
   Positive Scenario:
   When I send a GET request to REST API URL
   https://restful-booker.herokuapp.com/booking/5
   Then HTTP Status Code 200
   And Accept type "application/JSON
   And Responce format "application/JSON"
   And firstname "Eric"
   And lastname "Brown"
   And checkin date "2020-08-18"
   And checkout date "2022-02-04"
   {
    "firstname": "Eric",
    "lastname": "Brown",
    "totalprice": 969,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2020-08-18",
        "checkout": "2022-02-04"
    },
    "additionalneeds": "Breakfast"
    }

    */
    @Test
    public void get01(){
        Response response=given().
                            spec(spec01).
                            when().
                            get("/booking/5");

        response.prettyPrint();
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).body("firstname", equalTo("Susan"),
                "lastname",equalTo("Brown"),
                "totalprice",equalTo(122),
                "depositpaid",equalTo(true),
                "bookingdates.checkin",equalTo("2017-05-19"),
                "bookingdates.checkout",equalTo("2017-05-19"),
                "additionalneeds",equalTo("Breakfast"));
    }









}
