package com.diego.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.diego.helpdesk.domain.enums.Perfil;

@Entity
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente") // UM Cliente para VARIOS Chamados,
    // Mapeado por cliente pela classe Chamado
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

}
