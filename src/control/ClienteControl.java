package control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.SessionFactory;
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
import model.ClienteModel;
import persistence.ClienteDao;
import util.HibernateUtil;

public class ClienteControl implements OperacoesController<ClienteModel>{
	
	private ObservableList<ClienteModel> clientes = FXCollections.observableArrayList();
	
	private StringProperty id = new SimpleStringProperty();
	private StringProperty nome = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> datanasc = new SimpleObjectProperty<>();
	private StringProperty endereco = new SimpleStringProperty("");
	private StringProperty telefone = new SimpleStringProperty();
	private StringProperty cpf = new SimpleStringProperty();
	

	
	private TableView<ClienteModel> table = new TableView<>();
	
	public StringProperty idProperty() {
		return id;
	}
	public StringProperty nomeProperty() {
		return nome;
	}
	public ObjectProperty<LocalDate> dataProperty() {
        return datanasc;
    }
	public StringProperty enderecoProperty() {
		return endereco;
	}
	public StringProperty telefoneProperty() {
		return telefone;
	}
	public StringProperty cpfProperty() {
		return cpf;
	}
	
	@SuppressWarnings("unchecked")
	public ClienteControl() {
		TableColumn<ClienteModel, String> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<ClienteModel, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        TableColumn<ClienteModel, String> col4 = new TableColumn<>("Endereco");
        col4.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        
        TableColumn<ClienteModel, String> col5 = new TableColumn<>("Telefone");
        col4.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        
        TableColumn<ClienteModel, String> col6 = new TableColumn<>("Cpf");
        col4.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        TableColumn<ClienteModel, String> col3 = new TableColumn<>("Data");
        col3.setCellValueFactory((itemData)-> {
            LocalDate dt = itemData.getValue().getDataNasc();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new ReadOnlyStringWrapper(dt.format(formatter));           
        });


		
		table.getColumns().addAll(col1, col2, col3, col4, col5, col6);
		table.setItems(clientes);
	}
	
	public void adicionar() {
	}
	
	public void pesquisar() {	
		
	}
	
	public void limparCampos() {
		this.idProperty().setValue("");
		this.nomeProperty().setValue("");
		this.dataProperty().setValue(null);
		this.enderecoProperty().setValue("");
		this.telefoneProperty().setValue("");
		this.cpfProperty().setValue("");
	}
	@SuppressWarnings("rawtypes")
	public TableView getTable() {
		return table;
	}
	@Override
	public void salvar(ClienteModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ClienteDao atDao = new ClienteDao(sessionFactory);
		atDao.insert(t);
		
	}
	@Override
	public void modificar(ClienteModel t) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void remover(ClienteModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ClienteDao atDao = new ClienteDao(sessionFactory);
		atDao.delete(t);
		
	}
	@Override
	public ClienteModel consultar(ClienteModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ClienteDao atDao = new ClienteDao(sessionFactory);
		t = atDao.selectOne(t);
		return t;
	}
	@Override
	public List<ClienteModel> listar() throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ClienteDao atDao = new ClienteDao(sessionFactory);
		List<ClienteModel> clientes = atDao.selectAll();
		return clientes;
	}
}
