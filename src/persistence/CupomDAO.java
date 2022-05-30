package persistence;

import java.util.List;

import entity.Cupom;

public interface CupomDAO {
    void inserir(Cupom c);
    List<Cupom> consultar(String nome);
}