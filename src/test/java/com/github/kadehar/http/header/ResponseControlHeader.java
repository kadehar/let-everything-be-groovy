package com.github.kadehar.http.header;

public class ResponseControlHeader implements Header {
    private static final String NAME = "X-Response-Control";
    private static final String VALUE = "minified";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String value() {
        return VALUE;
    }
}