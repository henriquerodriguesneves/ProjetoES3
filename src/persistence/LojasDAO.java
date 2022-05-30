package persistence;

import entity.Loja;

import java.util.List;

public interface LojasDAO {
    void inserir(Loja e);
    List<Loja> consultar(String nome);
}