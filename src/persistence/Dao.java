package persistence;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	
	public void insert(T t) throws SQLException;
	public void update(T t) throws SQLException;
	public void delete(T t) throws SQLException;
	public T selectOne(T t) throws SQLException;
	public List<T> selectAll() throws SQLException;
	

}
