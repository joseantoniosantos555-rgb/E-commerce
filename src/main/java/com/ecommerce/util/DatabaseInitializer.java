package com.ecommerce.util;

import java.sql.Connection;
import java.sql.Statement;

/**
 * DESABILITADO - O Hibernate agora cria as tabelas automaticamente
 * Mantido apenas para referência
 */
public class DatabaseInitializer {

    public static void criarTodasTabelas() {
        // Desabilitado - Spring Boot + JPA cria automaticamente
        System.out.println("DatabaseInitializer desabilitado - usando Hibernate DDL auto");
        return;
        /*
        try (Connection conn = SessionManager.getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.println("🔧 Criando estrutura completa do banco...");

            // 1. Tabela usuarios
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS usuarios (
                    id SERIAL PRIMARY KEY,
                    username VARCHAR(50) NOT NULL UNIQUE,
                    password VARCHAR(100) NOT NULL,
                    nome VARCHAR(100) NOT NULL,
                    email VARCHAR(100),
                    tipo_usuario VARCHAR(20) NOT NULL DEFAULT 'CLIENTE'
                )
                """);
            System.out.println("✅ Tabela 'usuarios' criada");

            // 2. Tabela categorias
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS categorias (
                    id SERIAL PRIMARY KEY,
                    nome VARCHAR(50) NOT NULL UNIQUE,
                    descricao TEXT
                )
                """);
            System.out.println("✅ Tabela 'categorias' criada");

            // 3. Tabela produtos
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS produtos (
                    id SERIAL PRIMARY KEY,
                    nome VARCHAR(100) NOT NULL,
                    descricao TEXT,
                    preco DECIMAL(10,2) NOT NULL,
                    estoque INTEGER DEFAULT 0,
                    categoria_id INTEGER REFERENCES categorias(id),
                    url_imagem VARCHAR(255)
                )
                """);
            System.out.println("✅ Tabela 'produtos' criada");

            // 4. Tabela pedidos
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pedidos (
                    id SERIAL PRIMARY KEY,
                    usuario_id INTEGER REFERENCES usuarios(id),
                    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    status VARCHAR(20) DEFAULT 'PENDENTE',
                    valor_total DECIMAL(10,2) NOT NULL,
                    endereco_entrega TEXT
                )
                """);
            System.out.println("✅ Tabela 'pedidos' criada");

            // 5. Tabela pedido_produtos (relacionamento N:N)
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pedido_produtos (
                    id SERIAL PRIMARY KEY,
                    pedido_id INTEGER REFERENCES pedidos(id),
                    produto_id INTEGER REFERENCES produtos(id),
                    quantidade INTEGER NOT NULL,
                    preco_unitario DECIMAL(10,2) NOT NULL
                )
                """);
            System.out.println("✅ Tabela 'pedido_produtos' criada");

            // 6. Tabela pagamentos
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pagamentos (
                    id SERIAL PRIMARY KEY,
                    pedido_id INTEGER REFERENCES pedidos(id),
                    tipo_pagamento VARCHAR(20) NOT NULL,
                    valor DECIMAL(10,2) NOT NULL,
                    status VARCHAR(20) DEFAULT 'PENDENTE',
                    data_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """);
            System.out.println("✅ Tabela 'pagamentos' criada");

            // 7. Tabela cartao
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS cartao (
                    id SERIAL PRIMARY KEY,
                    pagamento_id INTEGER REFERENCES pagamentos(id),
                    numero_cartao VARCHAR(20) NOT NULL,
                    nome_titular VARCHAR(100) NOT NULL,
                    validade VARCHAR(7) NOT NULL,
                    cvv VARCHAR(4) NOT NULL,
                    bandeira VARCHAR(20)
                )
                """);
            System.out.println("✅ Tabela 'cartao' criada");

            // 8. Tabela pix
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pix (
                    id SERIAL PRIMARY KEY,
                    pagamento_id INTEGER REFERENCES pagamentos(id),
                    chave_pix VARCHAR(100) NOT NULL,
                    qr_code TEXT,
                    codigo_transacao VARCHAR(50)
                )
                """);
            System.out.println("✅ Tabela 'pix' criada");

            // 9. Tabela boleto
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS boleto (
                    id SERIAL PRIMARY KEY,
                    pagamento_id INTEGER REFERENCES pagamentos(id),
                    codigo_barras VARCHAR(100) NOT NULL,
                    data_vencimento DATE NOT NULL,
                    nosso_numero VARCHAR(20)
                )
                """);
            System.out.println("✅ Tabela 'boleto' criada");

            // Adicionar coluna endereco_entrega se não existir
            try {
                stmt.execute("ALTER TABLE pedidos ADD COLUMN IF NOT EXISTS endereco_entrega TEXT");
                System.out.println("✅ Coluna 'endereco_entrega' adicionada");
            } catch (Exception e) {
                System.out.println("ℹ️ Coluna 'endereco_entrega' já existe");
            }
            
            // Adicionar coluna forma_pagamento se não existir
            try {
                stmt.execute("ALTER TABLE pedidos ADD COLUMN IF NOT EXISTS forma_pagamento VARCHAR(50)");
                System.out.println("✅ Coluna 'forma_pagamento' adicionada");
            } catch (Exception e) {
                System.out.println("ℹ️ Coluna 'forma_pagamento' já existe");
            }

            // Inserir usuários padrão
            stmt.execute("""
                INSERT INTO usuarios (username, password, nome, email, tipo_usuario) 
                VALUES ('admin', 'admin123', 'Administrador', 'admin@ecommerce.com', 'ADMIN')
                ON CONFLICT (username) DO NOTHING
                """);
            
            stmt.execute("""
                INSERT INTO usuarios (username, password, nome, email, tipo_usuario) 
                VALUES ('cliente', '123456', 'Cliente Teste', 'cliente@ecommerce.com', 'CLIENTE')
                ON CONFLICT (username) DO NOTHING
                """);
            
            stmt.execute("""
                INSERT INTO usuarios (username, password, nome, email, tipo_usuario) 
                VALUES ('user', 'user123', 'Usuário Admin', 'user@ecommerce.com', 'ADMIN')
                ON CONFLICT (username) DO NOTHING
                """);
            
            System.out.println("✅ Usuários padrão criados");
            System.out.println("🎉 Estrutura completa do banco criada com sucesso!");

        } catch (Exception e) {
            System.out.println("❌ Erro ao criar estrutura do banco: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        */
    }
}