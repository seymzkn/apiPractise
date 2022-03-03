package apiPractise;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get_Request02 {

    /*
    Positive Scenario:
    when() Bir GET Request asagida verilen Endpointe yollanir
    https://restful-booker.herokuapp.com
    And() Accept Type'i "application/json" dir.
    then() statuc code 200 dur.
    and() content type "application/json" dir.

     */

    @Test
    public void get01(){
        given().
                accept("application/json").
                when().
                get("https://restful-booker.herokuapp.com/booking").
                then().
                assertThat().
                statusCode(200).
                contentType("application/json");

    }

    /*
    Negative Scenario:
    when() Bir GET Request asagida verilen Endpointe yollanir
    https://restful-booker.herokuapp.com/1001
    And() Accept Type'i "application/json" dir.
    then() statuc code 404 dur.

    */

    @Test
    public void get02(){
        Response response=given().accept("application/json").when().get("https://restful-booker.herokuapp.com/booking/1001");

        response.prettyPrint();

        response.then().assertThat().statusCode(404);

    }

    /*
    Negative Scenario:
    when() Bir GET Request asagida verilen Endpointe yollanir
    https://restful-booker.herokuapp.com/1001
    And() Accept Type'i "application/json" dir.
    then() statuc code 404 dur.
    and() Responce body de "Not Found var"
    and() Responce body de "Seyma" yok

    */

    @Test
    public void get03(){
        Response response=given().when().get("https://restful-booker.herokuapp.com/booking/1001");

        response.prettyPrint();

        assertEquals(404,response.getStatusCode());
        assertTrue(response.asString().contains("Not Found"));
        assertFalse(response.asString().contains("Seyma"));

    }




}
