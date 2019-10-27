package calculator;

import javafx.beans.property.SimpleStringProperty;

public class TableModel {

    private SimpleStringProperty column0;
    private SimpleStringProperty column1;
    private SimpleStringProperty column2;
    private SimpleStringProperty column3;
    private SimpleStringProperty column4;
    private SimpleStringProperty column5;
    private SimpleStringProperty column6;
    private SimpleStringProperty column7;
    private SimpleStringProperty column8;
    private SimpleStringProperty column9;
    private SimpleStringProperty column10;
    private SimpleStringProperty column11;
    private SimpleStringProperty column12;
    private SimpleStringProperty column13;
    private SimpleStringProperty column14;



    public TableModel(String c0, String c1, String c2, String c3, String c4, String c5, String c6, String c7) {
        this.column0 = new SimpleStringProperty(c0);
        this.column1 = new SimpleStringProperty(c1);
        this.column2 = new SimpleStringProperty(c2);
        this.column3 = new SimpleStringProperty(c3);
        this.column4 = new SimpleStringProperty(c4);
        this.column5 = new SimpleStringProperty(c5);
        this.column6 = new SimpleStringProperty(c6);

    }

    public String getColumn0() {
        return column0.get();
    }


    public void setColumn0(String column0) {
        this.column0 = new SimpleStringProperty(column0);
    }


    public String getColumn1() {
        return column1.get();
    }


    public void setColumn1(String column1) {
        this.column1 = new SimpleStringProperty(column1);
    }

    public String getColumn2() {
        return column2.get();
    }

    public void setColumn2(String column2) {
        this.column2 = new SimpleStringProperty(column2);
    }

    public String getColumn3() {
        return column3.get();
    }

    public void setColumn3(String column3) {
        this.column3 = new SimpleStringProperty(column3);
    }

}