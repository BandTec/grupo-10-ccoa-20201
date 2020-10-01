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

    public ResultSet consulta() {
        conectar();
        
        System.out.println("Tentando executar query...");
        try{
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String query = "select * from maquinas;";
            
            ResultSet retorno = stmt.executeQuery(query);
            
            System.out.println("Comando realizado com sucesso!");
            
            return retorno;
        } catch (SQLException ex){
            Logger.getLogger(ClsBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void conectar() {
        System.out.println("Tentando conectar ao BD...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(ClsBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conectado com sucesso ao BD!");
    }
}
