package com.mycompany.monitoramento.coldstock.jiraexecutavel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.monitoramento.coldstock.jiramodelos.Issue;
import com.mycompany.monitoramento.coldstock.modelos.Operacoes;
import java.awt.Color;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriarChamado {
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
            "coldstock.atlassian.net",
            "201grupo10c@bandtec.com.br",
            "xYT7D7fZZRvpKWl0svMyC6C9"
    );
    
    public void criarChamado(ResultSet registros, Double limiteValor) throws SQLException{
        Issue novaIssue = new Issue();
        
        
        //e setamos as informações como Key, Titulo, Descricao e Labels
        novaIssue.setProjectKey("CK");
        
        novaIssue.setSummary(new Operacoes().textoChamados(registros.getInt("fkComponente")));
        
        novaIssue.setDescription(
                "O valor do componente "
                + registros.getString("nomeComponente") + 
                " está em " +
                registros.getDouble("valor") + " e o máximo que pode atingir é "+
                new DecimalFormat("#,##0.00").format(limiteValor));
        
        novaIssue.setLabels("Alerta-" + registros.getString("nomeComponente"));
        
        try {
            //e com essas informações setadas, podemos criar uma issue no jira
            clienteJiraApi.criarIssue(novaIssue);
            System.out.println("Chamado criado com sucesso!!!");
            
        } catch (IOException ex) {
            //caso não seja possivel, imprimos para no log o erro
            Logger.getLogger(TelaCriarChamado.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Erro ao criar chamado,tente novaCkCKsmente!!");
            
        }
        
        System.out.println("Issue criada: "+gson.toJson(novaIssue));
        
    }
    
}
