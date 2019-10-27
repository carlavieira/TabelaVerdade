package Objects;

import java.util.Arrays;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableViewSample extends Application {

    @Override
    public void start(Stage primaryStage) {
        String fraseLogica = "pvq∧(¬(pvr))";
        TabelaVerdade tv = new TabelaVerdade(fraseLogica);
        String[][] strings = tv.retornaMatrizLogica();
        String[][] strings2 = new String[strings[0].length][strings.length];
        for (int i = 0; i < strings2.length; i++) {
            for (int j = 0; j < strings2[0].length; j++) {
                strings2[i][j] = strings[j][i];
            }
        }

        StackPane root = new StackPane();
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(strings2));
        data.remove(0);//remove titles from data
        TableView<String[]> table = new TableView<>();
        for (int i = 0; i < strings2[0].length; i++) {
            TableColumn tc = new TableColumn(strings2[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            table.getColumns().add(tc);
        }
        table.setItems(data);
        root.getChildren().add(table);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
