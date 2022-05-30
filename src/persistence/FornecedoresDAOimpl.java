package persistence;

import entity.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedoresDAOimpl implements FornecedoresDAO {
    private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/projetoPOO?allowMultiQueries=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";
    private Connection con;

    public FornecedoresDAOimpl() {
        try {
            Class.forName(JDBC_CLASS);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserir(Fornecedor e) {
        String sql = "INSERT INTO fornecedor (id, nome, prazo, produto, telefone, CNPJ) ";
        sql += " VALUES (0, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, e.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(e.getPrazo()));
            stmt.setString(3, e.getProduto());
            stmt.setString(4, e.getTelefone());
            stmt.setString(5, e.getCNPJ());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Fornecedor> consultar(String nome) {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor WHERE nome LIKE '%" + nome + "%'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setPrazo(rs.getDate("prazo").toLocalDate());
                fornecedor.setProduto(rs.getString("produto"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setCNPJ(rs.getString("CNPJ"));
                lista.add(fornecedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}