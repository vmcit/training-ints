package com.vmc.manageemployee.controllers;


import com.vmc.manageemployee.controllers.jpa.EmployeeJPAController;
import com.vmc.manageemployee.dto.EmployeeDTO;
import com.vmc.manageemployee.util.JsonUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeJPAControllerTest {

    @Autowired
    private EmployeeJPAController deptJPAController;

    @Autowired
    private MockMvc mockMvc;

    public String uri = "/api/app/jpa";
    public String id = "1";
    public String signinURI = "/api/auth/signin";
    public String accessTokenHeaderKey = "Authorization";
    public String username = "admin";
    public String password = "Abc12345";

    EmployeeDTO Employess = new EmployeeDTO();

    private String obtainAccessToken(String username, String password) throws Exception {
        String contentAsString = String.format("{ \"username\":\"%s\", \"password\":\"%s\" }", username, password);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(signinURI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(contentAsString))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        return resultActions.andReturn().getResponse().getHeader(accessTokenHeaderKey);
    }

    // Load Context
    @Test
    @Order(1)
    public void contextLoads() throws Exception {
        // assertThat(deptJPAController.getClass()).isNotNull();
    }

    @Test
    public void getAllEmployeesAPI() throws Exception
    {

    }

    //GetByID with Token
    @Test
    @Order(6)
    public void getEmployessById() throws Exception {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.get(uri + id)
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //Put ID with Token
    @Test
    @Order(7)
    public void putEmployessById() throws Exception {

        String accessToken = obtainAccessToken(username, password);

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .header(accessTokenHeaderKey, accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.asJsonString(Employess)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //Delete by ID with Token
    @Test
    @Order(8)
    public void deleteEmployessById() throws Exception {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.delete(uri + id)
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
