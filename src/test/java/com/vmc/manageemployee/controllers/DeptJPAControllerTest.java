package com.vmc.manageemployee.controllers;

import com.vmc.manageemployee.controllers.jpa.DeptJPAController;
import com.vmc.manageemployee.dto.DepartmentDTO;
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
public class DeptJPAControllerTest {


    @Autowired
    private DeptJPAController deptJPAController;

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
    DepartmentDTO department1 = new DepartmentDTO ( 1, "IT","IT",1
    );
    /* test update */
    DepartmentDTO department2 = new DepartmentDTO(1, "Nhan su","quan ly",1);

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
        assertThat(deptJPAController).isNotNull();
    }

    //Get All with Token
    @Test
    @Order(2)
    public void givenToken_whenGetSecureRequest_thenOk() throws Exception {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/departments")
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAllDepartmentAPI() throws Exception
    {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform( MockMvcRequestBuilders
                        .get(uri + "/departments")
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // GetByID with Token
    @Test
    @Order(3)
    public void getDepartmentById() throws Exception {

        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/departments/" + id)
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Post with Token
    @Test
    @Order(4)
    public void postDepartmentById() throws Exception {

        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.post(uri + "/departmentlocations/" + department1.getLocation_id()  +"/departments")
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //Put ID with Token
    @Test
    @Order(5)
    public void putDepartmentById() throws Exception {

        String accessToken = obtainAccessToken(username, password);

        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/departmentlocations/" + 1 + "/departments/" + id)
                        .header(accessTokenHeaderKey, accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.asJsonString(department2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //Delete by ID with Token
    @Test
    @Order(6)
    public void deleteDepartmentById() throws Exception {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/departmentlocations/" + 1 + "/departments/" + id)
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
