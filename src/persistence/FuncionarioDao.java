package persistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.FuncionarioModel;

public class FuncionarioDao implements Dao<FuncionarioModel> {

	private SessionFactory sf;
	
	public FuncionarioDao(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void insert(FuncionarioModel at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(at);
		transaction.commit();			
	}

	@Override
	public void update(FuncionarioModel at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(at);
		transaction.commit();			
	}

	@Override
	public void delete(FuncionarioModel at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(at);
		transaction.commit();			
	}

	@Override
	public FuncionarioModel selectOne(FuncionarioModel at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		at = entityManager.find(FuncionarioModel.class, at.getCpf());
		return at;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FuncionarioModel> selectAll() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM funcionarios");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> FuncionariosResultSet = query.getResultList();
		List<FuncionarioModel> Funcionarios = new ArrayList<FuncionarioModel>();
		for (Object[] o : FuncionariosResultSet) {
			FuncionarioModel at = new FuncionarioModel();
			at.setCpf(o[0].toString());
			at.setNome(o[1].toString());
			at.setEndereco(o[2].toString());
			at.setTelefone(o[3].toString());
			at.setDataAdmissao(LocalDate.parse(o[4].toString()));
			Funcionarios.add(at);
		}
		
		return Funcionarios;
	}

}
