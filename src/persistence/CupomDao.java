package persistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Cupom;

public class CupomDao implements Dao<Cupom> {

	private SessionFactory sf;
	
	public CupomDao(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void insert(Cupom cm) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(cm);
		transaction.commit();			
	}

	@Override
	public void update(Cupom cm) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(cm);
		transaction.commit();			
	}

	@Override
	public void delete(Cupom cm) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(cm);
		transaction.commit();			
	}

	@Override
	public Cupom selectOne(Cupom cm) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		cm = entityManager.find(Cupom.class, cm.getId());
		return cm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cupom> selectAll() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM cupons");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> cuponsResultSet = query.getResultList();
		List<Cupom> Cupons = new ArrayList<Cupom>();
		for (Object[] o : cuponsResultSet) {
			Cupom cm = new Cupom();
			cm.setId(o[0].toString());
			cm.setNome(o[1].toString());
			cm.setValidade(LocalDate.parse(o[2].toString()));
			cm.setCodigo(o[3].toString());
			cm.setValor(o[4].toString());
			Cupons.add(cm);
		}
		
		return Cupons;
	}

}

