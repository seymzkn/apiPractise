package apiPractise;
import base_url.TestBase;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends TestBase{
     /* (Klasik bir interview sorusudur )
    GSON: GSON ,
        1- Json formatindaki datalari Java objectlerine donusturur.(DE-Serialization)
        2- Java Object lerini Json formatindaki datalara donusturur. (Serialization)

     */

    @Test
    public void get01() {
        Response response = given().
                spec(spec03).
                when().
                get();

        response.prettyPrint();

        List<Map<Object,Object>>listOfMaps=response.as(ArrayList.class); //esitligin sag tarafi obje ureten taraftir bu tarafa list yazarsak sikayet eder, list class degil interface dir obje uretilemez
        System.out.println(listOfMaps);
        System.out.println(listOfMaps.get(0)); //{userId=1, id=1, title=delectus aut autem, completed=false}

        SoftAssert softAssert=new SoftAssert();

        //200 tane id oldugunu verify ediniz
        softAssert.assertTrue(listOfMaps.size()==200);
        //softAssert.assertAll();

        //121. elemanin completed degerininn true oldugunu verify ediniz
        softAssert.assertEquals(listOfMaps.get(120).get("completed"),true,"Istenen gibi degil");
        //softAssert.assertAll();

        //Sondan bir onceki elemanin title'inin "numquam repellendus a magnam" oldugunu verify edniz
        softAssert.assertEquals(listOfMaps.get(listOfMaps.size()-2).get("title"),"numquam repellendus a magnam");
        softAssert.assertAll();
    }


}
