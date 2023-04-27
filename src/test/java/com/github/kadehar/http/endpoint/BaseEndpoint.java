package com.github.kadehar.http.endpoint;

public class BaseEndpoint implements Endpoint {
    private static final String BASE_URL = "http://api.football-data.org";
    private static final String BASE_PATH = "/v4/";

    @Override
    public String asUrlString() {
        return BASE_URL + BASE_PATH;
    }
}
