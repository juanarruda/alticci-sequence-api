package org.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class AlticciRestTest {

    @Test
    public void testGetAlticciSeqCase1() {
        given()
                .when().get("/alticci/0")
                .then()
                .statusCode(200)
                .body(is("" + 0));
    }

    @Test
    public void testGetAlticciSeqCase2() {
        given()
                .when().get("/alticci/1")
                .then()
                .statusCode(200)
                .body(is("" + 1));
    }

    @Test
    public void testGetAlticciSeqCase3() {
        given()
                .when().get("/alticci/6")
                .then()
                .statusCode(200)
                .body(is("" + 3));
    }

    @Test
    public void testGetAlticciSeqException() {
        given()
                .when().get("/alticci/-1")
                .then()
                .statusCode(400)
                .body(is("O índice deve ser um valor natural (inteiro e positivo, incluindo o zero)!"));
    }

    @Test
    public void testGetAlticciArrayCase1() {
        ArrayList mockResponse =  new ArrayList<>();
        mockResponse.add(0);
        given()
                .when().get("/alticci/array?end=0&start=0")
                .then()
                .statusCode(200)
                .body(is(""+mockResponse));
    }

    @Test
    public void testGetAlticciArrayException() {
        given()
                .when().get("/alticci/array?end=2&start=3")
                .then()
                .statusCode(400)
                .body(is("Os valores devem ser inteiros e positivos com o final maior que o início!"));
    }
}