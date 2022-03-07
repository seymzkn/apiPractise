package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.*;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.junit.Before;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TestBase {
    //protected lari butun child class lar kullanabilir

    protected RequestSpecification spec01;  //Request ile ilgili bir cok duzenleme yapmami saglar. bu bir objedir.Heap memory de su an bir konteynir olusturduk
    protected RequestSpecification spec02;

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
}
