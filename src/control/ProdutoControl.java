package control;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;

//import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ProdutoModel;
//import persistence.ProdutoDao;
//import persistence.ProdutoDAOImpl;
import persistence.ProdutoDao;
import util.HibernateUtil;

public class ProdutoControl implements OperacoesController<ProdutoModel>{
	
	private ObservableList<ProdutoModel> produtos = FXCollections.observableArrayList();
	
	private StringProperty codigo = new SimpleStringProperty();
	private StringProperty descricao = new SimpleStringProperty("");
	private StringProperty fabricante = new SimpleStringProperty("");
	private StringProperty tipo = new SimpleStringProperty("");
	private StringProperty lote = new SimpleStringProperty();
	
//	private ProdutoDAO dao = new ProdutoDAOImpl();
	
	private TableView<ProdutoModel> table = new TableView<>();
	
	public StringProperty codigoProperty() {
		return codigo;
	}
	public StringProperty descricaoProperty() {
		return descricao;
	}
	public StringProperty fabricanteProperty() {
		return fabricante;
	}
	public StringProperty tipoProperty() {
		return tipo;
	}
	public StringProperty loteProperty() {
		return lote;
	}
	
	@SuppressWarnings("unchecked")
	public ProdutoControl() {
		TableColumn<ProdutoModel, String> col1 = new TableColumn<>("Codigo");
		col1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		TableColumn<ProdutoModel, String> col2 = new TableColumn<>("Descricao");
		col2.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		TableColumn<ProdutoModel, String> col3 = new TableColumn<>("Fabricante");
		col3.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
		TableColumn<ProdutoModel, String> col4 = new TableColumn<>("Tipo");
		col4.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		TableColumn<ProdutoModel, String> col5 = new TableColumn<>("Lote");
		col5.setCellValueFactory(new PropertyValueFactory<>("lote"));
		
		table.getColumns().addAll(col1, col2, col3, col4, col5);
		table.setItems(produtos);
	}
	
	public void adicionar() {
//		ProdutoModel p = new ProdutoModel();
//		p.setCodigo(codigo.get());
//		p.setDescricao(descricao.get());
//		p.setFabricante(fabricante.get());
//		p.setTipo(tipo.get());
//		p.setLote(lote.get());
//		produtos.add(p);
//		dao.inserir(p);
	}
	
	public void pesquisar() {
//		List<ProdutoModel> lista = dao.consultar(descricao.get());
//		produtos.clear();
//		produtos.addAll(lista);		
		
	}
	
	public void limparCampos() {
		this.codigoProperty().setValue("");
		this.descricaoProperty().setValue("");
		this.fabricanteProperty().setValue("");
		this.tipoProperty().setValue("");
		this.loteProperty().setValue("");
	}
	@SuppressWarnings("rawtypes")
	public TableView getTable() {
		return table;
	}
	@Override
	public void salvar(ProdutoModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ProdutoDao atDao = new ProdutoDao(sessionFactory);
		atDao.insert(t);
		
	}
	@Override
	public void modificar(ProdutoModel t) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void remover(ProdutoModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ProdutoDao atDao = new ProdutoDao(sessionFactory);
		atDao.delete(t);
		
	}
	@Override
	public ProdutoModel consultar(ProdutoModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ProdutoDao atDao = new ProdutoDao(sessionFactory);
		t = atDao.selectOne(t);
		return t;
	}
	@Override
	public List<ProdutoModel> listar() throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ProdutoDao atDao = new ProdutoDao(sessionFactory);
		List<ProdutoModel> produtos = atDao.selectAll();
		return produtos;
	}
}
