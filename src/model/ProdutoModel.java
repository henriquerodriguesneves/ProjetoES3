package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "produtos")
public class ProdutoModel {
	
	@Id
	@Column(name = "codigo_produto")
	@NotNull
	private String codigo;
	
	@Column(name = "descricao_produto", length = 50)
	@NotNull
	private String descricao;
	
	@Column(name = "fabricante_produto", length = 50)
	@NotNull
	private String fabricante;
	
	@Column(name = "tipo_produto", length = 20)
	@NotNull
	private String tipo;
	
	@Column(name = "lote_produto", length = 10)
	@NotNull
	private String lote;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}

}
