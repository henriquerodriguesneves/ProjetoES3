package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import boundary.FuncionarioBoundary;
import entity.FuncionarioEntity;
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
import persistence.FuncionarioDAO;
import persistence.FuncionarioDAOImpl;

public class FuncionarioControl {
	
	private ObservableList<FuncionarioEntity> funcionarios = FXCollections.observableArrayList();
	
	private StringProperty cpf = new SimpleStringProperty("");
	private StringProperty nome = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataAdmissao = new SimpleObjectProperty<>();
	private StringProperty telefone = new SimpleStringProperty("");
	private StringProperty endereco = new SimpleStringProperty("");
	
	private FuncionarioDAO dao = new FuncionarioDAOImpl();
	
	private TableView<FuncionarioEntity> table = new TableView<>();
	
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
	
	public FuncionarioControl() {
		TableColumn<FuncionarioEntity, String> col1 = new TableColumn<>("CPF");
		col1.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		TableColumn<FuncionarioEntity, String> col2 = new TableColumn<>("NOME");
		col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
		TableColumn<FuncionarioEntity, String> col3 = new TableColumn<>("DATA ADMISSAO");
		col3.setCellValueFactory((itemData) -> {
			LocalDate dt = itemData.getValue().getDataAdmissao();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(dt.format(formatter));
		});
		TableColumn<FuncionarioEntity, String> col4 = new TableColumn<>("TELEFONE");
		col4.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		TableColumn<FuncionarioEntity, String> col5 = new TableColumn<>("ENDERECO");
		col5.setCellValueFactory(new PropertyValueFactory<>("endereco"));

		
		table.getColumns().addAll(col1, col2, col3, col4, col5);
		table.setItems(funcionarios);
	}
	
	public void adicionar() {
		FuncionarioEntity f = new FuncionarioEntity();
		f.setCpf(cpf.get());
		f.setNome(nome.get());
		f.setDataAdmissao(dataAdmissao.get());
		f.setTelefone(telefone.get());
		f.setEndereco(endereco.get());
		funcionarios.add(f);
		dao.inserir(f);
		
	}
	
	public void pesquisar() {
		List<FuncionarioEntity> lista = dao.consultar(nome.get());
		funcionarios.clear();
		funcionarios.addAll(lista);
		
		}
	
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

}
