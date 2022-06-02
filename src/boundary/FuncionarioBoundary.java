package boundary;

import java.time.format.DateTimeFormatter;

import control.FuncionarioControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class FuncionarioBoundary extends Application{
	private TextField txtCpf = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtDataAdmissao = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEndereco = new TextField();
	private Button btnAdd = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnSair = new Button("Sair");
	private Button btnLimpar = new Button("Limpar");
	private FuncionarioControl control = new FuncionarioControl();
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane principal = new BorderPane();
		GridPane grid = new GridPane();
		principal.setCenter(grid);
		
		this.txtCpf.setPrefWidth(250);
		grid.add(new Label("CPF: "), 0, 0);
		grid.add(txtCpf, 1, 0);
		grid.add(new Label("Nome: "), 0, 1);
		grid.add(txtNome, 1, 1);
		grid.add(new Label("Data de admissão: "), 0, 2);
		grid.add(txtDataAdmissao, 1, 2);
		grid.add(new Label("Telefone: "), 0, 3);
		grid.add(txtTelefone, 1, 3);
		grid.add(new Label("Endereço: "), 0, 4);
		grid.add(txtEndereco, 1, 4);
		grid.add(btnAdd, 0, 5);
		grid.add(btnPesquisar, 1, 5);
		grid.add(btnLimpar, 2, 5);
		grid.add(btnSair, 3, 5);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateStringConverter ldc = new LocalDateStringConverter(formatter, null);
		
		Bindings.bindBidirectional(control.cpfProperty(), txtCpf.textProperty());
		Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
		Bindings.bindBidirectional(
				txtDataAdmissao.textProperty(), control.dataAdmissaoProperty(), ldc);
		Bindings.bindBidirectional(control.telefoneProperty(), txtTelefone.textProperty());
		Bindings.bindBidirectional(control.enderecoProperty(), txtEndereco.textProperty());
		
		principal.setBottom(control.getTable());
		
		btnAdd.setOnAction(e -> control.adicionar());
		btnLimpar.setOnAction( e -> control.limparCampos());
		btnPesquisar.setOnAction(e -> control.pesquisar());
		btnSair.setOnAction(e -> {
			System.exit(0);
		});
		
		Scene scn = new Scene(principal, 600, 400);
		stage.setScene(scn);
		stage.setTitle("Cadastro de funcionários");
		stage.show();
	}
	
//	public static void main(String[] args) {
//		Application.launch(FuncionarioBoundary.class, args);
//	}

}
