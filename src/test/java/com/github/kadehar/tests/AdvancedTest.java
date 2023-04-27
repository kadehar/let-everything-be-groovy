package com.github.kadehar.tests;

import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class AdvancedTest extends TestBase {
    @Test
    @DisplayName("Example 1: Get List of XML Nodes")
    public void getListOfXmlNodes_getAllResults() {
        String responseAsString = get("/videogames.xml").asString();
        List<Node> allResults = XmlPath.from(responseAsString).get("videoGames.videoGame.findAll { element -> return element }");
        System.out.println(allResults.get(2).get("name").toString());
    }

    @Test
    @DisplayName("Example 2: Get List of XML Nodes by .findAll() on an Attribute")
    public void getListOfXmlNodesByFindAllOnAttribute_getAllDrivingGames() {
        String responseAsString = get("/videogames.xml").asString();
        List<Node> allDrivingGames = XmlPath.from(responseAsString)
                .get("videoGames.videoGame.findAll { game -> def category = game.@category; category == 'Driving' }");
        System.out.println(allDrivingGames.get(0).get("name").toString());
    }

    @Test
    @DisplayName("Example 3: Get List of Map of Attributes")
    public void getListOfMapOfAttributes_getAllAttributesOfCertainGames() {
        String responseAsString = get("/videogames.xml").asString();
        List<Map<String,?>> attributesOfGamesWithReviewScore90 = XmlPath
                .from(responseAsString)
                .get("**.findAll { node -> node.reviewScore == '91' }*.attributes()");
        System.out.println(attributesOfGamesWithReviewScore90);
    }

    @Test
    @DisplayName("Example 4: Get Single Node with .find() on an Element")
    public void getSingleNode_getSingleVideoGameData() {
        String responseAsString = get("/videogames.xml").asString();
        Node videoGame = XmlPath.from(responseAsString).get("videoGames.videoGame.find { videoGame -> def name = videoGame.name; name == 'Tetris' }");
        String videoGameName = videoGame.get("name").toString();
        System.out.println(videoGameName);
    }

    @Test
    @DisplayName("Example 5: Get Multiple Nodes in a List")
    public void getMultipleNodesInAList_getAllVideoGamesData() {
        String responseAsString = get("/videogames.xml").asString();
        List<Node> allVideoGames = XmlPath.from(responseAsString).get("videoGames.videoGame.findAll { videoGame -> def id = videoGame.id.toFloat(); id > 0 }");
        String nameOfThirdGame = allVideoGames.get(2).get("name").toString();
        System.out.println(nameOfThirdGame);
    }

    @Test
    @DisplayName("Example 6: Collect All Elements")
    public void collectAllElements_getAllReviewScores() {
        String responseAsString = get("/videogames.xml").asString();
        List<Integer> allReviewScores = XmlPath.from(responseAsString).get("videoGames.videoGame.collect { it.reviewScore }");
        System.out.println(allReviewScores);
    }

    @Test
    @DisplayName("Example 7: Get Single Element with Depth First Search")
    public void getSingleElementWithDepthFirstSearch_getReviewScoreOfAGame() {
        String responseAsString = get("/videogames.xml").asString();
        int reviewScore = XmlPath.from(responseAsString).getInt("**.find { it.name == 'Gran Turismo 3' }.reviewScore");
        System.out.println(reviewScore);
    }

    @Test
    @DisplayName("Example 8: Get all Nodes Based on a Condition")
    public void getAllNodesBasedOnAConditionUsingParameter_getVideoGamesOverCertainReviewScore() {
        String responseAsString = get("/videogames.xml").asString();
        int reviewScore = 90;
        List<Node> allVideoGamesOverCertainScore = XmlPath.from(responseAsString).get("videoGames.videoGame.findAll { it.reviewScore.toFloat() >= " + reviewScore + " }");
        allVideoGamesOverCertainScore.forEach(node -> {
            System.out.println("{");
            System.out.println("\tid: " + node.get("id") + ",");
            System.out.println("\tname: " + node.get("name") + ",");
            System.out.println("\treleaseDate: " + node.get("releaseDate") + ",");
            System.out.println("\treviewScore: " + node.get("reviewScore"));
            System.out.println("}");
        });
    }
}