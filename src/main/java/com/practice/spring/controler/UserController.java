package com.practice.spring.controler;

import com.fasterxml.jackson.databind.JsonNode;
import com.practice.spring.entity.User;
import com.practice.spring.service.UserService;
import com.practice.spring.util.ExternalApiCall;
import com.practice.spring.util.GenerateUUID;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService theUserService) {
        this.userService = theUserService;
    }

    @GetMapping("/users/login/username/{username}/password/{password}/ipaddress/{ipaddress}")
    public ResponseEntity<String> loginForm(@Valid @PathVariable(value = "username") String username,
                            @Valid @PathVariable(value = "password") String password,
                            @PathVariable(value = "ipaddress") String ipaddress) throws IOException, InterruptedException {

        User accFoundResponse = userService.findByUsernameAndPassword(username, password);
        JsonNode jsonNode = new ExternalApiCall().getIpDetails(ipaddress);
        String countryName = jsonNode.get("country").asText();
        String cityName = jsonNode.get("city").asText();

        if((accFoundResponse != null) && countryName.toLowerCase().equals("canada")){
            return ResponseEntity.ok(" ID: " + new GenerateUUID().generateRandomID() +  "\n Welcome " + accFoundResponse.getUsername() + " from " + cityName);
        } else {
            return ResponseEntity.badRequest().body("User not eligible to register.");
        }
    }
}
