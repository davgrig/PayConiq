package Gists.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testMethods.gistData;

public class crudNegativeTests extends baseTest {
    private static final Logger LOG = LoggerFactory.getLogger(crudPositiveTests.class);
    private static ThreadLocal<String> gistId = new ThreadLocal<>();

    @Test
    public void createGistWithMissingFiles() {
        restAssuredBaseRequest()
            .body(gistData.getJsonObjectWithoutFile().toString())
            .when().post("/gists")
            .then().assertThat().statusCode(422)
            .assertThat().body("message", containsString("Invalid request."));
    }

    @Test
    public void createGistWithMissingAccessLevel() {
        restAssuredBaseRequest()
            .body(gistData.getJsonObjectWithIncorrectFiles().toString())
            .when().post("/gists")
            .then().assertThat().statusCode(422)
            .assertThat().body("message", containsString("Invalid request."));
    }

    @Test
    public void getNonExistingGist() {
        restAssuredBaseRequest()
            .when().get("/gists/nonExistingId")
            .then().assertThat().statusCode(404)
            .assertThat().body("message", containsString("Not Found"));

    }

    @Test
    public void deleteNonExistingGist() {
        restAssuredBaseRequest()
            .when().delete("/gists/nonExistingId")
            .then().assertThat().statusCode(404)
            .assertThat().body("message", containsString("Not Found"));

    }

    @Test
    public void updateNonExistingGist() {
        restAssuredBaseRequest()
            .body(gistData.createDefaultGistJsonObject().toString())
            .when().patch("/gists/foo")
            .then().assertThat().statusCode(404)
            .assertThat().body("message", containsString("Not Found"));
    }

    @Test
    public void sendUnauthorizedRequest() {
        given()
            .body(gistData.createDefaultGistJsonObject().toString())
            .when().post("/gists")
            .then().assertThat().statusCode(401)
            .assertThat().body("message", containsString("Requires authentication"));
    }

    @Test
    public void sendRequestWithWrongToken() {
        given()
            .header("Authorization", "Bearer nonExistingToken")
            .when().get("/gists")
            .then().assertThat().statusCode(401)
            .assertThat().body("message", containsString("Bad credentials"));
    }

}
