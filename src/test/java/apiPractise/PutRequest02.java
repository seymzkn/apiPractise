package apiPractise;

import base_url.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class PutRequest02 extends TestBase {

    /*
    spec03 kullamarak herhangi bir datayi update ediniz
    Update edildigini status code ve response body ile verify ediniz
     */

    @Test
    public void put02(){
        Response response=given().spec(spec03).when().get("/100");

        response.prettyPrint();

        JSONObject jsonObject=new JSONObject();

        jsonObject.put("userId",10);
        jsonObject.put("id",107);
        jsonObject.put("title","Alp");
        jsonObject.put("completed",true);

        Response responseAfterPut=given().
                contentType(ContentType.JSON).
                spec(spec03).
                body(jsonObject.toString()).
                when().
                put("/100");

        responseAfterPut.prettyPrint();

        responseAfterPut.
                then().
                assertThat().
                statusCode(200); //201 de olabilir

        JsonPath json=responseAfterPut.jsonPath();
        SoftAssert softAssert=new SoftAssert();

        //completed degerini verify ediniz
        softAssert.assertEquals(json.getBoolean("completed"),jsonObject.get("completed"));

        //title degerini verify ediniz
        softAssert.assertEquals(json.getString("title"),jsonObject.get("title"));

        //userId degerini verify ediniz
        softAssert.assertEquals(json.getInt("userId"),jsonObject.get("userId"));

        softAssert.assertAll();


    }
}
