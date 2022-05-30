package persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Cliente;

public class ClienteDAOImpl implements ClienteDAO {
    private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/projetoPOO?allowMultiQueries=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";
    private Connection con;
     

    public ClienteDAOImpl() {
    	
    	 try {
             Class.forName("org.mariadb.jdbc.Driver");
             System.out.println("Biblioteca importada");
             Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             System.out.println("Conectado com o banco de dados");

             String sql = "SELECT * FROM clientes";
             PreparedStatement stmt = con.prepareStatement(sql);


             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                 String nome = rs.getString("nome");
                 Date datanasc = rs.getDate("Datanasc");
                 String endereco = rs.getString("endereco");
                 String telefone = rs.getString("telefone");
                 String cpf = rs.getString("cpf");
                 
             }
             con.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
 	
        try {
            Class.forName(JDBC_CLASS);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inserir(Cliente ce) {
        String sql = "INSERT INTO clientes (id, nome, DataNasc, endereco, telefone, cpf) ";
        sql += " VALUES (0, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, ce.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(ce.getDataNasc()));
            stmt.setString(3, ce.getEndereco());
            stmt.setString(4, ce.getTelefone());
            stmt.setString(5, ce.getCpf());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Cliente> consultar(String nome) {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nome LIKE '%" + nome + "%'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setDataNasc(rs.getDate("datanasc").toLocalDate());
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCpf(rs.getString("cpf"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}