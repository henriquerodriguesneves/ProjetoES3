package control;

import java.util.List;

import entity.ProdutoEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import persistence.ProdutoDAO;
import persistence.ProdutoDAOImpl;

public class ProdutoControl {
	
	private ObservableList<ProdutoEntity> produtos = FXCollections.observableArrayList();
	
	private StringProperty codigo = new SimpleStringProperty();
	private StringProperty descricao = new SimpleStringProperty("");
	private StringProperty fabricante = new SimpleStringProperty("");
	private StringProperty tipo = new SimpleStringProperty("");
	private StringProperty lote = new SimpleStringProperty();
	
	private ProdutoDAO dao = new ProdutoDAOImpl();
	
	private TableView<ProdutoEntity> table = new TableView<>();
	
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
	
	public ProdutoControl() {
		TableColumn<ProdutoEntity, String> col1 = new TableColumn<>("Codigo");
		col1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		TableColumn<ProdutoEntity, String> col2 = new TableColumn<>("Descricao");
		col2.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		TableColumn<ProdutoEntity, String> col3 = new TableColumn<>("Fabricante");
		col3.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
		TableColumn<ProdutoEntity, String> col4 = new TableColumn<>("Tipo");
		col4.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		TableColumn<ProdutoEntity, String> col5 = new TableColumn<>("Lote");
		col5.setCellValueFactory(new PropertyValueFactory<>("lote"));
		
		table.getColumns().addAll(col1, col2, col3, col4, col5);
		table.setItems(produtos);
	}
	
	public void adcionar() {
		ProdutoEntity p = new ProdutoEntity();
		p.setCodigo(codigo.get());
		p.setDescricao(descricao.get());
		p.setFabricante(fabricante.get());
		p.setTipo(tipo.get());
		p.setLote(lote.get());
		produtos.add(p);
		dao.inserir(p);
	}
	
	public void pesuisar() {
		List<ProdutoEntity> lista = dao.consultar(descricao.get());
		produtos.clear();
		produtos.addAll(lista);
		
	}
	
	public void limparCampos() {
		this.codigoProperty().setValue("");
		this.descricaoProperty().setValue("");
		this.fabricanteProperty().setValue("");
		this.tipoProperty().setValue("");
		this.loteProperty().setValue("");
	}
	public TableView getTable() {
		return table;
	}
}
