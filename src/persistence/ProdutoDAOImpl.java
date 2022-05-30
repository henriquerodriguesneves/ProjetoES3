package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.ProdutoEntity;

public class ProdutoDAOImpl implements ProdutoDAO{
	
	private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
	private final static String JDBC_URL = "jdbc:mariadb://localhost:3306/projetoPOO?allowMultiQueries=true";
	private final static String JDBC_USER = "root";
	private final static String JDBC_PASS = "";
	private Connection con;
	
	public ProdutoDAOImpl() {
		try {
			Class.forName(JDBC_CLASS);
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
			System.out.println("Biblioteca importada");
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

	@Override
	public void inserir(ProdutoEntity p) {
		 String sql = "INSERT INTO produto (codigo, descricao, fabricante, tipo, lote) ";
		 sql += " VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getCodigo());
			stmt.setString(2, p.getDescricao());
			stmt.setString(3, p.getFabricante());
			stmt.setString(4, p.getTipo());
			stmt.setString(5, p.getLote());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			 ex.printStackTrace();
		}
	}

	@Override
	public List<ProdutoEntity> consultar(String descricao) {
		 List<ProdutoEntity> lista= new ArrayList<>();
		 String sql = "SELECT * FROM produto WHERE descricao LIKE '%" + descricao + "%'";
		 try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ProdutoEntity produto = new ProdutoEntity();
				produto.setCodigo(rs.getString("codigo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setFabricante(rs.getString("fabricante"));
				produto.setTipo(rs.getString("tipo"));
				produto.setLote(rs.getString("lote"));
				lista.add(produto);
			}
		} catch (SQLException ex) {
			 ex.printStackTrace();
		}
		return lista;
	}

}
