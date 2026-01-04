package com.hapifyme.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest {
    @Test
    public void testLogin() {
        RestAssured.baseURI = "https://test.hapifyme.com";

        String payload = "{\"username\": \"student_qa\", \"password\": \"securePass123\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/login_register.php")
                .then()
                .statusCode(200);
    }
}