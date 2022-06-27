package model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "fornecedor")
public class FornecedorModel {

    @Id
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name = "nome_fornecedor", length = 50)
    @NotNull
    private String nome = "";

    @Column(name = "prazo_fornecedor")
    @NotNull
    private LocalDate prazo = LocalDate.now();

    @Column(name = "produto_fornecedor", length = 50)
    @NotNull
    private String produto = "";

    @Column(name = "telefone_fornecedor", length = 50)
    @NotNull
    private String telefone = "";

    @Column(name = "CNPJ_fornecedor", length = 50)
    @NotNull
    private String CNPJ = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
}