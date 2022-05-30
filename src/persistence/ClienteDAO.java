package persistence;

import java.util.List;

import entity.Cliente;

public interface ClienteDAO {
    void inserir(Cliente ce);
    List<Cliente> consultar(String nome);
}