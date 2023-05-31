package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
        //response.log().all();

    }

    //    1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("total.size()", equalTo(20));
    }

    //    2. Verify if the name of id = 2329063 is equal to ”Deepan Gill”
    @Test
    public void test002() {
        response.body("[6].name", equalTo("Deepan Gill"));
    }

    //    3. Check the single ‘Name’ in the Array list (Daksha Bharadwaj)
    @Test
    public void test003() {
        response.body("findAll{it.id}.name", hasItem("Daksha Bharadwaj"));
    }

    //    4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void test004() {
        response.body("findAll{it.id}.name", hasItems("Daksha Bharadwaj", "Vishwamitra Abbott", "Giriraj Chopra"));
    }

    //    5. Verify the email of userid = 2329060 is equal “johar_abhaidev@welch.test”
    @Test
    public void test005() {
        response.body("findAll{it.id == 2329060}.email", hasItem("johar_abhaidev@welch.test"));
    }

    //    6. Verify the status is “Active” of user name is “Siddarth Varman”
    @Test
    public void test006() {
        response.body("findAll{it.name == 'Siddarth Varman'}.status", hasItem("active"));
    }

    //    7. Verify the Gender = male of user name is “Abhaidev Johar”
    @Test
    public void test007() {
        response.body("findAll{it.name == 'Abhaidev Johar'}.gender", hasItem("male"));
    }

}
