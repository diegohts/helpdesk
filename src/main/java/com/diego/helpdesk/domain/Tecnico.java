package com.diego.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.diego.helpdesk.domain.enums.Perfil;

@Entity
public class Tecnico extends Pessoa {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "tecnico") // UM Tecnico para VARIOS Chamados,
                                     // Mapeado por tecnico pela classe Chamado
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.TECNICO);
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
