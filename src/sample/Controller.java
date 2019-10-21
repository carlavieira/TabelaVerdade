package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
            display.setText(display.getText() + "p");
        } else if (event.getSource() == predicateQButton) {
            display.setText(display.getText() + "q");
        } else if (event.getSource() == predicateRButton) {
            display.setText(display.getText() + "r");
        } else if (event.getSource() == predicateSButton) {
            display.setText(display.getText() + "s");
        } else if (event.getSource() == negationButton) {
            display.setText(display.getText() + "¬");
        } else if (event.getSource() == andButton) {
            display.setText(display.getText() + "∧");
        } else if (event.getSource() == orButton) {
            display.setText(display.getText() + "v");
        } else if (event.getSource() == xorButton) {
            display.setText(display.getText() + ".");
        } else if (event.getSource() == implicationButton) {
            display.setText(display.getText() + "→");
        } else if (event.getSource() == biconditionalButton) {
            display.setText(display.getText() + "↔");
        } else if (event.getSource() == parenthesesLeftButton) {
            display.setText(display.getText() + "(");
        } else if (event.getSource() == parenthesesRightButton) {
            display.setText(display.getText() + ")");
        } else if (event.getSource() == deleteButton) {
            if (display.getText().length()>1){
                display.setText(display.getText().substring(0, (display.getText().length()-1)));
            } else {
                display.setText("");
            }
        } else if (event.getSource() == generateButton) {
            //conexão com back
            }
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}