package com.practice.spring.controler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.spring.entity.User;
import com.practice.spring.service.UserService;
import com.practice.spring.util.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService theUserService) {
        this.userService = theUserService;
    }

    @GetMapping("/users/login/username/{username}/password/{password}/ipaddress/{ipaddress}")
    public ResponseEntity<String> loginForm(@PathVariable(value = "username") String username,
                            @PathVariable(value = "password") String password,
                            @PathVariable(value = "ipaddress") String ipaddress) throws IOException, InterruptedException {

        User accFoundResponse = userService.findByUsernameAndPassword(username, password);
        JsonNode jsonNode = getIpDetails(ipaddress);
        String countryName = jsonNode.get("country").asText();
        String cityName = jsonNode.get("city").asText();

        if((accFoundResponse != null) && countryName.equals("Canada")){
            return ResponseEntity.ok(" ID: " + new GenerateUUID().generateRandomID() +  "\n Welcome " + accFoundResponse.getUsername() + " from " + cityName);
        } else {
            return ResponseEntity.badRequest().body("User not eligible to register.");
        }
    }

    private static JsonNode getIpDetails(String ipAddress) throws IOException, InterruptedException {
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
