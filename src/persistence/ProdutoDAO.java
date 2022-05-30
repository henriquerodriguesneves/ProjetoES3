package persistence;

import java.util.List;

import entity.ProdutoEntity;

public interface ProdutoDAO {
	
	void inserir(ProdutoEntity p);
	List<ProdutoEntity> consultar(String descricao);

}
