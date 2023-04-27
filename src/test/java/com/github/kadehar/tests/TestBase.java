package com.github.kadehar.tests;

import com.github.kadehar.http.endpoint.BaseEndpoint;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = new BaseEndpoint().asUrlString();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Accept", "application/xml")
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .setDefaultParser(Parser.XML) //не работает с включенным логированием ответа
                .build();
    }
}