package boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuPrincipal extends Application{
	
	private Button btnCliente = new Button("Clientes");
	private Button btnCupom = new Button("Cupons");
	private Button btnFornecedor = new Button("Fornecedores");
	private Button btnFuncionario = new Button("Funcionários");
	private Button btnLoja = new Button("Lojas");
	private Button btnProduto = new Button("Produtos");
	
	private ClienteBoundary cliente = new ClienteBoundary();
	private CupomBoundary cupom = new CupomBoundary();
	private FornecedorBoundary fornecedor = new FornecedorBoundary();
	private FuncionarioBoundary funcionario = new FuncionarioBoundary();
	private LojaBoundary loja = new LojaBoundary();
	private ProdutoBoundary produto = new ProdutoBoundary();
		
	private Label lblMenu = new Label("*** MENU PRINCIPAL ***");
	
	@Override
	public void start(Stage stage) throws Exception {
		GridPane principal = new GridPane();
		
		principal.add(lblMenu, 0, 0);
		principal.add(btnCliente, 0, 1);
		principal.add(btnCupom, 0, 2);
		principal.add(btnFornecedor, 0, 3);
		principal.add(btnFuncionario, 0, 4);
		principal.add(btnLoja, 0, 5);
		principal.add(btnProduto, 0, 6);
		
		btnCliente.setOnAction(e -> {
			try {
				cliente.start(stage);
			} catch (Exception e0) {
				 e0.printStackTrace();
			}
		});
		
		btnCupom.setOnAction(e -> {
			try {
				cupom.start(stage);
			} catch (Exception e1) {
				 e1.printStackTrace();
			}
		});
		
		btnFornecedor.setOnAction(e -> {
			try {
				fornecedor.start(stage);
			} catch (Exception e2) {
				 e2.printStackTrace();
			}
		});		
		
		 btnFuncionario.setOnAction(e -> {
			 try {
				 funcionario.start(stage);
			} catch (Exception e3) {
				 e3.printStackTrace();
			};
		 });
		 
		 btnLoja.setOnAction(e -> {
			 try {
				loja.start(stage);
			} catch (Exception e4) {
				// TODO: handle exception
			}
		 });
		 
		 btnProduto.setOnAction(e -> {
			 try {
				 produto.start(stage);
			} catch (Exception e5) {
				 e5.printStackTrace();
			}
		 });
		 
		 Scene scn = new Scene(principal, 600, 400);
		 stage.setScene(scn);
		 stage.setTitle("Menu principal");
		 stage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(MenuPrincipal.class, args);
	}

}
