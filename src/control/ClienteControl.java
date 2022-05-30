package control;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import persistence.ClienteDAOImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//import boundary.ClienteBoundary;
import entity.Cliente;

public class ClienteControl {
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    private StringProperty nome = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> datanasc = new SimpleObjectProperty<>();
    private StringProperty endereco = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private StringProperty cpf = new SimpleStringProperty("");

    private ClienteDAOImpl dao = new ClienteDAOImpl();

    private TableView<Cliente> table = new TableView<>();

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

    public ClienteControl() {
        TableColumn<Cliente, String> col1 = new TableColumn<>("Nome");
        col1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<Cliente, String> col3 = new TableColumn<>("Endereco");
        col3.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        TableColumn<Cliente, String> col4 = new TableColumn<>("Telefone");
        col4.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        TableColumn<Cliente, String> col5 = new TableColumn<>("CPF");
        col5.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        TableColumn<Cliente, String> col2 = new TableColumn<>("Data Nasc");
        col2.setCellValueFactory((itemData)-> {
            LocalDate dt = itemData.getValue().getDataNasc();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new ReadOnlyStringWrapper(dt.format(formatter));
            
        });

  
		table.getColumns().addAll(col1, col2, col3, col4, col5);

        table.setItems(clientes);
    }

    public void adicionar() {
        System.out.println("Nome: " + nome.get());
        System.out.println("DataNasc: " + datanasc.get());
        System.out.println("Endereco: " + endereco.get());
        System.out.println("Telefone: " + telefone.get());
        System.out.println("CPF: " + cpf.get());
        Cliente ce = new Cliente();
        ce.setNome(nome.get());
        ce.setDataNasc(datanasc.get());
        ce.setEndereco(endereco.get());
        ce.setTelefone(telefone.get());
        ce.setCpf(cpf.get());
        clientes.add(ce);
        dao.inserir(ce);
    }

    public void pesquisar() {
        List<Cliente> lista = dao.consultar(nome.get());
        clientes.clear();
        clientes.addAll(lista);
    }
    
    public void limparCampos() {
    	this.nomeProperty().setValue("");
    	this.enderecoProperty().setValue("");
    	this.telefoneProperty().setValue("");
    	this.cpfProperty().setValue("");
    	this.dataProperty().setValue(null);
    }
       
    
    public TableView getTable() {
        return table;
    }

}