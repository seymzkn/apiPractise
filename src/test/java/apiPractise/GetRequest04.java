package apiPractise;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest04 {

    /*
    Positive Scenario:
    When I send a GET request to REST API URL
    http://dummy.restapiexample.com/api/v1/employees
    And Accept type "application/JSON" dir
    Then
    HTTP Status Code 200
    And Responce in format "application/JSON"
    And should be 24 employees
    And "Ashton Cox" should be one of the employees
    And 21,61,23 should be among the employee ages
     */

    @Test
    public void get01(){
        String url="http://dummy.restapiexample.com/api/v1/employees";

        Response response=given().
                accept(ContentType.JSON).
                when().
                get(url);

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).contentType(ContentType.JSON).//"application/JSON" kullanilir ama bu daha iyi
                body("data.id", Matchers.hasSize(24),     //data sayisini sorarlarsa hasSize kullanilir.data.id lerden bir list olusturuyor Match ortustur ayni mi diyor
                "data.employee_name",Matchers.hasItem("Ashton Cox"), //item eleman demek "Ashton Cox" elemanina sahip mi diyor. tum name leri alip "Ashton Cox" var mi diye bakiyor
                "data.employee_age",Matchers.hasItems(21,61,23));
    }
}
