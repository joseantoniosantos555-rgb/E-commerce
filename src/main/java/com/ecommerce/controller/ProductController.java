package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.model.SimpleUser;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CartService cartService;
    
    @GetMapping
    public String listProducts(@RequestParam(required = false) String categoria,
                              @RequestParam(required = false) String search,
                              Model model, HttpSession session) {
        
        List<Product> products;
        
        if (search != null && !search.trim().isEmpty()) {
            products = productService.searchProducts(search.trim());
        } else if (categoria != null && !categoria.trim().isEmpty()) {
            products = productService.getProductsByCategory(categoria);
        } else {
            products = productService.getAvailableProducts();
        }
        
        List<String> categories = productService.getAllCategories();
        SimpleUser currentUser = (SimpleUser) session.getAttribute("currentUser");
        
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", categoria);
        model.addAttribute("searchTerm", search);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isLoggedIn", currentUser != null);
        model.addAttribute("cartItemCount", cartService.getCartItemCount(session));
        
        return "products";
    }
    
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long productId,
                           @RequestParam(defaultValue = "1") Integer quantity,
                           HttpSession session) {
        
        SimpleUser currentUser = (SimpleUser) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        cartService.addToCart(session, productId, quantity);
        return "redirect:/products";
    }
    
    @GetMapping("/admin")
    public String adminProducts(Model model, HttpSession session) {
        SimpleUser currentUser = (SimpleUser) session.getAttribute("currentUser");
        
        if (currentUser == null || !currentUser.isAdmin()) {
            return "redirect:/login";
        }
        
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("currentUser", currentUser);
        
        return "admin-products";
    }
    
    @GetMapping("/admin/new")
    public String newProductForm(Model model, HttpSession session) {
        SimpleUser currentUser = (SimpleUser) session.getAttribute("currentUser");
        
        if (currentUser == null || !currentUser.isAdmin()) {
            return "redirect:/login";
        }
        
        model.addAttribute("product", new Product());
        model.addAttribute("currentUser", currentUser);
        
        return "product-form";
    }
    
    @PostMapping("/admin/save")
    public String saveProduct(@ModelAttribute Product product, HttpSession session) {
        SimpleUser currentUser = (SimpleUser) session.getAttribute("currentUser");
        
        if (currentUser == null || !currentUser.isAdmin()) {
            return "redirect:/login";
        }
        
        if (product.getId() == null) {
            productService.addProduct(product);
        } else {
            productService.updateProduct(product);
        }
        
        return "redirect:/products/admin";
    }
    
    @GetMapping("/admin/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model, HttpSession session) {
        SimpleUser currentUser = (SimpleUser) session.getAttribute("currentUser");
        
        if (currentUser == null || !currentUser.isAdmin()) {
            return "redirect:/login";
        }
        
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products/admin";
        }
        
        model.addAttribute("product", product);
        model.addAttribute("currentUser", currentUser);
        
        return "product-form";
    }
    
    @PostMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        SimpleUser currentUser = (SimpleUser) session.getAttribute("currentUser");
        
        if (currentUser == null || !currentUser.isAdmin()) {
            return "redirect:/login";
        }
        
        productService.deleteProduct(id);
        return "redirect:/products/admin";
    }
}