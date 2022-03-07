package apiPractise;

import base_url.TestBase;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.unregisterParser;
import static org.junit.Assert.assertEquals;

public class GetRequest09 extends TestBase {

    @Test
    public void get01(){
        Response response=given().
                            spec(spec02).
                            when().
                            get();

        //response.prettyPrint();

        //JsonPath objesi olusturalim

        JsonPath json=response.jsonPath();
        //Tum employee isimlerini console a yazdiriniz

        System.out.println(json.getString("data.employee_name"));
        //System.out.println(json.getList("data.employee_name"));

        //ikinci iscinin(data index 1) isminin Garrett Winters oldugunu verify(soft assertion) ediniz
        //data bir list oldugundan istenen elemana index ile ulasiriy
        //Hard Assertion ile yaptik halbuki soruda "verify" diyor. Bu yuzden soft assertion kullanmaliyiz
        assertEquals("Isim istenen gibi degil","Garrett Winters",json.getString("data[1].employee_name"));

        /*
        Soft Assertion icin 3 adim takip edilmelidir:
        1- SoftAssert class indan obje olustur
        2- objeyi kullanarak assertion yap
        3- softAssert.assertAll();
         */

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(json.getString("data[1].employee_name"),"Garrett Winters","isim istenen gibi degil");
        //softAssert.assertAll(); butun assertion larin sonuna  bunu yazmam gerek bir kere yazmamiz lazim


        //iscilerin arasinda Herrod Chandler in var oldugunu verify ediniz
        softAssert.assertTrue(json.getList("data.employee_name").contains("Herrod Chandler"));
        //softAssert.assertAll();

        //7. iscinin maasinin 137500oldugunu verify ediniz
        softAssert.assertEquals(json.getString("data[6].employee_salary"),137500);
        softAssert.assertAll();


    }

}
