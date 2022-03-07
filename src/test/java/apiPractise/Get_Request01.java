package apiPractise;

import org.junit.Test;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class Get_Request01 {

    //Rest-Assured kullanrak API Testing yapilir
    //Rest-Assured Gherkin language kullanir. given, then.and

    @Test
    public void getMethod01(){

        given().
                when(). //when den sonra method kullanilir, postman de gordugumuz methodlar
                get("https://restful-booker.herokuapp.com"). //postman deki get "" icinde endpoint i yazmaliyiz.get icin sadece endpoint gerekliydi
                then(). // dogrulamaya yarar then den sonra her zaman assert yapariz
                assertThat(). //sunu dogrula demek
                statusCode(200); //status cod un 200 oldugunu dogrula

    }

    // get ile aldigim datayi console da gormek istiyorum
    @Test
    public void getMethod02(){
        //datayi responce konteynirina attik
        Response response= given().when().get("https://restful-booker.herokuapp.com/booking/3");
        //get methoduyla beraber endpoint kullanmak zorundayiz

        //postman de gordugumuz datanin aynisini burada gormus olacagiz
        //Responce body i console a yazdirmak icin response.prettyPrint();
        response.prettyPrint();

        //statuscode u console da gormek icin
        System.out.println("Status code: " + response.getStatusCode());

        //statusline i console da gormek icin;
        System.out.println("Status line: " + response.getStatusLine());

        //sureyi yazdirmak icin
        System.out.println("Test Zamani: " + response.time());

        //Responce body deki datanin content(icerik) type i(xml mi json mi text mi) console da gormeden anlamamiza yarar
        //1. Yol
        System.out.println("Content Type : " + response.getContentType()); //application/json; charset=utf-8... utf-8 ingiliz alfabesinden demek
        //2. Yol
        System.out.println(response.getHeader("Content-Type"));

        //Headers daki tum bilgileri alalim
        System.out.println(response.getHeaders()); //Serevr=Cowboy ile baslayan yeri getirdi

        //Headers'dan istenen spesific bir datayi almak

        System.out.println(response.getHeader("Date")); //Wed, 02 Mar 2022 10:58:42 GMT

        //Assertion yapalim--> assertThat() hard assertion oluyor.Ilk hatada code execution durur ve hata raporu verilir.
        //Ilk hatadan sonraki kodlar calistirilmaz
        //Biz statuscode da hard assrt. kullaniriz eger hata varsa kodun devami calismasin diye

        //1. Status code 200
        response.then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK").contentType("application/json; charset=utf-8");

        //2.
    }

}
