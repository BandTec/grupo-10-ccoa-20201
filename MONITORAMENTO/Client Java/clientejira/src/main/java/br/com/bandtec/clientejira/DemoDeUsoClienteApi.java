package br.com.bandtec.clientejira;

import br.com.bandtec.clientejira.modelo.Issue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class DemoDeUsoClienteApi {

    public static void main(String[] args) throws IOException {

        // Este "gson" Ã© opcional. Apenas para imprimir os objetos na saÃ­da padrÃ£o, caso queira.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
                "coldstock.atlassian.net",
                "201grupo10c@bandtec.com.br",
                "U7BdGDbdzPmNrlX8AbfMA0F5"
        );

        Issue issue = clienteJiraApi.getIssue("CK-2");
        System.out.println("Issue recuperada: "+gson.toJson(issue));

        // Exemplo de objeto para novo chamado (Issue)
//        Issue novaIssue = new Issue();
//        novaIssue.setProjectKey("CK");
//        novaIssue.setSummary("Uso crítico de CPU e Disco");
//        novaIssue.setDescription("CPU em 99% \nDisco em 89% \nMemória em 45.6%");
//        novaIssue.setLabels("alerta-cpu", "alerta-disco");
//
//        clienteJiraApi.criarIssue(novaIssue);
//        System.out.println("Issue criada: "+gson.toJson(novaIssue));

    }
}
