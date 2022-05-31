package control;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import persistence.CupomDAOImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//import boundary.CupomBoundary;
import entity.Cupom;

public class CupomControl {
    private ObservableList<Cupom> cupons = FXCollections.observableArrayList();

    private StringProperty nome = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> validade = new SimpleObjectProperty<>();
    private StringProperty codigo = new SimpleStringProperty("");
    private StringProperty valor = new SimpleStringProperty(""); //alterar para double

    private CupomDAOImpl dao = new CupomDAOImpl();

    private TableView<Cupom> table = new TableView<>();

    public StringProperty nomeProperty() {
        return nome;
    }
    public ObjectProperty<LocalDate> dataProperty() {
        return validade;
    }
    public StringProperty codigoProperty() {
        return codigo;
    }
    public StringProperty valorProperty() {
        return valor;
    }

    public CupomControl() {
        TableColumn<Cupom, String> col1 = new TableColumn<>("Nome");
        col1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<Cupom, String> col3 = new TableColumn<>("Codigo");
        col3.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        TableColumn<Cupom, String> col4 = new TableColumn<>("Valor");
        col4.setCellValueFactory(new PropertyValueFactory<>("valor"));
        TableColumn<Cupom, String> col2 = new TableColumn<>("Validade");
        col2.setCellValueFactory((itemData)-> {
            LocalDate dt = itemData.getValue().getValidade();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new ReadOnlyStringWrapper(dt.format(formatter));           
        });

        table.getColumns().addAll(col1, col2,col3, col4);

        table.setItems(cupons);
    }

    public void adicionar() {
        System.out.println("Nome: " + nome.get());
        System.out.println("Validade: " + validade.get());
        System.out.println("Codigo: " + codigo.get());
        System.out.println("Valor: " + valor.get());
        Cupom c = new Cupom();
        c.setNome(nome.get());
        c.setValidade(validade.get());
        c.setCodigo(codigo.get());
        c.setValor(valor.get());
        cupons.add(c);
        dao.inserir(c);
    }

    public void pesquisar() {
        List<Cupom> lista = dao.consultar(nome.get());
        cupons.clear();
        cupons.addAll(lista);
    }

    public void limparCampos() {
    	this.nomeProperty().setValue("");
    	this.valorProperty().setValue("");
    	this.codigoProperty().setValue("");
    	this.valorProperty().setValue("");
    	this.dataProperty().setValue(null);
    }
    
    public TableView getTable() {
        return table;
    }

}