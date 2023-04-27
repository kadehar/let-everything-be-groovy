package com.github.kadehar.tests.reqresin;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class AdvancedTest extends TestBase {
    @Test
    @DisplayName("Example 1: Extract Map of JSON Object with Find")
    public void extractListOfMapsOfElements_findAllUserDataForSingleUser() {
        Response response = get("users");
        Map<String,?> allUserDataForSingleUser = response.path("data.find { it.first_name == 'Eve' }");
        System.out.println(allUserDataForSingleUser);
    }

    @Test
    @DisplayName("Example 2: Extract Single Value with Find")
    public void extractSingleValueWithFind_findAUserByAnId() {
        Response response = get("users");
        String certainUser = response.path("data.find { it.id == 4 }.first_name");
        System.out.println(certainUser);
    }

    @Test
    @DisplayName("Example 3: Extract List of Values with FindAll")
    public void extractListOfValuesWithFindAll_findAllUsersWithIdGreaterThan3() {
        Response response = get("users");
        List<String> userNames = response.path("data.findAll { it.id > 3 }.first_name");
        System.out.println(userNames);
    }

    @Test
    @DisplayName("Example 4: Extract Single Value with Max")
    public void extractSingleValueWithHighestValueOfOtherElement_findEmailWithHighestLength() {
        Response response = get("users");
        String email = response.path("data.max { it.email.length() }.email");
        System.out.println(email);
    }

    @Test
    @DisplayName("Example 5: Extract Single Value with Min")
    public void extractSingleValueWithHighestValueOfOtherElement_findEmailWithLowestLength() {
        Response response = get("users");
        String email = response.path("data.min { it.email.length() }.email");
        System.out.println(email);
    }

    @Test
    @DisplayName("Example 6: Collect a list of values and sum() them")
    public void extractMultipleValuesWithCollectAndSumThem_addUpAllIds() {
        Response response = get("users");
        Integer sumOfIds = response.path("data.collect { it.id }.sum()");
        System.out.println(sumOfIds);
    }

    @Test
    @DisplayName("Example 7: Combine Find and FindAll in your GPath JSON Expressions")
    public void extractMapOfObjectWithFindAllAndFind_findSingleUser() {
        Response response = get("users");
        Map<String,?> user = response.path("data.findAll { it.email =~/.*?@reqres.in/ }.find { it.first_name == 'Eve'}");
        System.out.println(user);
    }

    @Test
    @DisplayName("Example 8: Use Parameters in your GPath JSON Expressions")
    public void extractMapOfObjectWithFindAllAndFind_findSingleUserUsingParams() {
        Response response = get("users");
        String domain = "@reqres.in";
        String firstName = "Eve";
        Map<String,?> user = response.path("data.findAll { it.email =~/.*?%s/ }.find { it.first_name == '%s'}",
                domain, firstName);
        System.out.println(user);
    }

    @Test
    @DisplayName("Example 9: Use Multiple FindAll")
    public void extractListOfMapOfElementsWithMultipleFindAll_findAllUsers() {
        Response response = get("users");
        String domain = "@reqres.in";
        String emailLength = "21";
        List<Map<String,?>> users = response.path("data.findAll { it.email =~/.*?%s/ }.findAll { it.email.length() > %s }",
                domain, emailLength);
        System.out.println(users);
    }
}