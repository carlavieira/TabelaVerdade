package calculator;

import Objects.TabelaVerdade;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private char[] propositions = new char[]{'p', 'q', 'r', 's'};
    private char[] connectors = new char[]{'¬', '∧', 'v', '*', '→', '↔'};

    @FXML
    private TextField display;


    @FXML
    private Button deleteButton;

    @FXML
    private Button generateButton;

    @FXML
    private Button predicatePButton;
    @FXML
    private Button predicateQButton;
    @FXML
    private Button predicateRButton;
    @FXML
    private Button predicateSButton;

    @FXML
    private Button negationButton;
    @FXML
    private Button andButton;
    @FXML
    private Button orButton;
    @FXML
    private Button xorButton;
    @FXML
    private Button implicationButton;
    @FXML
    private Button biconditionalButton;

    @FXML
    private Button parenthesesLeftButton;
    @FXML
    private Button parenthesesRightButton;


    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == predicatePButton) {
            if (permissionPropositions()) display.setText(display.getText() + "p");
        } else if (event.getSource() == predicateQButton) {
            if (permissionPropositions()) display.setText(display.getText() + "q");
        } else if (event.getSource() == predicateRButton) {
            if (permissionPropositions()) display.setText(display.getText() + "r");
        } else if (event.getSource() == predicateSButton) {
            if (permissionPropositions()) display.setText(display.getText() + "s");
        } else if (event.getSource() == negationButton) {
            if (pemissionNegation()) display.setText(display.getText() + "¬");
        } else if (event.getSource() == andButton) {
            if (permissionConnectors()) display.setText(display.getText() + "∧");
        } else if (event.getSource() == orButton) {
            if (permissionConnectors()) display.setText(display.getText() + "v");
        } else if (event.getSource() == xorButton) {
            if (permissionConnectors()) display.setText(display.getText() + "*");
        } else if (event.getSource() == implicationButton) {
            if (permissionConnectors()) display.setText(display.getText() + "→");
        } else if (event.getSource() == biconditionalButton) {
            if (permissionConnectors()) display.setText(display.getText() + "↔");
        } else if (event.getSource() == parenthesesLeftButton) {
            if (permissionParenthesesLeft()) display.setText(display.getText() + "(");
        } else if (event.getSource() == parenthesesRightButton) {
            if (permissionParenthesesRight()) display.setText(display.getText() + ")");
        } else if (event.getSource() == deleteButton) {
            if (display.getText().length() > 0)
                display.setText(display.getText().substring(0, (display.getText().length() - 1)));
        } else if (event.getSource() == generateButton) {
            char[] arrayChar = display.getText().toCharArray();
            StringBuilder fraseLogica = new StringBuilder();
            for (char element : arrayChar) {
                fraseLogica.append(element);
            }
            TabelaVerdade tv = new TabelaVerdade(fraseLogica.toString());
            String[][] matrizLogica = tv.retornaMatrizLogica();
            String[][] matrizExibicao = new String[matrizLogica[0].length][matrizLogica.length];
            for (int i = 0; i < matrizExibicao.length; i++) {
                for (int j = 0; j < matrizExibicao[0].length; j++) {
                    matrizExibicao[i][j] = matrizLogica[j][i];
                }
            }


            StackPane root = new StackPane();
            ObservableList<String[]> data = FXCollections.observableArrayList();
            data.addAll(Arrays.asList(matrizExibicao));
            data.remove(0);//remove titles from data
            TableView<String[]> table = new TableView<>();
            for (int i = 0; i < matrizExibicao[0].length; i++) {
                TableColumn tc = new TableColumn(matrizExibicao[0][i]);
                final int colNo = i;
                tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                        return new SimpleStringProperty((p.getValue()[colNo]));
                    }
                });
                tc.setPrefWidth(60);
                tc.setStyle( "-fx-alignment: CENTER; -fx-text-fill: gray;");
                if (tv.pegaIndiceColunaFinal()==i) {
                    tc.setStyle("-fx-font-weight: bold;  -fx-alignment: CENTER;");
                }
                table.getColumns().add(tc);
            }
            table.setItems(data);
            root.getChildren().add(table);
            Stage stage = new Stage();
            stage.setTitle("Tabela Verdade");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }
    }


    boolean permissionPropositions() {
        char[] arrayChar = display.getText().toCharArray();
        if (arrayChar.length > 0)
            return (new String(connectors).indexOf(arrayChar[arrayChar.length - 1]) != -1 || arrayChar[arrayChar.length - 1] == '(');
        else return true;
    }

    boolean pemissionNegation() {
        char[] arrayChar = display.getText().toCharArray();
        if (arrayChar.length > 0) return (arrayChar[arrayChar.length - 1] == '(');
        else return true;
    }

    boolean permissionConnectors() {
        int numConnectors = 0;
        char[] arrayChar = display.getText().toCharArray();
        if (arrayChar.length > 0) {
            for (char element : arrayChar) {
                if (new String(connectors).indexOf(element) != -1) numConnectors++;
            }
            if (numConnectors < 6) {
                return (new String(propositions).indexOf(arrayChar[arrayChar.length - 1]) != -1 || arrayChar[arrayChar.length - 1] == ')');
            }
        }
        return false;
    }

    boolean permissionParenthesesLeft() {
        char[] arrayChar = display.getText().toCharArray();
        if (arrayChar.length > 0)
            return (new String(connectors).indexOf(arrayChar[arrayChar.length - 1]) != -1 || arrayChar[arrayChar.length - 1] == '(');
        else return true;
    }

    boolean permissionParenthesesRight() {
        int numParenthesesOpen = 0;
        char[] arrayChar = display.getText().toCharArray();
        if (arrayChar.length > 0) {
            for (char element : arrayChar) {
                if (element == '(') numParenthesesOpen++;
                if (element == ')') numParenthesesOpen--;
            }
            if (numParenthesesOpen > 0) {
                return (new String(propositions).indexOf(arrayChar[arrayChar.length - 1]) != -1 || arrayChar[arrayChar.length - 1] == ')');
            }
        }
        return false;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}