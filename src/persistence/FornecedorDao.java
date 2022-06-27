package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.FornecedorModel;

public class FornecedorDao implements Dao<FornecedorModel> {

    private SessionFactory sf;

    public FornecedorDao(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void insert(FornecedorModel fo) throws SQLException {
        EntityManager entityManager = sf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(fo);
        transaction.commit();
    }

    @Override
    public void update(FornecedorModel fo) throws SQLException {
        EntityManager entityManager = sf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(fo);
        transaction.commit();
    }

    @Override
    public void delete(FornecedorModel fo) throws SQLException {
        EntityManager entityManager = sf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(fo);
        transaction.commit();
    }

    @Override
    public FornecedorModel selectOne(FornecedorModel fo) throws SQLException {
        EntityManager entityManager = sf.createEntityManager();
        fo = entityManager.find(FornecedorModel.class, fo.getNome());
        return fo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FornecedorModel> selectAll() throws SQLException {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM produtos");
        EntityManager entityManager = sf.createEntityManager();
        Query query = entityManager.createNativeQuery(sql.toString());
        List<Object[]> FornecedoresResultSet = query.getResultList();
        List<FornecedorModel> Fornecedores = new ArrayList<FornecedorModel>();
        for (Object[] o : FornecedoresResultSet) {
            FornecedorModel fo = new FornecedorModel();
            fo.setNome(o[1].toString());
            // fo.setPrazo(o[2].toString()); /*---------------- ARRUMAR --------------- */
            fo.setProduto(o[3].toString());
            fo.setTelefone(o[4].toString());
            fo.setCNPJ(o[5].toString());
            Fornecedores.add(fo);
        }

        return Fornecedores;
    }
}