package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
        //response.log().all();
    }

    //    1. Extract the title
    @Test
    public void test001() {
        List<?> listOfTitles = response.extract().path("title");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("List of titles are : " + listOfTitles);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    2. Extract the total number of record
    @Test
    public void test002() {
        Integer totalNumberOfRecords = response.extract().path("total.size()");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("Total number of records are : " + totalNumberOfRecords);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    3. Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("body[14]");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("Body of 15th record is : " + body);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<?> userIdOfAllRecords = response.extract().path("user_id");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("List of user_id of all the records : " + userIdOfAllRecords);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    5. Extract the title of all the records
    @Test
    public void test005() {
        List<?> userIdOfAllRecords = response.extract().path("title");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("List of user_id of all the records : " + userIdOfAllRecords);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    6. Extract the title of all records whose user_id = 2329050
    @Test
    public void test006() {
        String titleOfAUser_id = response.extract().path("find{it.user_id == 2329050}.title");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("Title of user_id 2329050 : " + titleOfAUser_id);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    7. Extract the body of all records whose id = 2329047
    @Test
    public void test007() {
        List<?> bodyOfAUser_id = response.extract().path("findAll{it.user_id == 2329047}.body");
        //Used List as two IDs have same user_id

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("Body of user_id 2329047 : " + bodyOfAUser_id);
        System.out.println("-------------------End of Test--------------------------");
    }
}
