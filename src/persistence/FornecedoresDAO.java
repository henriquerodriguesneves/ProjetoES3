package persistence;

import entity.Fornecedor;

import java.util.List;

public interface FornecedoresDAO {
    void inserir(Fornecedor e);
    List<Fornecedor> consultar(String nome);
}