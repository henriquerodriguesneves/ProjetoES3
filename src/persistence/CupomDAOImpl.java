package persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Cupom;

public class CupomDAOImpl implements CupomDAO {
    private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/projetoPOO?allowMultiQueries=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";
    private Connection con;

    public CupomDAOImpl() {
    	 try {
             Class.forName("org.mariadb.jdbc.Driver");
             System.out.println("Biblioteca importada");
             Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             System.out.println("Conectado com o banco de dados");

             String sql = "SELECT * FROM cupons";
             PreparedStatement stmt = con.prepareStatement(sql);


             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                 String nome = rs.getString("nome");
                 Date validade = rs.getDate("validade");
                 String codigo = rs.getString("codigo");
                 String valor = rs.getString("valor");
                
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
    public void inserir(Cupom c) {
        String sql = "INSERT INTO cupons (id, nome, validade, codigo, valor) ";
        sql += " VALUES (0, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(c.getValidade()));
            stmt.setString(3, c.getCodigo());
            stmt.setString(4, c.getValor());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Cupom> consultar(String nome) {
        List<Cupom> lista = new ArrayList<>();
        String sql = "SELECT * FROM cupons WHERE nome LIKE '%" + nome + "%'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cupom cupom = new Cupom();
                cupom.setNome(rs.getString("nome"));
                cupom.setValidade(rs.getDate("validade").toLocalDate());
                cupom.setCodigo(rs.getString("codigo"));
                cupom.setValor(rs.getString("valor"));
                lista.add(cupom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}