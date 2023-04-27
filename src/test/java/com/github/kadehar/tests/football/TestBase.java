package com.github.kadehar.tests.football;

import com.github.kadehar.http.endpoint.BaseEndpoint;
import com.github.kadehar.http.header.AuthTokenHeader;
import com.github.kadehar.http.header.ResponseControlHeader;
import com.github.kadehar.utils.Converter;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = new BaseEndpoint().asUrlString();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeaders(
                        new Converter()
                                .toHeaders(
                                        List.of(
                                                new ResponseControlHeader(),
                                                new AuthTokenHeader()
                                        )
                                )
                )
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .build();
    }
}