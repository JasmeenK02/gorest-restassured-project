package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserExtractionTest {

    private static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        HashMap<String, Object> qParam = new HashMap<String, Object>();
        qParam.put("page","1");
        qParam.put("per_page","20");

        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/";
        response = given()
                .queryParams(qParam)
                .when()
                .get("/users")
                .then().statusCode(200);
    }
    //1. Extract the All Ids
    @Test
    public void test001() {
        List<Integer> id = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Ids : " + id);
        System.out.println("------------------End of Test---------------------------");
    }


    //2.Extract the all Names
    @Test
    public void test002() {
        List<String> allNames = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Names are : " + allNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //3.Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4.Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose 'status = inactive' are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //5.Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose 'status = active' are : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

    //6.Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> names = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose 'gender = female' are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //7.Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<String> emails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The emails of the object where status = inactive are : " + emails);
        System.out.println("------------------End of Test---------------------------");
    }

    //8.Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<Integer> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of the object where status = inactive are : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //9.Get all the status
    @Test
    public void test009() {
        List<String> status = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the status are : " + status);
        System.out.println("------------------End of Test---------------------------");
    }

    //10.Get email of the object where name = Himadri Naik
    @Test
    public void test010() {
        String email = response.extract().path("find{it.name == 'Nalini Bhat'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email of the object where name = Nalini Bhat is : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    //11.Get gender of id = 2272603
    @Test
    public void test011() {
        String gender = response.extract().path("find{it.id == 4123642}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ender of id = 4123642 is : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }
}
