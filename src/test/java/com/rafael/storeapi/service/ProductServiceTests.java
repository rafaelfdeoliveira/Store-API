package com.rafael.storeapi.service;

import com.rafael.storeapi.dto.ProductDTO;
import com.rafael.storeapi.model.Product;
import com.rafael.storeapi.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.eq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testListAllEmpty() {
        Pageable pageable = PageRequest.of(0, 5);

        Mockito.when(productRepository.findAll(pageable))
                .thenReturn(Page.empty());
        Page<ProductDTO> productsPage = productService.listAll(null, pageable);

        Mockito.verify(productRepository, Mockito.times(1)).findAll(pageable);
        Assertions.assertEquals(0, productsPage.getSize());
    }

    @Test
    public void testListAll() {
        Pageable pageable = PageRequest.of(0, 5);
        List<String> codes = new ArrayList<>();
        codes.add("1");
        codes.add("2");

        List<Product> productsList = new ArrayList<>();
        productsList.add(new Product(1, "1", 10.0F, 100, null));
        productsList.add(new Product(2, "2", 20.0F, 50, null));
        Page<Product> productsPage = new PageImpl<>(productsList);

        Mockito.when(productRepository.findAll(Mockito.any(Specification.class), eq(pageable)))
                .thenReturn(productsPage);
        Page<ProductDTO> productsDTOPage = productService.listAll(codes, pageable);

        Mockito.verify(productRepository, Mockito.times(1)).findAll(Mockito.any(Specification.class), eq(pageable));
        Assertions.assertEquals(2, productsDTOPage.getSize());
        Assertions.assertEquals(ProductDTO.convert(productsList), productsDTOPage.getContent());
    }

    @Test
    public void testRegisterProduct() {
        ProductDTO productDTO = new ProductDTO("4", 200.0F, 30);

        Mockito.when(productRepository.save(Mockito.any()))
                .thenReturn(Product.convert(productDTO));
        ProductDTO productReturn = productService.registerProduct(productDTO);

        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
        Assertions.assertEquals(productDTO, productReturn);
    }

    @Test
    public void testDeleteProducts() {
        List<String> codes = new ArrayList<>();
        codes.add("1");
        codes.add("2");

        List<Product> productsList = new ArrayList<>();
        productsList.add(new Product(null, "1", 10.0F, 100, null));
        productsList.add(new Product(null, "2", 20.0F, 50, null));

        Mockito.when(productRepository.findAll(Mockito.any(Specification.class)))
                .thenReturn(productsList);
        List<ProductDTO> productDTOList = productService.deleteProducts(codes);

        Mockito.verify(productRepository, Mockito.times(1)).findAll(Mockito.any(Specification.class));
        Mockito.verify(productRepository, Mockito.times(1)).deleteAll(productsList);
        Assertions.assertEquals(2, productDTOList.size());
        Assertions.assertEquals(ProductDTO.convert(productsList), productDTOList);
    }

}
