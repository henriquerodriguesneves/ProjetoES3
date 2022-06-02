package boundary;

import control.ProdutoControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ProdutoBoundary extends Application{
	
	private TextField txtCodigo = new TextField();
	private TextField txtDescricao = new TextField();
	private TextField txtFabricante = new TextField();
	private TextField txtTipo = new TextField();
	private TextField txtLote = new TextField();
	private Button btnAdd = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnSair = new Button("Sair");
	private Button btnLimpar = new Button("Limpar");
//	private Button btnVoltar = new Button("Voltar");
	private ProdutoControl control = new ProdutoControl();
//	private MenuPrincipal menu = new MenuPrincipal();
	
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane principal = new BorderPane();
		GridPane grid = new GridPane();
		principal.setCenter(grid);
		
		this.txtDescricao.setPrefWidth(250);
		grid.add(new Label("Código: "), 0, 0);
		grid.add(txtCodigo, 1, 0);
		grid.add(new Label("Descrição: "), 0, 1);
		grid.add(txtDescricao, 1, 1);
		grid.add(new Label("Fabricante: "), 0, 2);
		grid.add(txtFabricante, 1, 2);
		grid.add(new Label("Tipo: "), 0, 3);
		grid.add(txtTipo, 1, 3);
		grid.add(new Label("Lote: "), 0, 4);
		grid.add(txtLote, 1, 4);
		grid.add(btnAdd, 0, 5);
		grid.add(btnPesquisar, 1, 5);
		grid.add(btnLimpar, 2, 5);
		grid.add(btnSair, 3, 5);
//		grid.add(btnVoltar, 4, 5);
		
		
		Bindings.bindBidirectional(control.codigoProperty(), txtCodigo.textProperty());
		Bindings.bindBidirectional(control.descricaoProperty(), txtDescricao.textProperty());
		Bindings.bindBidirectional(control.fabricanteProperty(), txtFabricante.textProperty());
		Bindings.bindBidirectional(control.tipoProperty(), txtTipo.textProperty());
		Bindings.bindBidirectional(control.loteProperty(), txtLote.textProperty());
		
		principal.setBottom(control.getTable());
		
		btnAdd.setOnAction(e -> control.adcionar());
		btnPesquisar.setOnAction(e -> control.pesuisar());
		btnLimpar.setOnAction(e -> control.limparCampos());
		btnSair.setOnAction(e -> {
			System.exit(0);
		});
//		btnVoltar.setOnAction(e -> {
//			try {
//				menu.start(stage);
//				
//			} catch (Exception e2) {
//				 e2.printStackTrace();
//			}
//		});		
		
		Scene scn = new Scene(principal, 600, 400);
		stage.setScene(scn);
		stage.setTitle("Cadastro de produtos");
		stage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(ProdutoBoundary.class, args);
	}

}
