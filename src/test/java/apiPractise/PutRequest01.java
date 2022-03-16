package apiPractise;

import base_url.TestBase;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutRequest01 extends TestBase {

    @Test
    public void put01(){

        Response response=given().
                            spec(spec03).
                            when().
                            get("/200");

        response.prettyPrint();

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("title","Seyma");
        jsonObject.put("userId",88);
        jsonObject.put("id",544);
        jsonObject.put("completed", true);

        //Put icin mutlaka endpoint ve body lazim. API yi yazan kisi mutlaka content type koyman lazim demisse koymak zorundayiz
        Response responseAfterPut=given().
                                    contentType(ContentType.JSON).
                                    spec(spec03).
                                    body(jsonObject.toString()).
                                    when().
                                    put("/200");

        responseAfterPut.prettyPrint();

    }

}
