package model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clientes")

public class ClienteModel {
	@Id
	@Column(name = "codigo_cliente")
	@NotNull
	private String id;
	
	@Column(name = "nome_cliente")
	@NotNull
	private String nome;
	
	@Column(name = "data_cliente")
	@NotNull
	private LocalDate DataNasc;
	
	@Column(name = "endereco_cliente")
	@NotNull
	private String endereco;
	
	@Column(name = "telefone_cliente")
	@NotNull
	private String telefone;
	
	@Column(name = "cpf_cliente")
	@NotNull
	private String cpf;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNasc() {
		return DataNasc;
	}
	public void setDataNasc(LocalDate dataNasc) {
		DataNasc = dataNasc;
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}