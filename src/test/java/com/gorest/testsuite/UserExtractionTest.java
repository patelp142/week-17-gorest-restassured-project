package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
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

    //    1. Extract the All Ids
    @Test
    public void test001() {
        List<?> listOfIds = response.extract().path("id");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    2. Extract the all Names
    @Test
    public void test002() {
        List<?> listOfNames = response.extract().path("name");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("List of names are : " + listOfNames);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    3. Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("name[4]");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("Name of 5th object is : " + name);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<?> inactiveUserName = response.extract().path("findAll{it.status == 'inactive'}.name");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("List of names having inactive status : " + inactiveUserName);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<?> genderOfActiveUsers = response.extract().path("findAll{it.status == 'active'}.gender");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("List of gender with active status : " + genderOfActiveUsers);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<?> nameOfFemaleGender = response.extract().path("findAll{it.gender == 'female'}.name");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("List of names for female gender : " + nameOfFemaleGender);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<?> emailOfInactiveUser = response.extract().path("findAll{it.status == 'inactive'}.email");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("List of email of inactive users : " + emailOfInactiveUser);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<?> idOfMaleUser = response.extract().path("findAll{it.gender == 'male'}.id");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("List of email of inactive users : " + idOfMaleUser);
        System.out.println("-------------------End of Test--------------------------");
    }
    //    9. Get all the status

    @Test
    public void test009() {
        List<?> status = response.extract().path("status");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("Status of all users : " + status);
        System.out.println("-------------------End of Test--------------------------");
    }

    //    10. Get email of the object where name = Kirti Pothuvaal
    @Test
    public void test010() {
        String emailOfAUser = response.extract().path("find{it.name == 'Kirti Pothuvaal'}.email");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("Email of Kirti Pothuvaal : " + emailOfAUser);
        System.out.println("-------------------End of Test--------------------------");
    }
    //    11. Get gender of id = 2329061
    @Test
    public void test011() {
        String genderOfAUserById = response.extract().path("find{it.id == 2329061}.gender");

        System.out.println("------------------Starting Test-------------------------");
        System.out.println("Gender of 2329061 ID : " + genderOfAUserById);
        System.out.println("-------------------End of Test--------------------------");
    }
}
