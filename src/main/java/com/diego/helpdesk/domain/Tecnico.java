package com.diego.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Pessoa {
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {

    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    /**
     * @return List<Chamado> return the chamados
     */
    public List<Chamado> getChamados() {
        return chamados;
    }

    /**
     * @param chamados the chamados to set
     */
    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

}
