package com.mycompany.monitoramento.coldstock.jiramodelos;

public class IssueField {
    //Esse objeto é utilizado como forma para criar os chamados

    protected String id;
    protected String key;
    protected String name;

    public IssueField(Long id) {
        this.id = id.toString();
    }

    public IssueField(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
