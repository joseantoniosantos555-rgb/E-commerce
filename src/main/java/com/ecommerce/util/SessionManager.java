package com.ecommerce.util;

import com.ecommerce.entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Gerenciador de sessão singleton para controle de usuários logados
 * e conexões com banco de dados
 */
public final class SessionManager {
    
    private static final Logger LOGGER = Logger.getLogger(SessionManager.class.getName());
    private static final String URL = "jdbc:postgresql://localhost:5432/ecommerce";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Matheus@9192";
    private static final String DRIVER = "org.postgresql.Driver";
    
    private static volatile SessionManager instance;
    private static User currentUser;
    
    private SessionManager() {
        // Singleton - construtor privado
    }
    
    public static SessionManager getInstance() {
        if (instance == null) {
            synchronized (SessionManager.class) {
                if (instance == null) {
                    instance = new SessionManager();
                }
            }
        }
        return instance;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User user) {
        currentUser = user;
        LOGGER.info("Usuário logado: " + (user != null ? user.getUsername() : "null"));
    }
    
    public void login(User user) {
        setCurrentUser(user);
    }
    
    public void logout() {
        LOGGER.info("Logout do usuário: " + (currentUser != null ? currentUser.getUsername() : "null"));
        currentUser = null;
    }
    
    public boolean isAdmin() {
        return currentUser != null && currentUser.getTipoUsuario() == User.TipoUsuario.ADMIN;
    }
    
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado", e);
        }
    }
    
    public static boolean testarConexao() {
        try (Connection conn = getConnection()) {
            LOGGER.info("Conexão com PostgreSQL estabelecida com sucesso");
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Erro na conexão com PostgreSQL", e);
            return false;
        }
    }
}