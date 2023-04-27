package com.github.kadehar.utils;

import com.github.kadehar.http.header.Header;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Converter {
    public Map<String, String> toHeaders(List<Header> headerList) {
        return headerList.stream()
                .collect(Collectors.toMap(Header::name, Header::value));
    }
}
