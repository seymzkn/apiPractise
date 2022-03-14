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

       Response response= createRequestBodyByJsonObjectClass();

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
        softAssert.assertEquals(json.getString("booking.firstname"),"Ali");

        //lastname assertion
        softAssert.assertEquals(json.getString("booking.lastname"),"Can");

        //totalprice assertion
        softAssert.assertEquals(json.getInt("booking.totalprice"),500);

        //depositpaid assertion
        softAssert.assertEquals(json.getBoolean("booking.depositpaid"),true);

        //checkin assertion
        softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),"2022-02-01");

        //checkout assertion
        softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),"2022-02-11");

        //checkout assertion
        softAssert.assertEquals(json.getString("booking.additionalneeds"),"Wifi");


        softAssert.assertAll();

    }


}
