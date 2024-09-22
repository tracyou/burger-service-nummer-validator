package com.example.bsnvalidator;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(BsnValidatorController.class)
class BsnValidatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BsnValidator bsnValidator;


    @Test
    void testGetIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    void testSubmitValid() throws Exception {
        String validBsn = "474862872";

        Mockito.when(bsnValidator.isValid(validBsn)).thenReturn(true);

        mockMvc.perform(post("/sent")
                        .param("input", validBsn))
                .andExpect(view().name("index"))
                .andExpect(model().attribute("bsn", validBsn))
                .andExpect(model().attributeDoesNotExist("error"));
    }

    @Test
    void testSubmitInvalid() throws Exception {
        String validBsn = "11111111";

        Mockito.when(bsnValidator.isValid(validBsn)).thenThrow(RuntimeException.class);

        mockMvc.perform(post("/sent")
                        .param("input", validBsn))
                .andExpect(view().name("index"))
                .andExpect(model().attribute("bsn", validBsn));
    }
}
