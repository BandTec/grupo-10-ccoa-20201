/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.modelos;

import com.mycompany.monitoramento.coldstock.jiraexecutavel.Chamado;
import com.mycompany.monitoramento.coldstock.telas.TelaEditarMaquina;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    Essa classe é similar a classe Consultas, onde fazemos a conexão com o banco e selects.
 */
public class Operacao {

    public BasicDataSource conectar() {
        /*
        Aqui realizamos a conexão utilizando o JDBC, da mesma forma que na Consultas
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
    
    public List trazerLista(BasicDataSource dataSource, Integer idComando, String componente, Integer fkMaquina) throws SQLException {
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
                    + "and nomeComponente = '%s' order by dataHora desc limit 30 ", fkMaquina, componente);
        }
        if (idComando == 3) {
            sql = String.format("select nomeComponente, valor, dataHora from registros INNER JOIN "
                    + "componentes on fkComponente = idComponente where fkMaquina = %d "
                    + "and nomeComponente = '%s' order by dataHora desc limit 1", fkMaquina, componente);
        }

        List<Registro> listaComponentes;
        listaComponentes = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Registro.class));

        // Aqui nos invertemos a lista, para ter os resultados mais recentes como os ultimos da lista, deixando o grafico mais dinamico
        Collections.reverse(listaComponentes);
        dataSource.close();
        return listaComponentes;
    }

    public void excluirMaquina(Integer idMaquina) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(conectar());
        String sql = "delete from configuracaoMaquina where fkMaquina = %d";
        jdbcTemplate.update(String.format(sql, idMaquina));
        sql = "delete from maquinas where idMaquina = %d";
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
        List<String> verificador = new ArrayList<String>();
        for (int j = 1; j < Itens.length; j++) {
            String sql = "insert into configuracaoMaquina (capacidadeMax, porcentagemMax,fkMaquina, fkComponente) values(?, ?, ?, (select idcomponente from componentes where nomeComponente = ?))";
            for (int i = 0; i < tabela.getRowCount(); i++) {
                if (String.valueOf(tabela.getModel().getValueAt(i, 0)).equals(Itens[j].split(",")[0].trim()) || verificador.contains(Itens[j].split(",")[0].trim())) {
                    sql = "update configuracaoMaquina set capacidadeMax = ? , porcentagemMax = ? where fkMaquina = ? and fkComponente = (select idcomponente from componentes where nomeComponente = ?)";
                    break;
                }
            }
            verificador.add(Itens[j].split(",")[0].trim());
            jdbcTemplate.update(sql, Itens[j].split(",")[1].trim(), Itens[j].split(",")[2].trim(), idMaquina, Itens[j].split(",")[0].trim());

        }
        JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso");
    }

    public void comparacao() throws SQLException {
        ResultSet configMaquina = new Consulta().consultarMaximas();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(conectar());
        String sql = "update registros set fkChamado = (select max(idchamado) from chamados) where datahora = ? and fkmaquina = ?";

        List<Double> capacidade = new ArrayList<Double>();
        List<Double> porcentagem = new ArrayList<Double>();
        while (configMaquina.next()) {
            capacidade.add(configMaquina.getDouble("CapacidadeMax"));
            porcentagem.add(configMaquina.getDouble("porcentagemMax"));
        }
        ResultSet registros = new Consulta().consultarRegistros(capacidade.size());
        List<Double> limite = new ArrayList<Double>();
        List<Double> valor = new ArrayList<Double>();
        List<String> nome = new ArrayList<String>();
        List<String> datahora = new ArrayList<String>();
        List<Integer> fkComponentes = new ArrayList<Integer>();
        for (int i = 0; registros.next(); i++) {

            if (registros.getInt("fkComponente") != 4 && registros.getInt("fkComponente") != 5 && registros.getDouble("valor") > (capacidade.get(i % capacidade.size()) * (porcentagem.get(i % porcentagem.size()) / 100))) {

                fkComponentes.add(registros.getInt("fkComponente"));
                datahora.add(registros.getString("dataHora"));
                nome.add(registros.getString("nomeComponente"));
                valor.add(registros.getDouble("valor"));
                limite.add(capacidade.get(i % capacidade.size()) * (porcentagem.get(i % porcentagem.size()) / 100));

            } else if ((registros.getInt("fkComponente") == 4 || registros.getInt("fkComponente") == 5) && registros.getDouble("valor") < (capacidade.get(i % capacidade.size()) * (porcentagem.get(i % porcentagem.size()) / 100))) {
//                    adicionarChamado(registros.getInt("fkComponentes"),registros.getString("dataHora") );
//                    jdbcTemplate.update(sql,  registros.getString("dataHora")
//                    , Maquina.fkmaquina);
                fkComponentes.add(registros.getInt("fkComponente"));
                datahora.add(registros.getString("dataHora"));
                nome.add(registros.getString("nomeComponente"));
                valor.add(registros.getDouble("valor"));
                limite.add(capacidade.get(i % capacidade.size()) * (porcentagem.get(i % porcentagem.size()) / 100));
                // new Chamado().abrirChamado(registros, (capacidade.get(i % capacidade.size()) * (porcentagem.get(i % porcentagem.size()) / 100)));
            }

        }
        registros.close();
        registros = new Consulta().consultarRegistros(capacidade.size());
        while (registros.next()) {
            if (registros.getString("fkChamado") == null) {
                adicionarChamado(fkComponentes, registros.getString("dataHora"));
                jdbcTemplate.update(sql, registros.getString("dataHora"),
                        Maquina.fkmaquina);
                new Chamado("coldstock.atlassian.net",
            "201grupo10c@bandtec.com.br",
            "xYT7D7fZZRvpKWl0svMyC6C9").abrirChamado(valor, nome, limite, fkComponentes);
                System.out.println("Registros atualizados com sucesso");
                break;

            }
        }
        registros.close();
    }

    public void adicionarChamado(List<Integer> fkcomponente, String datahora) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(conectar());
        String sql = "insert into chamados(dataChamado, descricao) values(?,?)";
        jdbcTemplate.update(sql, datahora, textoChamados(fkcomponente));
        System.out.println("Chamado inserido com sucesso");
    }

    public String textoChamados(List<Integer> fkComponente) {
        String texto = "";
        for (int i = 0; i < fkComponente.size(); i++) {
            switch (fkComponente.get(i)) {
                case 1:
                    texto += "CPU sobrecarregando!, ";
                    break;
                case 2:
                    texto += "RAM sobrecarregando!, ";
                    break;
                case 3:
                    texto = "DISCO Lotado!, ";
                    break;
                case 4:
                    texto = "CONEXÃO D. baixa!!, ";
                    break;
                case 5:
                    texto = "CONEXÃO U. baixa!, ";
                    break;
                case 6:
                    texto = "TEMPERATURA alta!, ";
                    break;
            }
        }

        return texto;
    }
}
