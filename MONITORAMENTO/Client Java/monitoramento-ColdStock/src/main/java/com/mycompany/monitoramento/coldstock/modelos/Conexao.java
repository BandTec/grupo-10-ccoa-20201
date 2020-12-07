/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Carlos Alberto
 */

/*
    Essa classe é similar a classe ClsBD, onde fazemos a conexão com o banco e selects.
 */
public class Conexao {

    public BasicDataSource conectar() {
        /*
        Aqui realizamos a conexão utilizando o JDBC, da mesma forma que na ClsBD
         */
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource.setUrl("jdbc:mysql://localhost/coldstock?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

        dataSource.setUsername("ColdUser");
        dataSource.setPassword("senha123");

        System.out.println("Conectado com sucesso ao BD!");
        System.out.println("Criando Statement...");

        return dataSource;
    }

    ;
    
    public List trazerLista(BasicDataSource dataSource, Integer idComando, String componente, Integer fkMaquina) {
        /*
            Essa função é a responsavel por trazer uma lista de dados que será usada para
        gerar o grafico.
         */
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = null;

        // Aqui verificamos qual lista devemos gerar. A lista que recebera o resultado desse select ira especificar qual sera o id
        if (idComando == 1) {
            sql = String.format("select nomeComponente, valor, dataHora from registros INNER JOIN "
                    + "componentes on fkComponente = idComponente where fkMaquina = %d "
                    + "and nomeComponente = '%s' order by dataHora desc limit 10", fkMaquina, componente);
        }
        if (idComando == 2) {
            sql = String.format("select nomeComponente, valor, dataHora from registros INNER JOIN "
                    + "componentes on fkComponente = idComponente where fkMaquina = %d "
                    + "and nomeComponente = '%s' order by dataHora desc ", fkMaquina, componente);
        }
        if (idComando == 3) {
            sql = String.format("select nomeComponente, valor, dataHora from registros INNER JOIN "
                    + "componentes on fkComponente = idComponente where fkMaquina = %d "
                    + "and nomeComponente = '%s' order by dataHora desc limit 1", fkMaquina, componente);
        }

        List<Componente> listaComponentes;
        listaComponentes = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Componente.class));

        // Aqui nos invertemos a lista, para ter os resultados mais recentes como os ultimos da lista, deixando o grafico mais dinamico
        Collections.reverse(listaComponentes);

        return listaComponentes;
    }

    public void excluirMaquina(Integer idMaquina) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(conectar());
        String sql = "delete from configuracaoMaquina where fkMaquina = %d";
        jdbcTemplate.update(String.format(sql, idMaquina));
        sql = "delete from Maquinas where idMaquina = %d";
        jdbcTemplate.update(String.format(sql, idMaquina));
        System.out.println("Delete efetuado com sucesso");
    }

    public void adicionarMaquina(String maquinaNome, String maquinaTipo) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(conectar());
        String sql = "insert into maquinas values (null, '%s', '%s')";
        jdbcTemplate.update(String.format(sql, maquinaNome, maquinaTipo));
        JOptionPane.showMessageDialog(null, "Inserção efetuada com sucesso");
    }

    public void editarComponentes(Integer porcentagem, Double capacidade,
            Integer fkMaquina, Integer fkComponente) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(conectar());
        String sql = "update configuracaoMaquina set capacidadeMax = ? , porcentagemMax = ? where fkMaquina = ? and fkComponente = ?";
        jdbcTemplate.update(sql, capacidade, porcentagem, fkMaquina, fkComponente);
        System.out.println(String.format(sql, capacidade, porcentagem, fkMaquina, fkComponente).replace(",", "."));
        JOptionPane.showMessageDialog(null, "Atualização efetuada com sucesso");
    }

    public void excluirComponentes(Integer idMaquina, Integer fkComponente) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(conectar());
        String sql = "delete from configuracaoMaquina where fkMaquina = ? and fkcomponente = ?";
        jdbcTemplate.update(sql, idMaquina, fkComponente);
        JOptionPane.showMessageDialog(null, "Delete efetuado com sucesso");
    }

    public void inserirComponentes(Integer idMaquina, Integer fkComponente, JTable tabela, String[] Itens) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(conectar());
        for (int j = 1; j < Itens.length; j++) {
            String sql = "insert into configuracaoMaquina (capacidadeMax, porcentagemMax,fkMaquina, fkComponente) values(?, ?, ?, (select idcomponente from componentes where nomeComponente = ?))";
            for (int i = 0; i < tabela.getRowCount(); i++) {
                if (String.valueOf(tabela.getModel().getValueAt(i, 0)).equals(Itens[j].split(",")[0].trim())) {
                    sql = "update configuracaoMaquina set capacidadeMax = ? , porcentagemMax = ? where fkMaquina = ? and fkComponente = (select idcomponente from componentes where nomeComponente = ?)";
                }

            }
            jdbcTemplate.update(sql,Itens[j].split(",")[1].trim(),Itens[j].split(",")[2].trim(), idMaquina, Itens[j].split(",")[0].trim());
            
            
            
        }
        JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso");
    }
}
