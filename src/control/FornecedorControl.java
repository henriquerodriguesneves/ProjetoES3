package control;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.SessionFactory;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.FornecedorModel;
import persistence.FornecedorDao;
import util.HibernateUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FornecedorControl implements OperacoesController<FornecedorModel> {

    private ObservableList<FornecedorModel> fornecedor = FXCollections.observableArrayList();

    private StringProperty nome = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> prazo = new SimpleObjectProperty<>();
    private StringProperty produto = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private StringProperty CNPJ = new SimpleStringProperty("");

    // private FornecedoresDAO dao = new FornecedoresDAOimpl();

    private TableView<FornecedorModel> table = new TableView<>();

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

    @SuppressWarnings("unchecked")
    public FornecedorControl() {
        TableColumn<FornecedorModel, String> col1 = new TableColumn<>("Nome");
        col1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<FornecedorModel, String> col3 = new TableColumn<>("Produto");
        col3.setCellValueFactory(new PropertyValueFactory<>("produto"));
        TableColumn<FornecedorModel, String> col4 = new TableColumn<>("Telefone");
        col4.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        TableColumn<FornecedorModel, String> col5 = new TableColumn<>("CNPJ");
        col5.setCellValueFactory(new PropertyValueFactory<>("CNPJ"));
        TableColumn<FornecedorModel, String> col2 = new TableColumn<>("Prazo");
        col2.setCellValueFactory((itemData) -> {
            LocalDate dt = itemData.getValue().getPrazo();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new ReadOnlyStringWrapper(dt.format(formatter));
        });

        table.getColumns().addAll(col1, col2, col3, col4, col5);

        table.setItems(fornecedor);
    }

    public void adicionar() {
        // System.out.println("Nome: " + nome.get());
        // System.out.println("Prazo: " + prazo.get());
        // System.out.println("Endereco: " + produto.get());
        // System.out.println("Telefone: " + telefone.get());
        // System.out.println("CNPJ: " + CNPJ.get());
        // Fornecedor e = new Fornecedor();
        // e.setNome(nome.get());
        // e.setPrazo(prazo.get());
        // e.setProduto(produto.get());
        // e.setTelefone(telefone.get());
        // e.setCNPJ(CNPJ.get());
        // fornecedor.add(e);
        // dao.inserir(e);
    }

    public void pesquisar() {
        // List<FornecedorModel> lista = dao.consultar(nome.get());
        // fornecedor.clear();
        // fornecedor.addAll(lista);
    }

    public void limparCampos() {
        this.nomeProperty().setValue("");
        this.prazoProperty().setValue(null);
        this.produtoProperty().setValue("");
        this.CNPJProperty().setValue("");
        this.telefoneProperty().setValue("");
    }

    @SuppressWarnings("rawtypes")
    public TableView getTable() {
        return table;
    }

    @Override
    public void salvar(FornecedorModel t) throws SQLException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        FornecedorDao foDao = new FornecedorDao(sessionFactory);
        foDao.insert(t);

    }

    @Override
    public void modificar(FornecedorModel t) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void remover(FornecedorModel t) throws SQLException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        FornecedorDao foDao = new FornecedorDao(sessionFactory);
        foDao.delete(t);

    }

    @Override
    public FornecedorModel consultar(FornecedorModel t) throws SQLException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        FornecedorDao foDao = new FornecedorDao(sessionFactory);
        t = foDao.selectOne(t);
        return t;
    }

    @Override
    public List<FornecedorModel> listar() throws SQLException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        FornecedorDao foDao = new FornecedorDao(sessionFactory);
        List<FornecedorModel> fornecedor = foDao.selectAll();
        return fornecedor;
    }
}