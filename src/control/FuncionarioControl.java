package control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.SessionFactory;

//import boundary.FuncionarioBoundary;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.FuncionarioModel;
import persistence.FuncionarioDao;
import util.HibernateUtil;

public class FuncionarioControl implements OperacoesController<FuncionarioModel>{
	
	private ObservableList<FuncionarioModel> funcionarios = FXCollections.observableArrayList();
	
	private StringProperty cpf = new SimpleStringProperty("");
	private StringProperty nome = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataAdmissao = new SimpleObjectProperty<>();
	private StringProperty telefone = new SimpleStringProperty("");
	private StringProperty endereco = new SimpleStringProperty("");
	
//	private FuncionarioDAO dao = new FuncionarioDAOImpl();
	
	private TableView<FuncionarioModel> table = new TableView<>();
	
	public StringProperty cpfProperty() {
		return cpf;
	}
	public StringProperty nomeProperty() {
		return nome;
	}
	public ObjectProperty<LocalDate> dataAdmissaoProperty(){
		return dataAdmissao;
	}
	public StringProperty telefoneProperty() {
		return telefone;
	}
	public StringProperty enderecoProperty() {
		return endereco;
	}
	
	@SuppressWarnings("unchecked")
	public FuncionarioControl() {
		TableColumn<FuncionarioModel, String> col1 = new TableColumn<>("CPF");
		col1.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		TableColumn<FuncionarioModel, String> col2 = new TableColumn<>("NOME");
		col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
		TableColumn<FuncionarioModel, String> col3 = new TableColumn<>("DATA ADMISSAO");
		col3.setCellValueFactory((itemData) -> {
			LocalDate dt = itemData.getValue().getDataAdmissao();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(dt.format(formatter));
		});
		TableColumn<FuncionarioModel, String> col4 = new TableColumn<>("TELEFONE");
		col4.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		TableColumn<FuncionarioModel, String> col5 = new TableColumn<>("ENDERECO");
		col5.setCellValueFactory(new PropertyValueFactory<>("endereco"));

		
		table.getColumns().addAll(col1, col2, col3, col4, col5);
		table.setItems(funcionarios);
	}
	
	public void adicionar() {
//		FuncionarioModel f = new FuncionarioModel();
//		f.setCpf(cpf.get());
//		f.setNome(nome.get());
//		f.setDataAdmissao(dataAdmissao.get());
//		f.setTelefone(telefone.get());
//		f.setEndereco(endereco.get());
//		funcionarios.add(f);
//		dao.inserir(f);
		
	}
	
	public void pesquisar() {
//		List<FuncionarioModel> lista = dao.consultar(nome.get());
//		funcionarios.clear();
//		funcionarios.addAll(lista);
		
		}
	
	@SuppressWarnings("rawtypes")
	public TableView getTable() {
		return table;
	}
	
	public void limparCampos() {
		this.cpfProperty().setValue("");
		this.nomeProperty().setValue("");
		this.dataAdmissaoProperty().setValue(null);
		this.telefoneProperty().setValue("");
		this.enderecoProperty().setValue("");
	}
	@Override
	public void salvar(FuncionarioModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		FuncionarioDao atDao = new FuncionarioDao(sessionFactory);
		atDao.insert(t);
		
	}
	@Override
	public void modificar(FuncionarioModel t) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void remover(FuncionarioModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		FuncionarioDao atDao = new FuncionarioDao(sessionFactory);
		atDao.delete(t);
		
	}
	@Override
	public FuncionarioModel consultar(FuncionarioModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		FuncionarioDao atDao = new FuncionarioDao(sessionFactory);
		t = atDao.selectOne(t);
		return t;
	}
	@Override
	public List<FuncionarioModel> listar() throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		FuncionarioDao atDao = new FuncionarioDao(sessionFactory);
		List<FuncionarioModel> produtos = atDao.selectAll();
		return produtos;
	}

}
