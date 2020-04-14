package Gists.api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class baseTest {
    private static final Logger LOG = LoggerFactory.getLogger(crudPositiveTests.class);
    private static String token = "d5df34d5919b2bf6321121281092cec6230077a3";
    private static java.lang.String baseUrl = "https://api.github.com";


    protected synchronized RequestSpecification restAssuredBaseRequest() {
        return given()
            .header("Authorization", "Bearer " + token);
    }

    @BeforeAll
    public static void setBaseUrl () {
        RestAssured.baseURI = baseUrl;
    }
}
