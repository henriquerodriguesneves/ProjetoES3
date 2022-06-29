package persistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.LojaModel;

public class LojaDao implements Dao<LojaModel> {

	private SessionFactory sf;
	
	public LojaDao(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void insert(LojaModel lj) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(lj);
		transaction.commit();			
	}

	@Override
	public void update(LojaModel lj) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(lj);
		transaction.commit();			
	}

	@Override
	public void delete(LojaModel lj) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(lj);
		transaction.commit();			
	}

	@Override
	public LojaModel selectOne(LojaModel lj) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		lj = entityManager.find(LojaModel.class, lj.getCNPJ());
		return lj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LojaModel> selectAll() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM Lojas");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> LojasResultSet = query.getResultList();
		List<LojaModel> Lojas = new ArrayList<LojaModel>();
		for (Object[] o : LojasResultSet) {
			LojaModel lj = new LojaModel();
			lj.setCNPJ(o[0].toString());
			lj.setNome(o[1].toString());
			lj.setDataCadastro(LocalDate.parse(o[3].toString()));
			lj.setEndereco(o[4].toString());
			lj.setTelefone(o[5].toString());
			Lojas.add(lj);
		}
		
		return Lojas;
	}

}
