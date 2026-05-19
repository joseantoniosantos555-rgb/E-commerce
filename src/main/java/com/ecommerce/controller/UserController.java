package com.ecommerce.controller;

import com.ecommerce.entity.Usuario;
import com.ecommerce.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam(required = false) String redirect,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(username);
        
        if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(password)) {
            session.setAttribute("usuario", usuarioOpt.get());
            
            if (redirect != null && !redirect.isEmpty() && !redirect.equals("null")) {
                return "redirect:" + redirect;
            }
            
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Usuário ou senha inválidos!");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}