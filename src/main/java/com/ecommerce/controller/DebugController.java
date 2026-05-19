package com.ecommerce.controller;

import com.ecommerce.repository.CategoriaRepository;
import com.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DebugController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/debug")
    @ResponseBody
    public String debug() {
        long categorias = categoriaRepository.count();
        long produtos = produtoRepository.count();
        
        return String.format("Categorias: %d, Produtos: %d", categorias, produtos);
    }
}