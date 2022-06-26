package model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "funcionarios")
public class FuncionarioModel {
	
	@Id
	@Column(name = "cpf", length = 14)
	@NotNull
	private String cpf;
	
	@Column(name = "nome_funcionario", length = 50)
	@NotNull
	private String nome;
	
	@Column(name = "data_admissao")
	@NotNull
	private LocalDate dataAdmissao;
	
	@Column(name = "telefone_funcionario", length = 11)
	@NotNull
	private String telefone;
	
	@Column(name = "endereco_funcionario", length = 100)
	@NotNull
	private String endereco;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
