package persistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.ClienteModel;

public class ClienteDao implements Dao<ClienteModel> {

	private SessionFactory sf;
	
	public ClienteDao(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void insert(ClienteModel ce) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(ce);
		transaction.commit();			
	}

	@Override
	public void update(ClienteModel ce) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(ce);
		transaction.commit();			
	}

	@Override
	public void delete(ClienteModel ce) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(ce);
		transaction.commit();			
	}

	@Override
	public ClienteModel selectOne(ClienteModel ce) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		ce = entityManager.find(ClienteModel.class, ce.getId());
		return ce;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClienteModel> selectAll() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM clientes");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> clientesResultSet = query.getResultList();
		List<ClienteModel> Clientes = new ArrayList<ClienteModel>();
		for (Object[] o : clientesResultSet) {
			ClienteModel ce = new ClienteModel();
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

