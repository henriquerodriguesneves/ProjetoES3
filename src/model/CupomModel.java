package model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cupons")

public class CupomModel {
	
	@Id
	@Column(name = "id_cupom")
	@NotNull
	private String id;
	
	@Column(name = "nome_cupom")
	@NotNull
	private String nome;
	
	@Column(name = "validade_cupom")
	@NotNull
	private LocalDate validade;
	
	@Column(name = "codigo_cupom")
	@NotNull
	private String codigo;
	
	@Column(name = "valor_cupom")
	@NotNull
	private String valor;
	
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
	public LocalDate getValidade() {
		return validade;
	}
	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = "Henrique";
	}


}