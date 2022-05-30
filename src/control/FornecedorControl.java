package control;

import persistence.FornecedoresDAO;
import persistence.FornecedoresDAOimpl;
import entity.Fornecedor;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FornecedorControl {
    private ObservableList<Fornecedor> fornecedor = FXCollections.observableArrayList();

    private StringProperty nome = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> prazo = new SimpleObjectProperty<>();
    private StringProperty produto = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private StringProperty CNPJ = new SimpleStringProperty("");

    private FornecedoresDAO dao = new FornecedoresDAOimpl();

    private TableView<Fornecedor> table = new TableView<>();

    public StringProperty nomeProperty() {
        return nome;
    }

    public ObjectProperty<LocalDate> prazoProperty() {
        return prazo;
    }

    public StringProperty produtoProperty() {
        return produto;
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public StringProperty CNPJProperty() {
        return CNPJ;
    }

    public FornecedorControl() {
        TableColumn<Fornecedor, String> col1 = new TableColumn<>("Nome");
        col1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<Fornecedor, String> col3 = new TableColumn<>("Produto");
        col3.setCellValueFactory(new PropertyValueFactory<>("produto"));
        TableColumn<Fornecedor, String> col4 = new TableColumn<>("Telefone");
        col4.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        TableColumn<Fornecedor, String> col5 = new TableColumn<>("CNPJ");
        col5.setCellValueFactory(new PropertyValueFactory<>("CNPJ"));
        TableColumn<Fornecedor, String> col2 = new TableColumn<>("Prazo");
        col2.setCellValueFactory((itemData)-> {
            LocalDate dt = itemData.getValue().getPrazo();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new ReadOnlyStringWrapper(dt.format(formatter));
        });

        table.getColumns().addAll(col1, col2, col3, col4, col5);

        table.setItems(fornecedor);
    }

    public void adicionar() {
        System.out.println("Nome: " + nome.get());
        System.out.println("Prazo: " + prazo.get());
        System.out.println("Endereco: " + produto.get());
        System.out.println("Telefone: " + telefone.get());
        System.out.println("CNPJ: " + CNPJ.get());
        Fornecedor e = new Fornecedor();
        e.setNome(nome.get());
        e.setPrazo(prazo.get());
        e.setProduto(produto.get());
        e.setTelefone(telefone.get());
        e.setCNPJ(CNPJ.get());
        fornecedor.add(e);
        dao.inserir(e);
    }

    public void pesquisar() {
        List<Fornecedor> lista = dao.consultar(nome.get());
        fornecedor.clear();
        fornecedor.addAll(lista);
    }
    public void limparCampos() {
    	this.nomeProperty().setValue("");
    	this.prazoProperty().setValue(null);
    	this.produtoProperty().setValue("");
    	this.CNPJProperty().setValue("");
    	this.telefoneProperty().setValue("");
    }
    public TableView getTable() {
        return table;
    }
}