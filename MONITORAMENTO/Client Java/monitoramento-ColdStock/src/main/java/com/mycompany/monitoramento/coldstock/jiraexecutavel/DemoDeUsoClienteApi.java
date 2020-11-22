package com.mycompany.monitoramento.coldstock.jiraexecutavel;

import com.mycompany.monitoramento.coldstock.jiramodelos.Issue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mycompany.monitoramento.coldstock.jiramodelos.Fields;
import java.awt.SystemColor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DemoDeUsoClienteApi {

    public static void main(String[] args) throws IOException {

        // Este "gson" é opcional. Apenas para imprimir os objetos na saída padrão, caso queira.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        // aqui passamos as informações para realizar a conexao
        ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
                "coldstock.atlassian.net",
                "201grupo10c@bandtec.com.br",
                "xYT7D7fZZRvpKWl0svMyC6C9"
        );

//        Issue issue = clienteJiraApi.getIssue("CK-1");
//        
//        System.out.println(issue.getKey());
//        System.out.println(issue.getFields().getSummary());
//        System.out.println(issue.getFields().getDescription().getContent()
//                .get(0).get("content"));
//        System.out.println(issue.getFields().getLabels());
//        
//        String testeFull = issue.getFields().getDescription().getContent()
//                .get(0).get("content").toString();
//        String lixo = "[{type=text, text=";
//        String nada = "";
//        String testeNovo = testeFull.replace(lixo, nada);
//        testeNovo = testeNovo.replace("}]", "");
//        
//        System.out.println(testeNovo);
//        
//        Map json;
//        json = gson.fromJson(issue, Issue);
//        ((Map) json.get("contatos")).get("telefone"))


        // Exemplo de objeto para novo chamado (Issue)
        
        //criaremos um objeto da classe Issue
        Issue novaIssue = new Issue();
        //e setamos as informações como Key, Titulo, Descricao e Labels
        novaIssue.setProjectKey("CK");
        novaIssue.setSummary("Chamado de teste");
        novaIssue.setDescription("Chamado 1 \nChamado 2 \nChamado 3");
        novaIssue.setLabels("alerta-cpu", "alerta-disco");
        
        //e com essas informações setadas, podemos criar uma issue no jira
        clienteJiraApi.criarIssue(novaIssue);
        System.out.println("Issue criada: "+gson.toJson(novaIssue));

    }
}
