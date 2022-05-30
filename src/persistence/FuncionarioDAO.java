package persistence;

import java.util.List;

import entity.FuncionarioEntity;

public interface FuncionarioDAO {
	
	void inserir(FuncionarioEntity f);
	List<FuncionarioEntity> consultar(String nome);

}
