package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    private static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        HashMap<String, Object> qParam = new HashMap<String, Object>();
        qParam.put("page", "1");
        qParam.put("per_page", "25");

        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/";
        response = given()
                .queryParams(qParam)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //1.Extract the title
    @Test
    public void test001() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of title is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //2.Extract the total number of record
    @Test
    public void test002() {
        int titleSize = response.extract().path("title.size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of title is : " + titleSize);
        System.out.println("------------------End of Test---------------------------");
    }

    //3.Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4.Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> userId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records are : " + userId);
        System.out.println("------------------End of Test---------------------------");
    }

    //5.Extract the title of all the records
    @Test
    public void test005() {
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The itle of all the records are : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //6.Extract the title of all records whose user_id = 39655
    @Test
    public void test006() {
        List<String> title = response.extract().path("findAll{it.user_id = 4136850}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 4136850 are : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //7.Extract the body of all records whose id = 39654
    @Test
    public void test007() {
        List<String> body = response.extract().path("findAll{it.id = 4136850}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of body of all records whose id = 4136850 are : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
}
