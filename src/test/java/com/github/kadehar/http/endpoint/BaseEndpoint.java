package com.github.kadehar.http.endpoint;

public class BaseEndpoint implements Endpoint {
    private static final String BASE_URL = "https://gist.github.com/kadehar";
    private static final String BASE_PATH = "/148a3fd1ab04b0bebd8b7b2e45b4478f/raw/a626a9410d852052bf5ed13397a164451ec2e892";

    @Override
    public String asUrlString() {
        return BASE_URL + BASE_PATH;
    }
}