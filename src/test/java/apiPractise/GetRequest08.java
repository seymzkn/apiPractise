package apiPractise;

import base_url.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest08 extends TestBase {

     /*
   When I send a GET request to REST API URL
   https://restful-booker.herokuapp.com/booking/5
   Then HTTP Status Code 200
   And Responce format "application/JSON"
   And responce body should be like;
   And firstname "Mark"
   And lastname "Jackson"
   "totalprice" 206
   depositpaid: true
   "bookingdates":
   And checkin date "2016-12-11"
   And checkout date "2020-10-12"
    */

    @Test
    public void get01(){
        //queryParam filtrelemeye yararken pathParam responce i daraltmaya yarar
        spec01.pathParam("bookingid",5);
        Response response=given().
                            spec(spec01).
                            when().
                            get("/booking/{bookingid}");

        response.prettyPrint();

        JsonPath json=response.jsonPath();
        //neden json yaptik? jsonpath class ini kullanarak json tipi datalarin icinde rahatca dolasabiliriz
        //bu obje ile jsonpath icindeki methodlara girecegiz ve datalarin icinde seyahat etmemize yardim eder

        System.out.println(json.getString("firstname")); //Mark
        assertEquals("firstname istenilen gibi degil","Eric",json.getString("firstname"));

        System.out.println(json.getString("lastname")); //Jackson
        Assert.assertEquals("Jackson",json.getString("lastname"));

        System.out.println(json.getInt("totalprice")); //206
        Assert.assertEquals("411",json.getString("totalprice"));


        System.out.println(json.getBoolean("depositpaid")); //true
        Assert.assertEquals(true,json.getBoolean("depositpaid"));

        System.out.println(json.getString("bookingdates.checkin")); //2015-07-18
        Assert.assertEquals("2016-05-11",json.getString("bookingdates.checkin"));
        System.out.println(json.getString("bookingdates.checkout")); //2019-08-09

    }


}
