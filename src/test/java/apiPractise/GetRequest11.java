package apiPractise;

import base_url.TestBase;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest11 extends TestBase {
    /* (Klasik bir interview sorusudur )
    GSON: GSON ,
        1- Json formatindaki datalari Java objectlerine donusturur.(DE-Serialization)
        2- Java Object lerini Json formatindaki datalara donusturur. (Serialization)

        Ben kodlarimi java formatinda yaziyorum fakat api de kullanilan dil json formatinda.
        Json datasini GSON alir Map seklinde Java ya iletir.(De-Serialization)
        Java da yazila kodlarda map seklinde update edilir, GSON vasitasiyla Java kodlari Json sekline donusturulerek APIye iletirilir(Serialization)

        NOTE: GSON ile ayni isi yapan ObjectMapper class'da var
     */

    @Test
    public void get01(){
        Response response=given().
                            spec(spec03).
                            when().
                            get("/2");

        response.prettyPrint(); //cikan sonuc json formatta ben bunu map e cevirecegim

        HashMap<String,String> map=response.as(HashMap.class);          //as(HashMap.class);//De-Serialization Json i aldim map yaptim hashMap siraya dikkat etmez hizlidir
        System.out.println(map);

        System.out.println(map.keySet()); //bana butun key leri verir  [id, completed, title, userId]
        System.out.println(map.values()); //value leri getirir [2, false, quis ut nam facilis et officia qui, 1]

        //completed key'sinin degerinin false oldugunu verify(softAssert) ediniz
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(map.get("completed"),false,"false olmaliydi ama degil");
        //softAssert.assertAll();

        //userId, id ve title degerlerini verify ediniz
        softAssert.assertEquals(map.get("userId"),1);
        softAssert.assertEquals(map.get("id"),2);
        softAssert.assertEquals(map.get("title"),"quis ut nam facilis et officia qui");
        softAssert.assertAll();
    }
}
