package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.FuncionarioEntity;

public class FuncionarioDAOImpl implements FuncionarioDAO {
	private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
	private final static String JDBC_URL = "jdbc:mariadb://localhost:3306/projetoPOO?allowMultiQueries=true";
	private final static String JDBC_USER = "root";
	private final static String JDBC_PASS = "";
	private Connection con;
	
	public FuncionarioDAOImpl() {
		try {
			Class.forName(JDBC_CLASS);
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
			System.out.println("Biblioteca importada");
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	@Override
	public void inserir(FuncionarioEntity f) {
		 String sql = "INSERT INTO funcionario (cpf, nome, dataAdmissao, telefone, endereco) ";
		 sql += " VALUES (?, ?, ?, ?, ?) ";
		 try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, f.getCpf());
			stmt.setString(2, f.getNome());
			stmt.setDate(3, java.sql.Date.valueOf(f.getDataAdmissao()));
			stmt.setString(4, f.getTelefone());
			stmt.setString(5, f.getEndereco());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			 ex.printStackTrace();
		}
		
	}

	@Override
	public List<FuncionarioEntity> consultar(String nome) {
		 List<FuncionarioEntity> lista = new ArrayList<>();
		 String sql = "SELECT * FROM funcionario WHERE nome LIKE '%" + nome +"%'";
		 try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FuncionarioEntity funcionario = new FuncionarioEntity();
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setDataAdmissao(rs.getDate("dataAdmissao").toLocalDate());
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setEndereco(rs.getString("endereco"));
				lista.add(funcionario);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}

}
