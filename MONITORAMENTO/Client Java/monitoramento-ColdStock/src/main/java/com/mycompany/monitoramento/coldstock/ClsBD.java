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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ClsBD {
    
    BasicDataSource dataSource  = new BasicDataSource();
        
   
    //link para acessar nosso banco de dados
    private static final String DB_URL = "jdbc:mysql://localhost/coldstock?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    
    //  Logando em nosso banco de dados
    private static final String USER = "ColdUser";
    private static final String PASS = "senha123";
    //criando uma variável de conexão
    private Connection conn = null;
    // criando uma variável que permite dar comandos do mysql
    private Statement stmt = null;
    
    //frase = resultado da tentativa de login
    private String frase = "";

    //pega o que estiver dentro da variável "frase" e retorna ela para ser plotada
    public String getFrase() {
        return frase;
    }
    
    //throws SQLException = tem erro? Joga na tela! 
    public ResultSet consultar() throws SQLException {
        
        System.out.println("Criando Statement...");
        
        //estamos mostrando onde iremos realizar os comandos mysql.... 
        //createStatment =  possibilita que criemos o statement
        stmt = conn.createStatement();

        //criamos uma variável sql do tipo string 
        String sql;
        sql = "SELECT * FROM Maquinas";
        
        //executando o comando my sql
        //resultset = retorno do banco de dados
        
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("Comando executado com sucesso!");

        return rs;
    }
    
    public List consultarMaquinas() throws SQLException{
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        dataSource.setUrl("jdbc:mysql://localhost/coldstock?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        
        dataSource.setUsername("ColdUser");
        dataSource.setPassword("senha123");
        
        System.out.println("Criando Statement...");
        
        //estamos mostrando onde iremos realizar os comandos mysql.... 
        //createStatment =  possibilita que criemos o statement
        stmt = conn.createStatement();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //criamos uma variável sql do tipo string 
        String sql;
        sql = "SELECT * FROM Maquinas";
        
        //executando o comando my sql
        //constultaMaquina = retorno do banco de dados
        List<Maquinas> consultaMaquina;
        consultaMaquina = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(Maquinas.class));
        
        return consultaMaquina;
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
    // criamos uma função onde dentro dela hávera duas variáveis do tipo string 
    public Boolean consultarFuncionario(String funcionario, String senha) throws SQLException {
        System.out.println("Criando Statement...");
        stmt = conn.createStatement();

        String sql;

        sql = String.format("SELECT * FROM funcionarios where emailFuncionario = '%s' and senhaFuncionario = '%s'", funcionario, senha);

        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("Comando executado com sucesso!");
        
        //Faz com que passemos para a próxima linha de comando
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
            //fazendo a conexão
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            //plota o erro(usando uma váriavel do tipo "erro no mysql")
            Logger.getLogger(ClsBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conectado com sucesso ao BD!");
    }
}
