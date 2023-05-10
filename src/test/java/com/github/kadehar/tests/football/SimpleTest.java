package com.github.kadehar.tests.football;

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
    public void extractSingleValue_findSingleTeamName() {
        Response response = get("teams/66");
        String teamName = response.path("name");
        System.out.println(teamName);
    }

    @Test
    @DisplayName("Example 2: Specify JSONPath")
    public void extractSingleValue_findSingleTeamName_specifyJsonPath() {
        Response response = get("teams/66");
        JsonPath jsonPath = new JsonPath(response.asString());
        String teamName = jsonPath.get("name");
        System.out.println(teamName);
    }

    @Test
    @DisplayName("Example 3: Response as String")
    public void extractSingleValue_findSingleTeamName_responseAsString() {
        String responseAsString = get("teams/66").asString();
        String teamName = JsonPath.from(responseAsString).get("name");
        System.out.println(teamName);
    }

    @Test
    @DisplayName("Example 4: Single Line")
    public void extractSingleValue_findSingleTeamName_getEverythingInOneGo() {
        String teamName = get("teams/66").path("name");
        System.out.println(teamName);
    }

    @Test
    @DisplayName("Example 5: Given, When, Then")
    public void extractSingleValue_findSingleTeamName_useAssertion() {
        // @formatter:off
        given()
        .when()
            .get("teams/66")
        .then()
            .assertThat()
            .body("name", equalTo("Manchester United FC"));
        // @formatter:on
    }

    @Test
    @DisplayName("Example 6: Extract First Value from Multiple Matches")
    public void extractFirstValueWhenSeveralReturned_findFirstTeamName() {
        Response response = get("competitions/WC/teams");
        String firstTeamName = response.path("teams.name[0]");
        System.out.println(firstTeamName);
    }

    @Test
    @DisplayName("Example 7: Extract Last Value from Multiple Matches")
    public void extractLastValueWhenSeveralReturned_findFirstTeamName() {
        Response response = get("competitions/WC/teams");
        String lastTeamName = response.path("teams.name[-1]");
        System.out.println(lastTeamName);
    }

    @Test
    @DisplayName("Example 8: Extract All Values into a List")
    public void extractListOfValues_findAllTeamNames() {
        Response response = get("competitions/WC/teams");
        List<String> allTeamNames = response.path("teams.name");
        System.out.println(allTeamNames);
    }

    @Test
    @DisplayName("Example 9: Extract Multiple Maps of Objects")
    public void extractListOfMapsOfElements_findAllTeamData() {
        Response response = get("competitions/WC/teams");
        List<Map<String, ?>> allTeamData = response.path("teams");
        System.out.println(allTeamData);
    }
}