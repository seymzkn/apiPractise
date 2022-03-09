package apiPractise;
import base_url.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends TestBase{

    @Test
    public void get01() {
        Response response = given().
                spec(spec02).
                when().
                get();

        response.prettyPrint();

        //Ilk 5 ismin Tiger Nixon,Garrett Winters,Ashton Cox, Cedric Kelly,Airi Satou  oldugunu verify ediniz

        //1.Yol ama tavsiye edilmez
        JsonPath json=response.jsonPath(); //bu objeyi olusturarak json formati icinde gezecegim
        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(json.getString("data[0].employee_name"),"Tiger Nixon");
        softAssert.assertEquals(json.getString("data[1].employee_name"),"Garrett Winters");
        softAssert.assertEquals(json.getString("data[2].employee_name"),"Ashton Cox");
        softAssert.assertEquals(json.getString("data[3].employee_name"),"Cedric Kelly");
        softAssert.assertEquals(json.getString("data[4].employee_name"),"Airi Satou");

        //softAssert.assertAll();


        //2. Yol olabilir
        List<String>isimList=new ArrayList<>();
        isimList.add("Tiger Nixon");
        isimList.add("Garrett Winters");
        isimList.add("Ashton Cox");
        isimList.add("Cedric Kelly");
        isimList.add("Airi Satou");

        for (int i=0; i<isimList.size(); i++) {
            softAssert.assertEquals(json.getString("data["+ i + "].employee_name"),isimList.get(i));
        }

        //softAssert.assertAll();

        //3. Yol en iyisi
        List<Map>actualList=json.getList("data");   //[{id=1, employee_name=Tiger Nixon, employee_salary=320800, employee_age=61, profile_image=}, {id=2, employee_name=Garrett Winters, employee_salary=170750, employee_age=63, profile_image=},.....
        System.out.println(actualList);

        Map<Integer,String>expectedMap=new HashMap<>();
        expectedMap.put(0,"Tiger Nixon");
        expectedMap.put(1,"Garrett Winters");
        expectedMap.put(2,"Ashton Cox");
        expectedMap.put(3,"Cedric Kelly");
        expectedMap.put(4,"Airi Satou");

        System.out.println(expectedMap);    //{0=Tiger Nixon, 1=Garrett Winters, 2=Ashton Cox, 3=Cedric Kelly, 4=Airi Satou}

        for (int i = 0; i <expectedMap.size() ; i++) {
            softAssert.assertEquals(actualList.get(i).get("employee_name"),expectedMap.get(i));
            softAssert.assertAll();
        }

    }
}
