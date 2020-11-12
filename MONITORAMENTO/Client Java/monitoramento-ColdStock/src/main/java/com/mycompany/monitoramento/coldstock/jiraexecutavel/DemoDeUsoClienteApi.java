package com.mycompany.monitoramento.coldstock.jiraexecutavel;

import com.mycompany.monitoramento.coldstock.jiramodelos.Issue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class DemoDeUsoClienteApi {

    public static void main(String[] args) throws IOException {

        // Este "gson" é opcional. Apenas para imprimir os objetos na saída padrão, caso queira.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
                "coldstock.atlassian.net",
                "201grupo10c@bandtec.com.br",
                "xYT7D7fZZRvpKWl0svMyC6C9"
        );

        Issue issue = clienteJiraApi.getIssue("CK-1");
        System.out.println("Issue recuperada: "+gson.toJson(issue));

        // Exemplo de objeto para novo chamado (Issue)
//        Issue novaIssue = new Issue();
//        novaIssue.setProjectKey("CK");
//        novaIssue.setSummary("Chamado de teste");
//        novaIssue.setDescription("Chamado 1 \nChamado 2 \nChamado 3");
//        novaIssue.setLabels("alerta-cpu", "alerta-disco");
//
//        clienteJiraApi.criarIssue(novaIssue);
//        System.out.println("Issue criada: "+gson.toJson(novaIssue));

    }
}
