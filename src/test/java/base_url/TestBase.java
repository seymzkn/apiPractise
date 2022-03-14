package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.*;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.json.JSONObject;
import org.junit.Before;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestBase {
    //protected lari butun child class lar kullanabilir

    protected RequestSpecification spec01;  //Request ile ilgili bir cok duzenleme yapmami saglar. bu bir objedir.Heap memory de su an bir konteynir olusturduk
    protected RequestSpecification spec02;
    protected RequestSpecification spec03;

    @Before
    public void setUp01() {

        //objeye deger atamasi yapalim. bu asagida yapilan Url olustur spec01 in icine koy ben artik spec01 kullanacagim diyorum.
        //Url adi ilerde degistiginde 100 class da degisim yapmak yerine bu class a gelip bir kere degistirmek yeterli olacaktir.
        //bu setup methodu her test methodundan once kullanacagiz bu yuzden @Before (JUnit)dedik
        //https://restful-booker.herokuapp.com bu base URL dir

        spec01 = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();


    }

    @Before
    public void setUp02() {

        spec02 = new RequestSpecBuilder().
                setBaseUri("https://dummy.restapiexample.com/api/v1/employees").
                build();

    }

    @Before
    public void setUp03() {

        spec03 = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/todos").
                build();

    }

    protected Response createRequestBodyByJsonObjectClass(){

        JSONObject jsonBookingDatesRequestBody=new JSONObject();
        jsonBookingDatesRequestBody.put("checkin","2022-02-01");
        jsonBookingDatesRequestBody.put("checkout","2022-02-11");

        JSONObject jsonRequestBody=new JSONObject();
        jsonRequestBody.put("firstname","Ali");
        jsonRequestBody.put("lastname","Can");
        jsonRequestBody.put("totalprice",500);
        jsonRequestBody.put("depositpaid",true);
        jsonRequestBody.put("bookingdates",jsonBookingDatesRequestBody); //bookingdates in value su bir Json
        jsonRequestBody.put("additionalneeds","Wifi");

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin","password123").
                body(jsonRequestBody).      //body methodu icinde hep string ister ama bu string degil json..string e cevirmek icin toString() methodu kullaniriz
                when().
                post("/booking");


        return response;
    }

    protected Response createRequestBodyByMap(){
        Map<String,String>bookingDatesMap=new HashMap<>();
        bookingDatesMap.put("checkin","2022-02-01");
        bookingDatesMap.put("checkout","2022-02-11");

        Map <String,Object>requestBodyMap=new HashMap<>();
        requestBodyMap.put("firstname","Ali");
        requestBodyMap.put("lastname","Can");
        requestBodyMap.put("totalprice",500);
        requestBodyMap.put("depositpaid",true);
        requestBodyMap.put("bookindates",bookingDatesMap);
        requestBodyMap.put("additionalneeds","Wifi");

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin","password123").
                body(requestBodyMap.toString()).
                when().
                post("/booking");

        response.prettyPrint();

        return response;

    }


}
