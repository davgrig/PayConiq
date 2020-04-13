package Gists.api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class baseTest {
    private static final Logger LOG = LoggerFactory.getLogger(crudPositiveTests.class);
    private static String token = "bed811dc90ab6a8ed74d7dd2a10649a759583bc5";
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
