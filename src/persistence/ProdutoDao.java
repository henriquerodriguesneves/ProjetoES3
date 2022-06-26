package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.ProdutoModel;

public class ProdutoDao implements Dao<ProdutoModel> {

	private SessionFactory sf;
	
	public ProdutoDao(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void insert(ProdutoModel at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(at);
		transaction.commit();			
	}

	@Override
	public void update(ProdutoModel at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(at);
		transaction.commit();			
	}

	@Override
	public void delete(ProdutoModel at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(at);
		transaction.commit();			
	}

	@Override
	public ProdutoModel selectOne(ProdutoModel at) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		at = entityManager.find(ProdutoModel.class, at.getCodigo());
		return at;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProdutoModel> selectAll() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM produtos");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		List<Object[]> ProdutosResultSet = query.getResultList();
		List<ProdutoModel> Produtos = new ArrayList<ProdutoModel>();
		for (Object[] o : ProdutosResultSet) {
			ProdutoModel at = new ProdutoModel();
			at.setCodigo(o[0].toString());
			at.setDescricao(o[1].toString());
			at.setFabricante(o[2].toString());
			at.setTipo(o[3].toString());
			at.setLote(o[4].toString());
			Produtos.add(at);
		}
		
		return Produtos;
	}

}
