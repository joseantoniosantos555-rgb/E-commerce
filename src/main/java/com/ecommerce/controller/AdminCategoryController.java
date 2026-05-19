package com.ecommerce.controller;

import com.ecommerce.entity.*;
import com.ecommerce.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias/novo")
    public String novaCategoria(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario == null || usuario.getTipoUsuario() != Usuario.TipoUsuario.ADMIN) {
            return "redirect:/login";
        }
        
        model.addAttribute("categoria", new Categoria());
        return "admin-categoria-form";
    }

    @PostMapping("/categorias/salvar")
    public String salvarCategoria(@RequestParam String nome,
                                 @RequestParam String descricao,
                                 RedirectAttributes redirectAttributes) {
        
        try {
            Categoria categoria = new Categoria();
            categoria.setNome(nome);
            categoria.setDescricao(descricao);
            
            categoriaRepository.save(categoria);
            redirectAttributes.addFlashAttribute("sucesso", "Categoria cadastrada com sucesso!");
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao cadastrar categoria: " + e.getMessage());
        }
        
        return "redirect:/admin/categorias/novo";
    }
}