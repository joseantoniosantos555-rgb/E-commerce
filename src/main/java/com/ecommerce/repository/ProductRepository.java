package com.ecommerce.repository;

import com.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategoria(String categoria);
    
    List<Product> findByNomeContainingIgnoreCase(String nome);
    
    @Query("SELECT p FROM Product p WHERE p.estoque > 0")
    List<Product> findAvailableProducts();
    
    @Query("SELECT p FROM Product p WHERE p.categoria = :categoria AND p.estoque > 0")
    List<Product> findAvailableProductsByCategory(@Param("categoria") String categoria);
    
    @Query("SELECT DISTINCT p.categoria FROM Product p WHERE p.categoria IS NOT NULL")
    List<String> findAllCategories();
}