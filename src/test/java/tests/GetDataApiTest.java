package tests;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.path.json.JsonPath;
import org.aspectj.weaver.IHasSourceLocation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;


public class GetDataApiTest {

    @Test
    public void testResponseCode() {
        given().
                when().
                      get("https://reqres.in/api/users?page=2")
                .then()
                      .assertThat()
                      .statusCode(200)
                .and()
                      .contentType(ContentType.JSON)
                .and()
                      .body("data[0].id", equalTo(7), "data[5].first_name", equalToIgnoringCase("rachel"))
                .and()
                      .body("data[0].email", containsString("@"));

    }

}
