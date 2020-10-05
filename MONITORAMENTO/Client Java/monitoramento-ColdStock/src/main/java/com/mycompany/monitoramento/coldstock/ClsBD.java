/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClsBD {

    //static final String DB_URL = "jdbc:mysql://localhost:3306/coldstock";
    static final String DB_URL = "jdbc:mysql://localhost/coldstock?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "ColdUser";
    static final String PASS = "senha123";
    Connection conn = null;
    Statement stmt = null;

    public ResultSet consultar() throws SQLException {
        System.out.println("Criando Statement...");
        stmt = conn.createStatement();
        
        String sql;
        sql = "SELECT * FROM Maquinas";
        
        ResultSet rs = stmt.executeQuery(sql);
        
        System.out.println("Comando executado com sucesso!");
        
        return rs;
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
