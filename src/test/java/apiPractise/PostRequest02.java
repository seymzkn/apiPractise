package apiPractise;

import base_url.TestBase;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends TestBase{

      /*
        Post Scenario
        Accept type Json olsujn (contentType olsun demek )
        When I send a POST request
        https://restful-booker.herokuapp.com/booking
        with the request body
        {
          {
               "firstname": "Ali",
               "lastname": "Can",
               "totalprice": 500,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2022-02-01",
                   "checkout": "2022-02-11"
              },
              "additionalneeds": "Wifi"
              }
              Then status code 200
              Responce body, request body ile ayni oldugunu verify ediniz
     */

    @Test
    public void post01(){

       Response response= createRequestBodyByJsonObjectClass(); //JSONObject class kullandik

        response.prettyPrint();

        //Status code 200 olmali
        response.
                then().
                assertThat().
                statusCode(200); //bu hard assertion dir

        // JsonPath kullanarak assertion yapalim
        JsonPath json=response.jsonPath();
        SoftAssert softAssert=new SoftAssert();

        //firstname assertion
        softAssert.assertEquals(json.getString("booking.firstname"),jsonRequestBody.getString("firstname"));

        //lastname assertion
        softAssert.assertEquals(json.getString("booking.lastname"),jsonRequestBody.getString("firstname"));

        //totalprice assertion
        softAssert.assertEquals(json.getInt("booking.totalprice"),jsonRequestBody.getInt("totalprice"));

        //depositpaid assertion
        softAssert.assertEquals(json.getBoolean("booking.depositpaid"),jsonRequestBody.getBoolean("depositpaid"));

        //checkin assertion
        softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),jsonBookingDatesRequestBody.getString("checkin"));

        //checkout assertion
        softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),jsonBookingDatesRequestBody.getString("checkout"));

        //checkout assertion
        softAssert.assertEquals(json.getString("booking.additionalneeds"),jsonRequestBody.getString("depositpaid"));


        softAssert.assertAll();

    }


}
