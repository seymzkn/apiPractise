package apiPractise;

import base_url.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends TestBase {

    /*
    Post Request olusturmak icin gerekenler:
        1- EndPoint sart
        2- Request body sart
        3- Authorization sart
        4- Accept Type istege baglidir.Bazen kullanilir, bazen kullanilmaz
        5- Content Type istege baglidir.Bazen kullanilir, bazen kullanilmaz

        GET ile POST Request'lerin farklari nedir?(Interview)
        1-GET Request olusturmak icin sadece EndPoint yeterlidir,
        POT Request olusturmak icin Endpoint yaninda Request body gereklidir
        2- Get data cekmek icin, Post yeni data olusturmak icin kullanilir.

        NOTE:   API Developer'lar olusturulacak datanin bazi bolumlerinin bos birakilmamasina karar vermislerse
                POST Request olustururken kesinlikle o kisimlara deger verilerek POST Request olusturulmalidir.
                Eger buna dikkat etmezseniz 400 Bad Request status code alirsiniz.


        NOTE:   API Developer'lar olusturulacak datanin bazi bolumlerinin tekrarli olmamasina tekrarli a karar vermislerse
                POST Request olustururken kesinlikle o kisimlara degerler verilerek POST Request olusturulmamalidir.
                Eger buna dikkat etmezseniz 400 Bad Request status code alirsiniz.

     */

    /*
    Post Scenario
    Accept type Json olsujn (contentType olsun demek )
    When I send a POST request
    https://restful-booker.herokuapp.com/booking
    with the request body
    {
      {
           "firstname": "Veli",
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

    //1. Way : Iyi degil

    @Test
            public void post01(){
        String jsonRequestBody=" {\n" +
                "\"firstname\": \"Ali\",\n" +
                "\"lastname\": \"Can\",\n" +
                "\"totalprice\": 500,\n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2022-02-01\",\n" +
                "\"checkout\": \"2022-02-11\"\n" +
                "},\n" +
                "\"additionalneeds\": \"Wifi\"\n" +
                "}";

        Response response=given().
                            contentType(ContentType.JSON).  //contentType: Icerik tipi, DataBase e yolladigim datanin icerigi JSON olsun diyorum
                                                            // post'da given() dan sonra contentType(ContentType.JSON) kullan
                            spec(spec01).
                            auth().basic("admin","password123").
                            body(jsonRequestBody).
                            when().
                            post("/booking");

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
