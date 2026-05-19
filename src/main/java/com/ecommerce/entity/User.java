package com.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome_usuario", unique = true, nullable = false)
    private String nomeUsuario;
    
    @Column(nullable = false)
    private String senha;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;
    
    private String endereco;
    private String telefone;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario = TipoUsuario.CLIENTE;
    
    public enum TipoUsuario {
        CLIENTE, ADMIN
    }
    
    public User() {}
    
    public User(String nomeUsuario, String senha, String email, String nomeCompleto) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.email = email;
        this.nomeCompleto = nomeCompleto;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }
    
    public String getUsername() { return nomeUsuario; }
    public void setUsername(String username) { this.nomeUsuario = username; }
    
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public String getPassword() { return senha; }
    public void setPassword(String password) { this.senha = password; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    
    public String getNome() { return nomeCompleto; }
    public void setNome(String nome) { this.nomeCompleto = nome; }
    
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}