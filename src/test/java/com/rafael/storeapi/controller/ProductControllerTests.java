package com.rafael.storeapi.controller;

import com.rafael.storeapi.dto.ProductDTO;
import com.rafael.storeapi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @WithMockUser
    @Test
    public void testProductPage() throws Exception {
        when(productService.listAll(Mockito.any(), Mockito.any())).thenReturn(Page.empty());

        mockMvc.perform(
                        get("/produto"))
                .andExpect(status().isOk());

    }

    @WithMockUser
    @Test
    public void testRegisterProduct() throws Exception {
        when(productService.registerProduct(Mockito.any())).thenReturn(new ProductDTO());

        mockMvc.perform(post("/produto").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"100\"}"))
                .andExpect(status().isOk());
    }
}
