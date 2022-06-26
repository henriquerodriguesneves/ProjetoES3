package control;

import java.sql.SQLException;
import java.util.List;

public interface OperacoesController<T> {

	public void salvar(T t) throws SQLException;
	public void modificar(T t) throws SQLException;
	public void remover(T t) throws SQLException;
	public T consultar(T t) throws SQLException;
	public List<T> listar() throws SQLException;
	
}
