package com.mycompany.monitoramento.coldstock.jiraexecutavel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.monitoramento.coldstock.jiramodelos.Issue;
import com.mycompany.monitoramento.coldstock.modelos.Operacao;
import java.awt.Color;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chamado extends ClienteJiraApi{
    
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
//    ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
//            "coldstock.atlassian.net",
//            "201grupo10c@bandtec.com.br",
//            "xYT7D7fZZRvpKWl0svMyC6C9"
//    );

    public Chamado(String urlBaseJira, String usuario, String token) {
        super(urlBaseJira, usuario, token);
    }
    
    public void abrirChamado(List valor, List nome,List limiteValor, List<Integer> fkComponentes) throws SQLException{
        Issue novaIssue = new Issue();
        
        
        //e setamos as informações como Key, Titulo, Descricao e Labels
        novaIssue.setProjectKey("CK");
        
        novaIssue.setSummary(new Operacao().textoChamados(fkComponentes));
        
        novaIssue.setDescription(textoSummary(valor, nome, limiteValor));
        
        novaIssue.setLabels("Alerta-" + nome.get(0));
        
        try {
            //e com essas informações setadas, podemos criar uma issue no jira
            this.criarIssue(novaIssue);
            System.out.println("Chamado criado com sucesso!!!");
            
        } catch (IOException ex) {
            //caso não seja possivel, imprimos para no log o erro
            
            System.out.println("Erro ao criar chamado,tente novaCkCKsmente!!");
            
        }
        
        System.out.println("Issue criada: "+gson.toJson(novaIssue));
        
    }
    
    private String textoSummary(List valor, List nome,List limiteValor){
        String texto = "";
        for (int i = 0; i < valor.size(); i++) {
              texto += "O valor do componente "
                + nome.get(i) + 
                " está em " +
                valor.get(i) + " e o máximo que pode atingir é "+
                new DecimalFormat("#,##0.00").format(limiteValor.get(i)) + "\n";
        }
        
        return texto;
    }
    
}
