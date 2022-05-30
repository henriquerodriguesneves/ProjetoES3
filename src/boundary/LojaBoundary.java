package boundary;

import control.LojaControl;
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

import java.time.format.DateTimeFormatter;

public class LojaBoundary extends Application {
    private TextField txtNome = new TextField();
    private TextField txtData = new TextField();
    private TextField txtEndereco = new TextField();
    private TextField txtTelefone = new TextField();
    private TextField txtCNPJ = new TextField();
    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnLimpar = new Button("Limpar");
    private Button btnSair = new Button(" Sair ");
    private LojaControl control = new LojaControl();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane principal = new BorderPane();
        GridPane grid = new GridPane();
        principal.setTop(grid);

        grid.add(new Label("Nome"), 0, 0);
        grid.add(txtNome, 1, 0);
        grid.add(new Label("Data Cadastro"), 0, 1);
        grid.add(txtData, 1, 1);
        grid.add(new Label("Endereço"), 0, 2);
        grid.add(txtEndereco, 1, 2);
        grid.add(new Label("Telefone"), 0, 3);
        grid.add(txtTelefone, 1, 3);
        grid.add(new Label("CNPJ"), 0, 4);
        grid.add(txtCNPJ, 1, 4);
        grid.add(btnAdicionar, 0, 5);
        grid.add(btnPesquisar, 1, 5);
        grid.add(btnLimpar, 2, 5);
        grid.add(btnSair, 3, 5);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateStringConverter ldc =
                new LocalDateStringConverter(formatter, null);

        Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
        Bindings.bindBidirectional(txtData.textProperty(), control.dataCadastroProperty(), ldc);
        Bindings.bindBidirectional(control.enderecoProperty(), txtEndereco.textProperty());
        Bindings.bindBidirectional(control.telefoneProperty(), txtTelefone.textProperty());
        Bindings.bindBidirectional(control.CNPJProperty(), txtCNPJ.textProperty());


        principal.setCenter(control.getTable());

        btnAdicionar.setOnAction( e -> control.adicionar());

        btnPesquisar.setOnAction(e -> control.pesquisar());

        btnSair.setOnAction(e -> System.exit(0));

        btnLimpar.setOnAction(e -> control.limparCampos());

        Scene scn = new Scene(principal, 600, 400);
        stage.setScene(scn);
        stage.setTitle("Lojas: ");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(LojaBoundary.class, args);
    }
}