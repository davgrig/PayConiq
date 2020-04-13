package Gists.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;

import io.restassured.response.ResponseBody;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import testMethods.gistData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class crudPositiveTests extends baseTest{

    private static final Logger LOG = LoggerFactory.getLogger(crudPositiveTests.class);
    private static ThreadLocal<String> gistId = new ThreadLocal<>();

    @Test
    @Order(1)
    public void createGist() {
        ResponseBody createdGist = restAssuredBaseRequest()
            .body(gistData.createDefaultGistJsonObject().toString())
            .when().post("/gists")
            .then().assertThat().statusCode(201)
            .assertThat().body("files.\"hello_world.py\".filename", equalTo("hello_world.py"))
            .assertThat().body("files.\"hello_world.rb\".filename", equalTo("hello_world.rb"))
            .assertThat().body("files.\"hello_world_python.txt\".filename", equalTo("hello_world_python.txt"))
            .assertThat().body("files.\"hello_world_ruby.txt\".filename", equalTo("hello_world_ruby.txt"))
            .assertThat().body("files.\"hello_world.py\".type", equalTo("application/x-python"))
            .assertThat().body("files.\"hello_world.rb\".type", equalTo("application/x-ruby"))
            .assertThat().body("files.\"hello_world_ruby.txt\".type", equalTo("text/plain"))
            .assertThat().body("files.\"hello_world_python.txt\".type", equalTo("text/plain"))
            .assertThat().body("files.\"hello_world.py\".language", equalTo("Python"))
            .assertThat().body("files.\"hello_world.rb\".language", equalTo("Ruby"))
            .assertThat().body("files.\"hello_world_ruby.txt\".language", equalTo("Text"))
            .assertThat().body("files.\"hello_world.py\".size", equalTo(199))
            .assertThat().body("files.\"hello_world.rb\".size", equalTo(167))
            .assertThat().body("files.\"hello_world_ruby.txt\".size", equalTo(46))
            .assertThat().body("files.\"hello_world_python.txt\".size", equalTo(48))
            .assertThat().body("files.\"hello_world_python.txt\".content",
                equalTo("Run `python hello_world.py` to print Hello World"))
            .assertThat().body("files.\"hello_world_ruby.txt\".content",
                equalTo("Run `ruby hello_world.rb` to print Hello World"))
            .assertThat().body("files.\"hello_world.py\".truncated", equalTo(false))
            .assertThat().body("files.\"hello_world.rb\".truncated", equalTo(false))
            .assertThat().body("files.\"hello_world_ruby.txt\".truncated", equalTo(false))
            .assertThat().body("files.\"hello_world_python.txt\".truncated", equalTo(false))
            .assertThat().body("owner.login", equalTo("davgrig"))
            .assertThat().body("public", equalTo(true))
            .assertThat().body("comments", equalTo(0))
            .assertThat().body(matchesJsonSchemaInClasspath("gistJsonSchema.json"))
            .extract().response().getBody();
         gistId.set(createdGist.jsonPath().get("id").toString());
        LOG.info(gistId.get());
    }

    @Test
    @Order(2)
    public void getGist () {
        restAssuredBaseRequest()
            .when().get("gists/" + gistId.get())
            .then().assertThat().statusCode(200)
            .assertThat().body("id", equalTo(gistId.get()))
            .assertThat().body("files.\"hello_world.py\".filename", equalTo("hello_world.py"))
            .assertThat().body("files.\"hello_world.rb\".filename", equalTo("hello_world.rb"))
            .assertThat().body("files.\"hello_world_python.txt\".filename", equalTo("hello_world_python.txt"))
            .assertThat().body("files.\"hello_world_ruby.txt\".filename", equalTo("hello_world_ruby.txt"))
            .assertThat().body("files.\"hello_world.py\".type", equalTo("application/x-python"))
            .assertThat().body("files.\"hello_world.rb\".type", equalTo("application/x-ruby"))
            .assertThat().body("files.\"hello_world_ruby.txt\".type", equalTo("text/plain"))
            .assertThat().body("files.\"hello_world_python.txt\".type", equalTo("text/plain"))
            .assertThat().body("files.\"hello_world.py\".language", equalTo("Python"))
            .assertThat().body("files.\"hello_world.rb\".language", equalTo("Ruby"))
            .assertThat().body("files.\"hello_world_ruby.txt\".language", equalTo("Text"))
            .assertThat().body("files.\"hello_world.py\".size", equalTo(199))
            .assertThat().body("files.\"hello_world.rb\".size", equalTo(167))
            .assertThat().body("files.\"hello_world_ruby.txt\".size", equalTo(46))
            .assertThat().body("files.\"hello_world_python.txt\".size", equalTo(48))
            .assertThat().body("files.\"hello_world_python.txt\".content",
                equalTo("Run `python hello_world.py` to print Hello World"))
            .assertThat().body("files.\"hello_world_ruby.txt\".content",
                equalTo("Run `ruby hello_world.rb` to print Hello World"))
            .assertThat().body("files.\"hello_world.py\".truncated", equalTo(false))
            .assertThat().body("files.\"hello_world.rb\".truncated", equalTo(false))
            .assertThat().body("files.\"hello_world_ruby.txt\".truncated", equalTo(false))
            .assertThat().body("files.\"hello_world_python.txt\".truncated", equalTo(false))
            .assertThat().body("owner.login", equalTo("davgrig"))
            .assertThat().body("public", equalTo(true))
            .assertThat().body("comments", equalTo(0))
            .assertThat().body(matchesJsonSchemaInClasspath("gistJsonSchema.json"));
    }

    @Test
    @Order(3)
    public void updateGist () {
        restAssuredBaseRequest()
            .body(gistData.getUpdatedDefaultGistJsonObject().toString())
            .when().patch("gists/" + gistId.get())
            .then().assertThat().statusCode(200)
            .assertThat().body("id", equalTo(gistId.get()))
            .assertThat().body("files.\"hello_world.md\".filename", equalTo("hello_world.md"))
            .assertThat().body("files.\"new_file.txt\".filename", equalTo("new_file.txt"))
            .assertThat().body("files.\"new_file.txt\".content", equalTo("new content"))
            .assertThat().body("files.\"hello_world.rb\".filename", equalTo("hello_world.rb"))
            .assertThat().body("files.\"hello_world_ruby.txt\".filename", equalTo("hello_world_ruby.txt"))
            .assertThat().body("files", not(hasKey("hello_world_python.txt")))
            .assertThat().body("files.\"hello_world.md\".content", equalTo("updated content"));
    }

    @Test
    @Order(3)
    public void deleteGist () {
        restAssuredBaseRequest()
            .when().delete("gists/" + gistId.get())
            .then().assertThat().statusCode(204);
        restAssuredBaseRequest()
            .when().get("gists/" + gistId.get())
            .then().assertThat().statusCode(404);
    }
}
