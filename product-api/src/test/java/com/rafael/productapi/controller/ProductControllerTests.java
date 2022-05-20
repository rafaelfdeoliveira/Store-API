package com.rafael.productapi.controller;

import com.rafael.productapi.dto.ProductDTO;
import com.rafael.productapi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testProductPage() throws Exception {
        when(productService.listAll(Mockito.any())).thenReturn(Flux.empty());

        mockMvc.perform(
                        get("/produto"))
                .andExpect(status().isOk());

    }

    @Test
    public void testRegisterProduct() throws Exception {
        when(productService.registerProduct(Mockito.any())).thenReturn(Mono.just(new ProductDTO()));

        mockMvc.perform(post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"100\"}"))
                .andExpect(status().isOk());
    }
}
