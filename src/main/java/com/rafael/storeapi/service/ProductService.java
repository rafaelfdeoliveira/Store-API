package com.rafael.storeapi.service;

import com.rafael.storeapi.dto.ProductDTO;
import com.rafael.storeapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductDTO> listAll(List<String> code, Pageable pageable) {
        return productRepository
                .findByCode(code.get(0), pageable)
                .map(ProductDTO::convert);
    }

    public Page<ProductDTO> listAll(Pageable pageable) {
        return productRepository
                .findAll(pageable)
                .map(ProductDTO::convert);
    }
//
//
//    public PessoaDTO criarPessoa(PessoaDTO pessoa) {
//        Pessoa pessoaBD = pessoaRepository.save(Pessoa.convert(pessoa));
//        return PessoaDTO.convert(pessoaBD);
//    }
}
