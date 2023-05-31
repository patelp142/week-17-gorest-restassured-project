package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
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

    //    1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("total.size()", equalTo(25));
    }

    //    2. Verify the if the title of id = 40093 is equal to ”Adficio vox vinum usus utpote patrocinor repudiandae.”
    @Test
    public void test002() {
        response.body("findAll{it.id == 40093}.title", hasItem("Adficio vox vinum usus utpote patrocinor repudiandae."));
    }

    //    3. Check the single user_id in the Array list (2329062)
    @Test
    public void test003() {
        response.body("findAll{it.id}.user_id", hasItem(2329062));
    }

    //    4. Check the multiple ids in the ArrayList (40081, 40075,40067)
    @Test
    public void test004() {
        response.body("findAll{it.id}.id", hasItems(40081, 40075, 40067));
    }

    //    5. Verify the body of user_id = 2329073 is equal “Nihil dedecor vicissitudo. Careo ustulo appello. Degenero curvus officiis. Censura vita teneo. Comis a subvenio. Cur testimonium velum. Viridis cognatus solium. Sufficio aeger velit. Thermae ea constans. Cogito thorax carbo. Id capillus arbustum. Confero adnuo ipsam. Vultuosus dicta vulgus."”
    @Test
    public void test005() {
        response.body("findAll{it.user_id == 2329073}.body", hasItem("Nihil dedecor vicissitudo. Careo ustulo appello. Degenero curvus officiis. Censura vita teneo. Comis a subvenio. Cur testimonium velum. Viridis cognatus solium. Sufficio aeger velit. Thermae ea constans. Cogito thorax carbo. Id capillus arbustum. Confero adnuo ipsam. Vultuosus dicta vulgus."));
    }

}
