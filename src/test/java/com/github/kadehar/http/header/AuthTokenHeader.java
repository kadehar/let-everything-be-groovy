package com.github.kadehar.http.header;

public class AuthTokenHeader implements Header {
    private static final String NAME = "X-Auth-Token";
    private static final String VALUE = "d481c322ece840108d9451d88804a61a";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String value() {
        return VALUE;
    }
}