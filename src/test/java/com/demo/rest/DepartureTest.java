package com.demo.rest;

import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.lessThan;

public class DepartureTest extends BaseTest {
    @Test
    public void checkDepartureEndpointStatus() {
        prepareGet(DEPARTURE_ALL).statusCode(SC_OK);
    }

    @Test
    public void checkSchemaValidity() {
        prepareGet(DEPARTURE_ALL)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/departure_schema.json"));
    }

    @Test
    public void checkResponseTimeAll() {
        prepareGet(DEPARTURE_ALL)
                .time(lessThan(ENDPOINT_RESPONSE_TIME));
    }

    @Test
    public void checkResponseTimeById() {
        prepareGet(DEPARTURE_ALL_BY_ID)
                .time(lessThan(ENDPOINT_RESPONSE_TIME));
    }

    /*
     * Negative testing section
     */
    @Test
    public void checkPutMethod() {
        preparePut(DEPARTURE_ALL_BY_ID, DUMMY_TEST_JSON)
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void checkPostMethod() {
        preparePost(DEPARTURE_ALL, DUMMY_TEST_JSON)
                .then()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void checkDeleteMethod() {
        prepareDelete(DEPARTURE_ALL_BY_ID)
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }
}
