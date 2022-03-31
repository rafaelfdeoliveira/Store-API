package com.rafael.storeapi.controller;

import com.rafael.storeapi.dto.ProductDTO;
import com.rafael.storeapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> listProdutos(
            @RequestBody List<String> code,
            Pageable pageable) {
        return productService.listAll(code, pageable);
    }

    @GetMapping("/page")
    public Page<ProductDTO> listPessoas(Pageable pageable) {
        return productService.listAll(pageable);
    }
//
//    @PostMapping("/")
//    public PessoaDTO criarPessoa(@RequestBody PessoaDTO pessoa) {
//        return pessoaService.criarPessoa(pessoa);
//    }
//
//    @DeleteMapping("/{cpf}")
//    public void deletePessoa(@PathVariable String cpf) {
//        pessoaService.deletePessoa(cpf);
//    }
}
