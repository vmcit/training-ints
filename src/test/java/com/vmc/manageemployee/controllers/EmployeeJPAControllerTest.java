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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeJPAControllerTest {

    @Autowired
    private EmployeeJPAController employeeJPAController;

    @Autowired
    private MockMvc mockMvc;

    private String uri = "/api/app/jpa";
    private String id = "1";
    private String signinURI = "/api/auth/signin";
    private String accessTokenHeaderKey = "Authorization";
    
    /* user name and pasword admin */
    private String username = "admin";
    private String password = "Abc12345";

    /* test create, delete */
    EmployeeDTO employee1 = new EmployeeDTO(20,"abcxyz", "Male","10-01-2021",
            1
    );
    /* test update */
    EmployeeDTO employee2 = new EmployeeDTO(20,"xyztt", "Male","10-01-1999",
            1
    );

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

    @Test
    @Order(1)
    public void contextLoads() throws Exception {
        assertThat(employeeJPAController).isNotNull();
    }

    //Get All with Token
    @Test
    @Order(5)
    public void givenToken_whenGetSecureRequest_thenOk() throws Exception {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/employees")
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAllEmployeeAPI() throws Exception
    {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform( MockMvcRequestBuilders
                        .get(uri + "/employees")
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // GetByID with Token
    @Test
    @Order(6)
    public void getEmployeeById() throws Exception {

        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/employees/" + id)
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //Put ID with Token
    @Test
    @Order(7)
    public void putEmployeeById() throws Exception {

        String accessToken = obtainAccessToken(username, password);

        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/departments/" + employee2.getDepartment_id() +"/employees/" + id)
                        .header(accessTokenHeaderKey, accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.asJsonString(employee2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //Delete by ID with Token
    @Test
    @Order(8)
    public void deleteEmployeeById() throws Exception {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/departments/" + employee2.getDepartment_id() +"/employees/" + id)
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
