package com.rafael.productapi.service;

import com.rafael.productapi.dto.ProductDTO;
import com.rafael.productapi.model.Product;
import com.rafael.productapi.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testListAllEmpty() {
        Mockito.when(productRepository.findAll())
                .thenReturn(Flux.empty());
        Flux<ProductDTO> productsFlux = productService.listAll(null);
        List<ProductDTO> productsList = productsFlux.collectList().block();

        Mockito.verify(productRepository, Mockito.times(1)).findAll();
        assert productsList != null;
        Assertions.assertEquals(0L, productsList.size());
    }

//    @Test
//    public void testListAll() {
//        List<String> codes = new ArrayList<>();
//        codes.add("1");
//        codes.add("2");
//
//        List<Product> productsList = new ArrayList<>();
//        productsList.add(new Product(UUID.randomUUID(), "1", 10.0F, 100, null));
//        productsList.add(new Product(UUID.randomUUID(), "2", 20.0F, 50, null));
//
//        Mockito.when(productRepository.findAllByCode(Mockito.any()))
//                .thenReturn(Flux.fromStream(productsList.stream()));
//        Flux<ProductDTO> productsDTOFlux = productService.listAll(codes);
//
//        Mockito.verify(productRepository, Mockito.times(2)).findAllByCode(Mockito.any());
//        List<ProductDTO> productDTOList = productsDTOFlux.collectList().block();
//        assert productDTOList != null;
//        Assertions.assertEquals(2, productDTOList.size());
//        Assertions.assertEquals(productsList.stream().map(ProductDTO::convert), productDTOList.stream());
//    }

    @Test
    public void testRegisterProduct() {
        ProductDTO productDTO = new ProductDTO("4", 200.0F, 30);

        Mockito.when(productRepository.save(Mockito.any()))
                .thenReturn(Mono.just(Product.convert(productDTO)));
        Mono<ProductDTO> productReturn = productService.registerProduct(productDTO);

        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
        Assertions.assertEquals(productDTO, productReturn.block());
    }

//    @Test
//    public void testDeleteProducts() {
//        List<String> codes = new ArrayList<>();
//        codes.add("1");
//        codes.add("2");
//
//        List<Product> productsList = new ArrayList<>();
//        productsList.add(new Product(UUID.randomUUID(), "1", 10.0F, 100, null));
//        productsList.add(new Product(UUID.randomUUID(), "2", 20.0F, 50, null));
//
//        Mockito.when(productRepository.findAllByCode(Mockito.any()))
//                .thenReturn(Flux.fromStream(productsList.stream()));
//        Flux<ProductDTO> productDTOFlux= productService.deleteProducts(codes);
//
//        Mockito.verify(productRepository, Mockito.times(4)).findAllByCode(Mockito.any());
//        Mockito.verify(productRepository, Mockito.times(1)).deleteAll(Mockito.anyIterable());
//        List<ProductDTO> productDTOList = productDTOFlux.collectList().block();
//        assert productDTOList != null;
//        Assertions.assertEquals(2, productDTOList.size());
//        Assertions.assertEquals(ProductDTO.convert(Flux.fromStream(productsList.stream())).collectList().block(), productDTOList);
//    }

}
