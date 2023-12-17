package com.practice.spring.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExternalApiCall {

    public ExternalApiCall() {
    }

    public JsonNode getIpDetails(String ipAddress) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://ip-api.com/json/" + ipAddress;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode jsonNode = mapper.readTree(response.body());
        return jsonNode;
    }
}
