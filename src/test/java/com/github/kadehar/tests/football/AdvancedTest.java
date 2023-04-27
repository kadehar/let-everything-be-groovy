package com.github.kadehar.tests.football;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.get;

public class AdvancedTest extends TestBase {
    @Test
    @DisplayName("Example 1: Extract Map of JSON Object with Find")
    public void extractListOfMapsOfElements_findAllTeamData() {
        Response response = get("competitions/WC/teams");
        Map<String,?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'Netherlands' }");
        System.out.println(allTeamDataForSingleTeam);
    }

    @Test
    @DisplayName("Example 2: Extract Single Value with Find")
    public void extractSingleValueWithFind_findACompetitionAreaNameByAnAreaCode() {
        Response response = get("teams/66/matches");
        String competitionAreaName = response.path("matches.find { it.competition.area.code == 'ENG' }.competition.area.name");
        System.out.println(competitionAreaName);
    }

    @Test
    @DisplayName("Example 3: Extract List of Values with FindAll")
    public void extractListOfValuesWithFindAll_findAllTeamsWithPLCompetition() {
        Response response = get("teams/66/matches");
        Set<String> homeTeamNames = Set.copyOf(response.path("matches.findAll { it.competition.code == 'PL' }.homeTeam.name"));
        System.out.println(homeTeamNames);
    }

    @Test
    @DisplayName("Example 4: Extract Single Value with Max")
    public void extractSingleValueWithHighestValueOfOtherElement_findHighestMatchDayId() {
        Response response = get("teams/66/matches");
        Integer highestMatchDayId = response.path("matches.max { it.matchday }.id");
        System.out.println(highestMatchDayId);
    }

    @Test
    @DisplayName("Example 5: Extract Single Value with Min")
    public void extractSingleValueWithHighestValueOfOtherElement_findLowestMatchDayId() {
        Response response = get("teams/66/matches");
        Integer lowestMatchDayId = response.path("matches.min { it.matchday }.id");
        System.out.println(lowestMatchDayId);
    }

    @Test
    @DisplayName("Example 6: Collect a list of values and sum() them")
    public void extractMultipleValuesWithCollectAndSumThem_addUpAllMatchDays() {
        Response response = get("teams/66/matches");
        Integer sumOfMatchDays = response.path("matches.collect { it.matchday }.sum()");
        System.out.println(sumOfMatchDays);
    }

    @Test
    @DisplayName("Example 7: Combine Find and FindAll in your GPath JSON Expressions")
    public void extractMapOfObjectWithFindAllAndFind_findSingleHomeTeam() {
        Response response = get("teams/66/matches");
        Map<String,?> homeTeam = response.path("matches.findAll { it.competition.code == 'PL' }.find { it.homeTeam.name == 'Manchester United FC'}");
        System.out.println(homeTeam);
    }

    @Test
    @DisplayName("Example 8: Use Parameters in your GPath JSON Expressions")
    public void extractMapOfObjectWithFindAllAndFind_findSingleHomeTeamUsingParams() {
        Response response = get("teams/66/matches");
        String competitionCode = "PL";
        String teamName = "Manchester United FC";
        Map<String,?> homeTeam = response.path("matches.findAll { it.competition.code == '%s' }.find { it.homeTeam.name == '%s'}",
                competitionCode,
                teamName);
        System.out.println(homeTeam);
    }

    @Test
    @DisplayName("Example 9: Use Multiple FindAll")
    public void extractListOfMapOfElementsWithMultipleFindAll_findAllHomeTeam() {
        Response response = get("teams/66/matches");
        String competitionCode = "PL";
        String areaCode = "ENG";
        List<Map<String,?>> allEngTeams = response.path("matches.findAll { it.competition.code == '%s' }.findAll { it.competition.area.code == '%s'}",
                competitionCode,
                areaCode);
        System.out.println(allEngTeams);
    }
}