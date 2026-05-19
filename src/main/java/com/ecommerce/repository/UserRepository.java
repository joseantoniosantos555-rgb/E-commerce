package com.ecommerce.repository;

import com.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByNomeUsuario(String nomeUsuario);
    
    Optional<User> findByEmail(String email);
    
    boolean existsByNomeUsuario(String nomeUsuario);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.nomeUsuario = :username AND u.senha = :password")
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}