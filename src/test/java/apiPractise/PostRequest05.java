package apiPractise;

import base_url.Booking;
import base_url.BookingDates;
import base_url.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class PostRequest05 extends TestBase {

     /*
        Post Scenario
        Accept type Json olsujn (contentType olsun demek )
        When I send a POST request
        https://restful-booker.herokuapp.com/booking
        with the request body
        {
          {
               "firstname": "Suleyman",
               "lastname": "Alp",
               "totalprice": 123,
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

    /*
    POJO: Plain Old Java Object
     */

    @Test
    public void post01(){
        BookingDates bookingDates=new BookingDates("2022-02-01","2022-02-11");
        Booking booking=new Booking("Suleyman","Alp",123,true,bookingDates,"Wifi");

        Response response=given().
                when().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin","password123").
                body(booking).
                when().
                post("/booking");

        response.prettyPrint();

        //Status Code 200 olmali
        response.
                then().
                assertThat().
                statusCode(200); //bu hard assertion dir

        // JsonPath kullanarak assertion yapalim
        JsonPath json=response.jsonPath();


        SoftAssert softAssert=new SoftAssert();

        //firstname assertion
        softAssert.assertEquals(json.getString("booking.firstname"),booking.getFirstname());

        //lastname assertion
        softAssert.assertEquals(json.getString("booking.lastname"),booking.getLastname());

        //totalprice assertion
        softAssert.assertEquals(json.getInt("booking.totalprice"),booking.getTotalprice());

        //depositpaid assertion
        softAssert.assertEquals(json.get("booking.depositpaid"),booking.getDepositpaid());

        //checkin assertion
        softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),booking.getBookingdates().getCheckin());

        //checkout assertion
        softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),booking.getBookingdates().getCheckout());

        //checkout assertion
        softAssert.assertEquals(json.getString("booking.additionalneeds"),booking.getAdditionalneeds());


        softAssert.assertAll();

    }
}
