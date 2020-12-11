package com.mycompany.monitoramento.coldstock.jiraexecutavel;

import com.mycompany.monitoramento.coldstock.jiramodelos.Issue;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Base64;

public class ClienteJiraApi {
    
    //essa classe é responsavel por criar a conexão com o jira

    //criamos as variaveis para poder realizar essa conexao
    private String urlBaseJira;
    private String usuario;
    private String token;

    /**
     * Cria uma nova instância de {@link ClienteJiraApi}.
     * @param urlBaseJira URL base do seu Jira. Ex: <b>meuapp.atlassian.net</b>
     * @param usuario Seu usuário de acesso ao Jira
     * @param token Seu token de acesso ao Jira.
     *              Pode ser gerado em <a href='https://id.atlassian.com/manage-profile/security/api-tokens'>
     *              https://id.atlassian.com/manage-profile/security/api-tokens</a>
     */
    
    // esse é o construtor da classe
    public ClienteJiraApi(String urlBaseJira, String usuario, String token) {
        this.urlBaseJira = urlBaseJira.startsWith("https://")?urlBaseJira:"https://"+urlBaseJira;
        this.usuario = usuario;
        this.token = token;
    }

    /**
    Cria um chamado (Issue) e, se tudo der certo, atualiza os "id" e "key" do objeto do tipo Issue enviado
    @param novaIssue Objeto com os dados do novo chamado (Issue)
     **/
    //essa funçao cria issues
    public void criarIssue(Issue novaIssue) throws IOException {
        Gson gson = new Gson();
        
        RequestBody issueBody = RequestBody.create(gson.toJson(novaIssue), MediaType.get("application/json;charset=UTF-8"));
        Request request = criarRequestBuilder()
                .url(String.format("%s/rest/api/3/issue", urlBaseJira)).method("POST", issueBody)
                .build();

        Response response = criarCliente().newCall(request).execute();

        Issue issueCriada = gson.fromJson(response.body().string(), Issue.class);

        novaIssue.setId(issueCriada.getId());
        novaIssue.setKey(issueCriada.getKey());
    }

    /**
     * Recupera um chamado (Issue) por valor da chave (key)
     * @param key
     * @return
     * @throws IOException
     */
    
    //essa função ira conectar no jira e procurar por um issua especifico
    //esse issue é identificado pela key
    public Issue getIssue(String key) throws IOException {
        Request request = criarRequestBuilder()
                .url(String.format("%s/rest/api/3/issue/%s", urlBaseJira, key)).method("GET", null)
                .build();

        Response response = criarCliente().newCall(request).execute();

        Issue issue = new Gson().fromJson(response.body().string(), Issue.class);

        return issue;
    }

    private OkHttpClient criarCliente() {
        return new OkHttpClient().newBuilder().build();
    }

    private Request.Builder criarRequestBuilder() {
        String autenticacaoBasic = Base64.getEncoder().encodeToString(String.format("%s:%s", usuario, token).getBytes());

        return new Request.Builder().addHeader("Authorization", String.format("Basic %s", autenticacaoBasic));
    }

}
