package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class UserAssertionTest {

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

    //1. Verify the if the total record is 20
    @Test
    public void Test001() {
        response.body("total.size", equalTo(20));
    }

    //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void Test002() {
        response.body("findAll{it.id == 4123647}", hasItem(hasEntry("name","Deepesh Pandey")));
    }


    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003() {
        response.body("name", hasItem("Sharmila Gandhi"));
    }


    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan
    //Guha, Karthik Dubashi IV)
    // (Dr. Lakshmi Sinha,Tejas Nayar,Udit Agarwal)
    @Test
    public void test004() {
        response.body("name", hasItems("Dr. Lakshmi Sinha", "Tejas Nayar", "Udit Agarwal"));
    }


    //5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void Test005() {
        response.body("find{it.id == 4123642}.email",equalTo("kin_butt@baumbach-skiles.example"));
    }
    //6. Verify the status is “Active” of user name is “Shanti Bhat V”

    @Test
    public void Test006() {
        response.body("find{it.name == 'Dr. Lakshmi Sinha'}.status", equalTo("active"));
    }


    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void Test007() {
        response.body("find{it.name == 'Deepesh Pandey'}.gender", equalTo("male"));
    }

}
