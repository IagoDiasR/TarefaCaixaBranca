package br.com.projeto.documentacao;

//Importação de bibliotecas necessárias para a conexão com o banco de dados
import java.sql.Connection;   // Para usar a classe Connection
import java.sql.DriverManager; // Para gerenciar a conexão com o banco
import java.sql.Statement;     // Para executar a instrução SQL
import java.sql.ResultSet;     // Para capturar o resultado da consulta
/**
 * Classe responsável pela autenticação de usuários no sistema.
 * Realiza a conexão ao banco de dados e valida as credenciais fornecidas.
 */

public class user {

    /**
     * Método para conectar ao banco de dados.
     * @return Objeto Connection ou null em caso de falha.
     */
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // Carrega o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            // Tratamento da exceção pode ser adicionado aqui
        }
        return conn;
    }

    /** Nome do usuário autenticado */
    public String nome = "";

    /** Resultado da verificação (true se o usuário for autenticado, false caso contrário) */
    public boolean result = false;

    /**
     * Método para verificar se as credenciais fornecidas são válidas.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @return true se o usuário foi autenticado, false caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD();

        // Monta a instrução SQL
        sql += "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += "  and senha = '" + senha + "';";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Verifica se houve resultado na consulta
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) {
            // Tratamento da exceção pode ser adicionado aqui
        }
        return result;
    }
}