package Objects;

public abstract class TruthTableItem {
    private static final int arraySize = 17;
    private char[] truthTableItemArray;

    public TruthTableItem(char itemChar, int itemSize){
        truthTableItemArray = new char[arraySize];
        this.truthTableItemArray[0] = itemChar;
    }
}
