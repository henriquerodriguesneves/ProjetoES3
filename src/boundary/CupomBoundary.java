package boundary;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//import javafx.util.StringConverter;
//import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.LocalDateStringConverter;

//import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import control.CupomControl;

public class CupomBoundary extends Application {
    private TextField txtNome = new TextField();
    private TextField txtValidade = new TextField();
    private TextField txtCodigo = new TextField();
    private TextField txtValor = new TextField();
    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnLimpar = new Button("Limpar");
    private Button btnSair = new Button(" Sair ");
    private CupomControl control = new CupomControl();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane principal = new BorderPane();
        GridPane grid = new GridPane();
        principal.setTop(grid);

        grid.add(new Label("Nome"), 0, 0);
        grid.add(txtNome, 1, 0);
        grid.add(new Label("Validade"), 0, 1);
        grid.add(txtValidade, 1, 1);
        grid.add(new Label("Codigo"), 0, 2);
        grid.add(txtCodigo, 1, 2);
        grid.add(new Label("Valor"), 0, 3);
        grid.add(txtValor, 1, 3);
        grid.add(btnAdicionar, 0, 4);
        grid.add(btnPesquisar, 1, 4);
        grid.add(btnLimpar, 0, 5);
        grid.add(btnSair, 1, 5);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateStringConverter ldc =
                new LocalDateStringConverter(formatter, null);

        Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
        Bindings.bindBidirectional(
                txtValidade.textProperty(), control.dataProperty(), ldc);
        Bindings.bindBidirectional(control.codigoProperty(), txtCodigo.textProperty());
        Bindings.bindBidirectional(control.valorProperty(), txtValor.textProperty());


        principal.setCenter(control.getTable());

        btnAdicionar.setOnAction( e -> control.adicionar());

        btnPesquisar.setOnAction(e -> control.pesquisar());
        
        btnSair.setOnAction(e -> 
        System.exit(0)
        );
        
        btnLimpar.setOnAction(e -> control.limparCampos());
        

        Scene scn = new Scene(principal, 600, 400);
        stage.setScene(scn);
        stage.setTitle("Cupom");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(CupomBoundary.class, args);
    }
}