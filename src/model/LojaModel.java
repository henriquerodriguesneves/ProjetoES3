package model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "lojas")
public class LojaModel {
	
	@Id
	@Column(name = "CNPJ")
	@NotNull
	private String CNPJ = "";
	
	@Column(name = "Nome")
	@NotNull
    private String nome;
	
	@Column(name = "DataCadastro")
	@NotNull
    private LocalDate dataCadastro = LocalDate.now();
	
	@Column(name = "Endereco")
	@NotNull
    private String endereco = "";
	
	@Column(name = "Telefone")
	@NotNull
    private String telefone = "";
    
   

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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