package apiPractise;

import base_url.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends TestBase {

      /*
   When I send a GET request to REST API URL
   https://dummy.restapiexample.com/api/v1/employees
   Then HTTP Status Code 200
   1-Print all ids greater than 10 on the console(10 dan buyuk tum id elri konsola yazdir)
   Assert that there are 14 ids greater than 10 (10 dan byk 14 id oldugunu verify et)
   2-Print all ages less than 30 on the console(30 dan kucuk tum yaslari yazdir)
   Asser that max age is 23(30 dan kucuk en byuk yasin 23 oldgnu verify et)
   3- Print all employee names whose salaries are greater than 350000(maasi 3500 den cok olan iscilerin isimlerini yazdir)
   Assert that Charde Marshall is one of the employees whose salary is greater than 350,000(charde nin maasinin 350000 den buyuk oldugunu verify et)
    */


    @Test
    public void get01(){
        Response response=given().
                            spec(spec02).
                            when().
                            get();

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);

        JsonPath json=response.jsonPath();

        SoftAssert softAssert=new SoftAssert();

        //1-10 dan buyuk tum id leri konsola yazdir
        List<String>idList=json.getList("data.findAll{Integer.valueOf(it.id)>10}.id");
        System.out.println(idList);
        //verify 10 dan byk 14 id oldugunu verify et
        softAssert.assertEquals(idList.size(),14,"Eleman sayisi uyusmadi");
        softAssert.assertAll();

        //2-30 dan kucuk tum yaslari yazdir
        List<Integer>ageList=json.getList("data.findAll{(it.employee_age)<30}.employee_age");
        System.out.println(ageList);

        //30 dan kucuk en byuk yasin 23 oldgnu verify et
        Collections.sort(ageList);//[19,21,22,22,23,23] siraladi

        softAssert.assertTrue(ageList.get(ageList.size()-1)==23,"yas istenen gibi degil");
        softAssert.assertAll();

        //maasi 3500 den cok olan iscilerin isimlerini yazdir
        List<String>nameList=json.getList("data.findAll{Integer.valueOf(it.employee_salary)>350000}.employee_name");
        System.out.println(nameList);

        softAssert.assertTrue(nameList.contains("Charde Marshall"));
        //charde nin maasinin 350.000 den buyuk oldugunu verify et
        

    }
}
