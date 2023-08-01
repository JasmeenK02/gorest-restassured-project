package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    private static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        HashMap<String, Object> qParam = new HashMap<String, Object>();
        qParam.put("page","1");
        qParam.put("per_page","25");

        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/";
        response = given()
                .queryParams(qParam)
                .when()
                .get("/posts")
                .then().statusCode(200);
}

    //1.Verify the if the total record is 10
    @Test
    public void test001() {
        response.body("total.size", equalTo(25));
    }

    //2.Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test002() {
        response.body("find{it.id == 57624}.title", equalTo("Tempus tenus sortitus talus voveo caritas accusator."));
    }
    //3.Check the single user_id in the Array list (5522)
    @Test
    public void test003() {
        response.body("id", hasItem(57438));
    }

    //4.Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id", hasItems(57438,57430,57423));
    }

    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”

    @Test
    public void test005(){
        response.body("find{it.id == 57624}.body",equalTo("Calculus laudantium amiculum. Deleo et votum. Aggredior spes supellex. Deinde commemoro a. Ad arceo arbor. Coniuratio adnuo supra. Censura speculum utpote. Stips vergo vindico. Viduo avarus adultus. Quo sonitus unus. Vos thesaurus ipsum. Argumentum demitto bestia. Non adeptio toties. Contego succurro taceo. Terreo accipio ambitus. Alii defigo sit."));
    }





}
