package App;

import Objects.TabelaVerdade;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        String fraseLogica = "pvq∧(¬(pvr))";
        String fraseLogica2 = "¬(p∧q∧(r∧p∧(pvr)))";

        TabelaVerdade tv = new TabelaVerdade(fraseLogica);

        String[][] strings = tv.retornaMatrizLogica();
        System.out.println(strings.length);
        System.out.println(strings[0].length);

        String[][] strings2 = new String[strings[0].length][strings.length];

        for (int i = 0; i < strings2.length; i++) {
            for (int j = 0; j < strings2[0].length; j++) {
                strings2[i][j] = strings[j][i];
                System.out.print(strings2[i][j] + " ");
            }
            System.out.println(" ");
        }

        System.out.println(" ");
//        for (int i = 0; i < strings2.length; i++) {
//
//            strings2[1][i] = strings[1][i];
//            System.out.print(strings2[1][i] + " ");
//        }

//        System.out.println(" ");
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[0].length; j++) {
               System.out.print(strings[i][j]+" ");
            }
            System.out.println(" ");
        }



//        String[][] strings2 = new String[12][17];
//        for (int i = 0; i < strings.length; i++) {
//            for (int j = 0; j < strings[0].length; j++) {
//                strings2[j][i] = strings[i][j];
//            }
//        }
//        for (int i = 0; i < strings2.length; i++) {
//            for (int j = 0; j < strings2[0].length; j++) {
//                System.out.print(strings2[i][j] + " "+i+" "+" "+j+" ");
//            }
//            System.out.println(" ");
//        }
//        int SIZE = fraseLogica.length();
//        int length = SIZE;
//        int width = SIZE;
//
//        GridPane root = new GridPane();
//        int n = 0;
//        for(int y = 0; y < length; y++){
//            for(int x = 0; x < width; x++){
//
//                Random rand = new Random();
//                //int rand1 = rand.nextInt(2);
//
//                // Create a new TextField in each Iteration
//                TextField tf = new TextField();
//                tf.setPrefHeight(50);
//                tf.setPrefWidth(50);
//                tf.setAlignment(Pos.CENTER);
//                tf.setEditable(false);
//                tf.setText("(" + n + ")");
//
//                // Iterate the Index using the loops
//                GridPane.setRowIndex(tf,y);
//                GridPane.setColumnIndex(tf,x);
//                root.getChildren().add(tf);
//                n++;
//            }
//            n = 0;
//        }
//
//        Scene scene = new Scene(root, 500, 500);
//        primaryStage.setTitle("Random Binary Matrix (JavaFX)");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}