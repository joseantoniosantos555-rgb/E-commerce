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
@RequestMapping("/admin/categorias")
public class CategoryManagementController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String listarCategorias(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario == null || usuario.getTipoUsuario() != Usuario.TipoUsuario.ADMIN) {
            return "redirect:/login";
        }
        
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        
        return "admin-categorias";
    }

    @PostMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoriaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("sucesso", "Categoria excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir categoria: " + e.getMessage());
        }
        return "redirect:/admin/categorias";
    }
}