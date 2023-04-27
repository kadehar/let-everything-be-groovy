package com.github.kadehar.tests;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;

public class SimpleTest extends TestBase {
    @Test
    @DisplayName("Example 1: Get Single XML Element Value")
    public void getSingleXmlElementValue_getVideoGameName() {
        Response response = get("/videogames.xml");
        String name = response.path("videoGames.videoGame.name[0]");
        System.out.println(name);
    }

    @Test
    @DisplayName("Example 2: Get List of XML Element Values")
    public void getListOfElementValues_getAllVideoGameNames() {
        String responseAsString = get("/videogames.xml").asString();
        List<String> allNames = XmlPath.from(responseAsString).getList("videoGames.videoGame.name");
        System.out.println(allNames);
    }

    @Test
    @DisplayName("Example 3: Get Single Xml Attribute")
    public void getSingleXmlAttribute_getVideoGameCategory() {
        Response response = get("/videogames.xml");
        String category = response.path("videoGames.videoGame[0].@category");
        System.out.println(category);
    }
}