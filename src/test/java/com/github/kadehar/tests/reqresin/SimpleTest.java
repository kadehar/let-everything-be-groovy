package com.github.kadehar.tests.reqresin;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class SimpleTest extends TestBase {
    @Test
    @DisplayName("Example 1: Use path() on Response")
    public void extractSingleValue_findFirstName() {
        Response response = get("users/2");
        String firstName = response.path("data.first_name");
        System.out.println(firstName);
    }

    @Test
    @DisplayName("Example 2: Specify JSONPath")
    public void extractSingleValue_findFirstName_specifyJsonPath() {
        Response response = get("users/2");
        JsonPath jsonPath = new JsonPath(response.asString());
        String firstName = jsonPath.get("data.first_name");
        System.out.println(firstName);
    }

    @Test
    @DisplayName("Example 3: Response as String")
    public void extractSingleValue_findFirstName_responseAsString() {
        String responseAsString = get("users/2").asString();
        String firstName = JsonPath.from(responseAsString).get("data.first_name");
        System.out.println(firstName);
    }

    @Test
    @DisplayName("Example 4: Single Line")
    public void extractSingleValue_findFirstName_getEverythingInOneGo() {
        String firstName = get("users/2").path("data.first_name");
        System.out.println(firstName);
    }

    @Test
    @DisplayName("Example 5: Given, When, Then")
    public void extractSingleValue_findFirstName_useAssertion() {
        //@formatter:off
        given().
                when().
                get("users/2").
                then().
                assertThat().
                body("data.first_name", equalTo("Janet"));
        //@formatter:on
    }

    @Test
    @DisplayName("Example 6: Extract First Value from Multiple Matches")
    public void extractFirstValueWhenSeveralReturned_findFirstName() {
        Response response = get("users");
        String firstName = response.path("data.first_name[0]");
        System.out.println(firstName);
    }

    @Test
    @DisplayName("Example 7: Extract Last Value from Multiple Matches")
    public void extractLastValueWhenSeveralReturned_findFirstName() {
        Response response = get("users");
        String lastName = response.path("data.first_name[-1]");
        System.out.println(lastName);
    }

    @Test
    @DisplayName("Example 8: Extract All Values into a List")
    public void extractListOfValues_findAllFirstNames() {
        Response response = get("users");
        List<String> allFirstNames = response.path("data.first_name");
        System.out.println(allFirstNames);
    }

    @Test
    @DisplayName("Example 9: Extract Multiple Maps of Objects")
    public void extractListOfMapsOfElements_findAllUsersData() {
        Response response = get("users");
        List<Map<String,?>> allUsersData = response.path("data");
        System.out.println(allUsersData);
    }
}