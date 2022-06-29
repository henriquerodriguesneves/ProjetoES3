package control;

import persistence.LojaDao;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LojaModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LojaControl {
    private ObservableList<LojaModel> loja = FXCollections.observableArrayList();

    private StringProperty nome = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> dataCadastro = new SimpleObjectProperty<>();
    private StringProperty endereco = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private StringProperty CNPJ = new SimpleStringProperty("");


    private TableView<LojaModel> table = new TableView<>();

    public StringProperty nomeProperty() {
        return nome;
    }

    public ObjectProperty<LocalDate> dataCadastroProperty() {
        return dataCadastro;
    }

    public StringProperty enderecoProperty() {
        return endereco;
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public StringProperty CNPJProperty() {
        return CNPJ;
    }


    public LojaControl() {
        TableColumn<LojaModel, String> col1 = new TableColumn<>("Nome");
        col1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<LojaModel, String> col3 = new TableColumn<>("Endereco");
        col3.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        TableColumn<LojaModel, String> col4 = new TableColumn<>("Telefone");
        col4.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        TableColumn<LojaModel, String> col5 = new TableColumn<>("CNPJ");
        col5.setCellValueFactory(new PropertyValueFactory<>("CNPJ"));
        TableColumn<LojaModel, String> col2 = new TableColumn<>("Data Cadastro");
        col2.setCellValueFactory((itemData)-> { LocalDate dt = itemData.getValue().getDataCadastro();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new ReadOnlyStringWrapper(dt.format(formatter));
        });


        table.getColumns().addAll(col1, col2, col3, col4, col5);

        table.setItems(loja);
    }

    public void adicionar() {
    }

    public void pesquisar() {
    	
    }
    public void limparCampos() {
    	this.nomeProperty().setValue("");
    	this.dataCadastroProperty().setValue(null);
    	this.enderecoProperty().setValue("");
    	this.telefoneProperty().setValue("");
    	this.CNPJProperty().setValue("");
    }
    public TableView getTable() {
        return table;
    }
    
}