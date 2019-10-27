package calculator;

import Objects.TabelaVerdade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {


    private char[] propositions = new char[]{'p', 'q', 'r', 's'};
    private char[] connectors = new char[]{'¬', '∧', 'v', '*', '→', '↔'};

    @FXML
    private TextField display;

    @FXML
    private TableView outputTable;

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
            outputTable = new TableView<>();
            char[] arrayChar = display.getText().toCharArray();
            StringBuilder fraseLogica = new StringBuilder();

            for (char element : arrayChar) {
                fraseLogica.append(element);
            }

            TabelaVerdade tv = new TabelaVerdade(fraseLogica.toString());
            String[][] matrizLogica = tv.retornaMatrizLogica();
//            System.out.println();
//            for (int i = 0;i< matrizLogica.length; i++)
//                System.out.println(matrizLogica[i][0]);
//            for (int i = 0;i< matrizLogica.length; i++)
//                System.out.println(matrizLogica[0][i]);


            for (int i = 0; i < fraseLogica.length(); i++) {
                for (int j = 0; j < 17; j++) {
                    System.out.print(matrizLogica[i][j] + " ");
                }
                System.out.println("");
            }
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