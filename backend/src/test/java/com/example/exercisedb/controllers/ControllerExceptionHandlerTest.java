package com.example.exercisedb.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.example.exercisedb.constants.ErrorMessages.CONSTRAINT_VALIDATION_ERROR_MESSAGE;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ControllerExceptionHandlerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void constraintValidationException() throws Exception {
        String sizePath = "$.errors.size";
        String pagePath = "$.errors.page";
        String statusPath = "$.status";
        String messagePath = "$.message";

        int badRequeststatus = 400;
        String sizeErrorMessage = "must be greater than or equal to 1";
        String pageErrorMessage = "must be greater than or equal to 0";

        mockMvc.perform(
                get("/api/cities/queryByPage?page=-1&size=0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(sizePath, is(sizeErrorMessage)))
                .andExpect(jsonPath(pagePath, is(pageErrorMessage)))
                .andExpect(jsonPath(statusPath, is(badRequeststatus)))
                .andExpect(jsonPath(messagePath, is(CONSTRAINT_VALIDATION_ERROR_MESSAGE)));



        mockMvc.perform(
                get("/api/cities/queryByPage?page=0&size=0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(sizePath, is(sizeErrorMessage)))
                .andExpect(jsonPath(pagePath).doesNotExist())
                .andExpect(jsonPath(statusPath, is(badRequeststatus)))
                .andExpect(jsonPath(messagePath, is(CONSTRAINT_VALIDATION_ERROR_MESSAGE)));

        mockMvc.perform(
                get("/api/cities/queryByPage?page=-1&size=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(sizePath).doesNotExist())
                .andExpect(jsonPath(pagePath, is(pageErrorMessage)))
                .andExpect(jsonPath(statusPath, is(badRequeststatus)))
                .andExpect(jsonPath(messagePath, is(CONSTRAINT_VALIDATION_ERROR_MESSAGE)));
    }

}