package com.vmc.manageemployee.controllers;

import com.vmc.manageemployee.controllers.jpa.DeptlocJPAController;
import com.vmc.manageemployee.dto.DepartmentLocationDTO;
import com.vmc.manageemployee.util.JsonUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeptlocJPAControllerTest {

    @Autowired
    private DeptlocJPAController deptlocJPAController;

    @Autowired
    private MockMvc mockMvc;

    public String uri = "/api/app/jpa";
    public String id = "20";
    public String signinURI = "/api/auth/signin";
    public String accessTokenHeaderKey = "Authorization";
    public String username = "admin";
    public String password = "Abc12345";

    /* test create, delete */
    DepartmentLocationDTO DepartmentLocationLocation1 = new DepartmentLocationDTO(20,
            "Ha Noi", "Ha Noi","Viet Nam"
    );
    /* test update */
    DepartmentLocationDTO DepartmentLocationLocation2 = new DepartmentLocationDTO(20,
            "Ha Long", "Quang Ninh","Viet Nam"
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

    // Load Context
//    @Test
//    @Order(1)
//    public void contextLoads() throws Exception {
//         assertThat(deptlocJPAController).isNotNull();
//    }


//    // PostID without Token
//    @Test
//    @Order(2)
//    public void givenNoToken_whenPostSecureRequest_thenUnauthorized() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.post(uri + "/departmentlocations" )
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(JsonUtils.asJsonString(DepartmentLocationLocation1)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
//    }
//
//    //Post ID with Token
//    @Test
//    @Order(3)
//    public void givenToken_whenPostSecureRequest_thenOk() throws Exception {
//
//        String accessToken = obtainAccessToken(username, password);
//        mockMvc.perform(MockMvcRequestBuilders.post(uri + "/departmentlocations")
//                        .header(accessTokenHeaderKey, accessToken)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(JsonUtils.asJsonString(DepartmentLocationLocation1)))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    // Get All without Token
//    @Test
//    @Order(4)
//    public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/departmentlocations"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
//
//    }

    //Get All with Token
    @Test
    @Order(5)
    public void givenToken_whenGetSecureRequest_thenOk() throws Exception {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/departmentlocations")
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAllDepartmentLocationAPI() throws Exception
    {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform( MockMvcRequestBuilders
                        .get(uri + "/departmentlocations")
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentlocations").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentlocations[*].locationid").isNotEmpty());

    }

    //GetByID with Token
    @Test
    @Order(6)
    public void getDepartmentLocationById() throws Exception {

        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/departmentlocations" + id)
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //Put ID with Token
    @Test
    @Order(7)
    public void putDepartmentLocationById() throws Exception {

        String accessToken = obtainAccessToken(username, password);

        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/departmentlocations" + id)
                        .header(accessTokenHeaderKey, accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.asJsonString(DepartmentLocationLocation2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //Delete by ID with Token
    @Test
    @Order(8)
    public void deleteDepartmentLocationById() throws Exception {
        String accessToken = obtainAccessToken(username, password);
        mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/departmentlocations" + id)
                        .header(accessTokenHeaderKey, accessToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
