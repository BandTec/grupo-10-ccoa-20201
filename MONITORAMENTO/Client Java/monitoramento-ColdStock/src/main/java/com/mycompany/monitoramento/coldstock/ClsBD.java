/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock;

//import com.mysql.jdbc.Driver;
//import com.mysql.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClsBD {

    //static final String DB_URL = "jdbc:mysql://localhost:3306/coldstock";
    private static final String DB_URL = "jdbc:mysql://localhost/coldstock?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //  Database credentials
    private static final String USER = "ColdUser";
    private static final String PASS = "senha123";
    private Connection conn = null;
    private Statement stmt = null;
    
    //login
    private String frase = "";


    public String getFrase() {
        return frase;
    }
    

    public ResultSet consultar() throws SQLException {
        System.out.println("Criando Statement...");
        stmt = conn.createStatement();

        String sql;
        sql = "SELECT * FROM Maquinas";

        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("Comando executado com sucesso!");

        return rs;
    }
    
        public ResultSet consultarComponentes() throws SQLException {
        System.out.println("Criando Statement...");
        stmt = conn.createStatement();

        String sql;
        sql = "SELECT * FROM componentes";

        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("Comando executado com sucesso!");
        return rs;
    }
        
    public ResultSet consultarConfiguracaoMaquina(Integer fkMaquina) throws SQLException {
        System.out.println("Criando Statement...");
        stmt = conn.createStatement();

        String sql;
        sql =String.format("select nomeComponente, capacidadeMax, metrica, porcentagemMax from configuracaoMaquina, componentes where idComponente = fkComponente and fkMaquina = %d",fkMaquina);

        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("Comando executado com sucesso!");
        return rs;
    }

    public Boolean consultarFuncionario(String funcionario, String senha) throws SQLException {
        System.out.println("Criando Statement...");
        stmt = conn.createStatement();

        String sql;

        sql = String.format("SELECT * FROM funcionarios where emailFuncionario = '%s' and senhaFuncionario = '%s'", funcionario, senha);

        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("Comando executado com sucesso!");
        if (rs.next()) {
            System.out.println("Buenas buenas, cliente encontrado!");
            frase = String.format("Bem vindo, %s.",rs.getString("nomeFuncionario"));
            return true;
        } else {
            System.out.println("Nada encontrado");
            frase = "Usuário ou senha incorretos.";
            return false;
        }
    }

    public void conectar() {
        System.out.println("Tentando conectar ao BD...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(ClsBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conectado com sucesso ao BD!");
    }
}
