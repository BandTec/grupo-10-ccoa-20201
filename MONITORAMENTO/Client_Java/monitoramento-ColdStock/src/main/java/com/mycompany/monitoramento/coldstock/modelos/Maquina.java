
package com.mycompany.monitoramento.coldstock.modelos;

/*
    Essa classe é responsavel por ser o molde para a lista gerada a partir do select das máquinas.
    A tabela das máquinas possui idMaquina, nomeMaquina e tipoMaquina, por isso essa classe
    possui os mesmos atributos.
    Esses atributos são protegidos por get e set, evitando que eles sejam editados sem utilizar os metodos. 
    Por exemplo, só é possivel editar o idMaquina usando o setIdMaquina, e só é possivel acessar
    o conteudo do idMaquina usando o getIdMaquina.
*/
public class Maquina {
    public Integer idMaquina;
    private String nomeMaquina;
    private String tipoMaquina;
    public static Integer fkmaquina = 1;
    
    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNomeMaquina() {
        return nomeMaquina;
    }

    public void setNomeMaquina(String nomeMaquina) {
        this.nomeMaquina = nomeMaquina;
    }

    public String getTipoMaquina() {
        return tipoMaquina;
    }

    public void setTipoMaquina(String tipoMaquina) {
        this.tipoMaquina = tipoMaquina;
    }
}
