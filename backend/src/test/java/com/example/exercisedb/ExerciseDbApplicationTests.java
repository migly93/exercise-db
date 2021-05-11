package com.example.exercisedb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ExerciseDbApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void idsLisApi() throws Exception {
        String lisPath = "$.lis";
        this.mockMvc.perform(
                get("/api/cities/ids/lis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath(lisPath).isArray())
                .andExpect(jsonPath(lisPath).isNotEmpty());
    }

    @Test
    void citiesApi() throws Exception {
        String contentPath = "$.content";
        String totalPagesPath = "$.totalPages";
        String totalElementsPath = "$.totalElements";

        this.mockMvc.perform(
                get("/api/cities/queryByPage?page=0&size=5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath(contentPath).isArray())
                .andExpect(jsonPath(contentPath).isNotEmpty())
                .andExpect(jsonPath(totalPagesPath).isNumber())
                .andExpect(jsonPath(totalElementsPath).isNumber());
    }

}
