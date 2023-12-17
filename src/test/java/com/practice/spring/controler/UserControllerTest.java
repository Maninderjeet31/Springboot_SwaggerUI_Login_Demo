package com.practice.spring.controler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.spring.entity.User;
import com.practice.spring.service.UserService;
import com.practice.spring.util.ExternalApiCall;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    UserService userService;
    @Mock
    ExternalApiCall externalApiCall;

    @Test
    void loginForm_WithValidCredentials_AndLocationInCanada_ThenReturnResponse() throws Exception {
        String username = "user2", password = "Abc_1234", ipAddress  = "24.48.0.1";
        User mockedUser = new User();
            mockedUser.setUsername(username);
            mockedUser.setPassword(password);
            mockedUser.setId(1);

        String jsonResponse = "{\"country\": \"Canada\",\"city\": \"Montreal\"}";
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Mock the behavior of the external method
        when(externalApiCall.getIpDetails(ipAddress)).thenReturn(jsonNode);
        when(userService.findByUsernameAndPassword(username, password)).thenReturn(mockedUser);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/login/username/{username}/password/{password}/ipaddress/{ipaddress}",username,password,ipAddress)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

      assertTrue(result.getResponse().getContentAsString().contains("Welcome user2 from Montreal"));
    }

    @Test
    void loginForm_WithValidCredentials_AndLocationInUS_ThenReturnNotEligible() throws Exception {
        String username = "user2", password = "Abc_1234", ipAddress  = "28.48.0.1";
        User mockedUser = new User();
        mockedUser.setUsername(username);
        mockedUser.setPassword(password);
        mockedUser.setId(1);

        String jsonResponse = "{\"country\": \"Canada\",\"city\": \"Montreal\"}";
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Mock the behavior of the external method
        when(externalApiCall.getIpDetails(ipAddress)).thenReturn(jsonNode);
        when(userService.findByUsernameAndPassword(username, password)).thenReturn(mockedUser);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/login/username/{username}/password/{password}/ipaddress/{ipaddress}",username,password,ipAddress)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("User not eligible to register."));
    }

    @Test
    void loginForm_WithInvalidCredentials_AndLocationInCanada_ThenReturnNotEligible() throws Exception {
        String username = "user2", password = "Abc_1234", ipAddress  = "28.48.0.1";
        User mockedUser = new User();
        mockedUser.setUsername(username);
        mockedUser.setPassword(password);
        mockedUser.setId(1);

        String jsonResponse = "{\"country\": \"Canada\",\"city\": \"Montreal\"}";
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Mock the behavior of the external method
        when(externalApiCall.getIpDetails(ipAddress)).thenReturn(jsonNode);
        when(userService.findByUsernameAndPassword(username, password)).thenReturn(mockedUser);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/login/username/{username}/password/{password}/ipaddress/{ipaddress}","user1","Abc_1235",ipAddress)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("User not eligible to register."));
    }

    @Test
    void loginForm_WithNullCredentials_AndLocationInCanada_ThenReturnNotEligible() throws Exception {
        String username = "user2", password = "Abc_1234", ipAddress  = "28.48.0.1";
        User mockedUser = new User();
        mockedUser.setUsername(username);
        mockedUser.setPassword(password);
        mockedUser.setId(1);

        String jsonResponse = "{\"country\": \"Canada\",\"city\": \"Montreal\"}";
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Mock the behavior of the external method
        when(externalApiCall.getIpDetails(ipAddress)).thenReturn(jsonNode);
        when(userService.findByUsernameAndPassword(username, password)).thenReturn(mockedUser);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/login/username/{username}/password/{password}/ipaddress/{ipaddress}",null,"Abc_1235",ipAddress)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertTrue(!result.getResponse().getContentAsString().contains("Welcome user2 from Montreal"));
    }

    @Test
    void loginForm_WithEmptyCredentials_AndLocationInCanada_ThenReturnNotEligible() throws Exception {
        String username = "user2", password = "Abc_1234", ipAddress  = "28.48.0.1";
        User mockedUser = new User();
        mockedUser.setUsername(username);
        mockedUser.setPassword(password);
        mockedUser.setId(1);

        String jsonResponse = "{\"country\": \"Canada\",\"city\": \"Montreal\"}";
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Mock the behavior of the external method
        when(externalApiCall.getIpDetails(ipAddress)).thenReturn(jsonNode);
        when(userService.findByUsernameAndPassword(username, password)).thenReturn(mockedUser);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/login/username/{username}/password/{password}/ipaddress/{ipaddress}","","",ipAddress)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertTrue(!result.getResponse().getContentAsString().contains("Welcome user2 from Montreal"));
    }
}