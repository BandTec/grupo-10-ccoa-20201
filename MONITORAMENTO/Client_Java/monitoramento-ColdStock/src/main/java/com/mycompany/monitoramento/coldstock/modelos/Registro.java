package com.mycompany.monitoramento.coldstock.modelos;

public class Registro {
    private String nomeComponente;
    private Double valor;
    private String dataHora;
    private Integer fkChamado;
    /* Essa classe é utilizada de molde para receber as informações que foram buscadas
    na função trazerLista da classe Conexao.
    */
    
    
//    public Registros(String nomeComponente, Double valor, String dataValor) {
//        this.nomeComponente = nomeComponente;
//        this.valor = valor;
//        this.dataValor = dataValor;
//    }

    public String getNomeComponente() {
        return nomeComponente;
    }

    public Double getValor() {
        return valor;
    }

   

    public void setNomeComponente(String nomeComponente) {
        this.nomeComponente = nomeComponente;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    
    
    
    
    
    
}
