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
import model.CupomModel;
import persistence.CupomDao;
import util.HibernateUtil;

public class CupomControl implements OperacoesController<CupomModel>{
	
	private ObservableList<CupomModel> Cupoms = FXCollections.observableArrayList();
	
	private StringProperty id = new SimpleStringProperty();
	private StringProperty nome = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> validade = new SimpleObjectProperty<>();
	private StringProperty codigo = new SimpleStringProperty("");
	private StringProperty valor = new SimpleStringProperty();

	

	
	private TableView<CupomModel> table = new TableView<>();
	
	public StringProperty idProperty() {
		return id;
	}
	public StringProperty nomeProperty() {
		return nome;
	}
	public ObjectProperty<LocalDate> validadeProperty() {
        return validade;
    }
	public StringProperty codigoProperty() {
		return codigo;
	}
	public StringProperty valorProperty() {
		return valor;
	}

	
	@SuppressWarnings("unchecked")
	public CupomControl() {
		TableColumn<CupomModel, String> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<CupomModel, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        TableColumn<CupomModel, String> col4 = new TableColumn<>("Codigo");
        col4.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        
        TableColumn<CupomModel, String> col5 = new TableColumn<>("Valor");
        col4.setCellValueFactory(new PropertyValueFactory<>("valor"));
        
        
        TableColumn<CupomModel, String> col3 = new TableColumn<>("Validade");
        col3.setCellValueFactory((itemData)-> {
            LocalDate dt = itemData.getValue().getValidade();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new ReadOnlyStringWrapper(dt.format(formatter));           
        });


		
		table.getColumns().addAll(col1, col2, col3, col4, col5);
		table.setItems(Cupoms);
	}
	
	public void adicionar() {
	}
	
	public void pesquisar() {	
		
	}
	
	public void limparCampos() {
		this.idProperty().setValue("");
		this.nomeProperty().setValue("");
		this.validadeProperty().setValue(null);
		this.codigoProperty().setValue("");
		this.valorProperty().setValue("");
	}
	@SuppressWarnings("rawtypes")
	public TableView getTable() {
		return table;
	}
	@Override
	public void salvar(CupomModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		CupomDao atDao = new CupomDao(sessionFactory);
		atDao.insert(t);
		
	}
	@Override
	public void modificar(CupomModel t) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void remover(CupomModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		CupomDao atDao = new CupomDao(sessionFactory);
		atDao.delete(t);
		
	}
	@Override
	public CupomModel consultar(CupomModel t) throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		CupomDao atDao = new CupomDao(sessionFactory);
		t = atDao.selectOne(t);
		return t;
	}
	@Override
	public List<CupomModel> listar() throws SQLException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		CupomDao atDao = new CupomDao(sessionFactory);
		List<CupomModel> Cupoms = atDao.selectAll();
		return Cupoms;
	}
}
