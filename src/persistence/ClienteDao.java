package persistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Cliente;

public class ClienteDao implements Dao<Cliente> {

	private SessionFactory sf;
	
	public ClienteDao(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void insert(Cliente ce) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(ce);
		transaction.commit();			
	}

	@Override
	public void update(Cliente ce) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(ce);
		transaction.commit();			
	}

	@Override
	public void delete(Cliente ce) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(ce);
		transaction.commit();			
	}

	@Override
	public Cliente selectOne(Cliente ce) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		ce = entityManager.find(Cliente.class, ce.getId());
		return ce;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> selectAll() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM clientes");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> clientesResultSet = query.getResultList();
		List<Cliente> Clientes = new ArrayList<Cliente>();
		for (Object[] o : clientesResultSet) {
			Cliente ce = new Cliente();
			ce.setId(o[0].toString());
			ce.setNome(o[1].toString());
			ce.setDataNasc(LocalDate.parse(o[2].toString()));
			ce.setEndereco(o[3].toString());
			ce.setTelefone(o[4].toString());
			ce.setCpf(o[5].toString());

			Clientes.add(ce);
		}
		
		return Clientes;
	}

}

