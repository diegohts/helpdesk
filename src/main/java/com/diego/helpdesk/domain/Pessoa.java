package com.diego.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.diego.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public abstract class Pessoa implements Serializable {
    // Serializable serve pra criar uma sequencia de bytes para que possam ser
    // trafegados em rede e armazenados em memoria
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geracao dessa chave primaria sera do banco, para cada objeto
                                                        // o banco gera um id diferente
    protected Integer id;
    protected String nome;

    @Column(unique = true) // informando coluna unica no banco, nao vai existir 2 cpfs com mesmo valores
    protected String cpf;

    @Column(unique = true)
    protected String email;
    protected String senha;

    @ElementCollection(fetch = FetchType.EAGER) // Colecao de elementos do tipo integer e quando der um get, quando
                                                // buscar essa pessoa la no meu banco, a lista de perfis abaixo tem que
                                                // vim com usuario. Entao asseguro que minha lista de perfis vai vim com
                                                // usuario
                                                // Porque la no front vai ter algumas rotas que dependem do perfil do
                                                // usuario pra ter acesso
    @CollectionTable(name = "PERFIS") // Vai vim uma tabelinha no banco apenas com perfis
    protected Set<Integer> perfis = new HashSet<>(); // hashset evita a questao de ponteiro nulo e set também nao vai
                                                     // ter dois valores iguais, permite apenas um unico valor

    @JsonFormat(pattern = "dd/mm/yyyy") // porque eh gerado um valor de acordo com o padrao do BD, entao aqui passo o
                                        // padrao que desejo
    protected LocalDate dataCriacao = LocalDate.now(); // pega o tempo atual que a instancia foi criada

    public Pessoa() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
