package Objects;

public abstract class TruthTableItem {
    private static final int arraySize = 17;
    private char[] truthTableItemArray;

    public TruthTableItem(char itemChar){
        this.truthTableItemArray = new char[arraySize];
        this.truthTableItemArray[0] = itemChar;
    }

    public static int getArraySize() {
        return arraySize;
    }

    public char[] getTruthTableItemArray() {
        return truthTableItemArray;
    }

    public void setTruthTableItemArray(char[] truthTableItemArray) {
        this.truthTableItemArray = truthTableItemArray;
    }

    @Override
    public String toString(){
        StringBuilder finalString = new StringBuilder();
        for(int i=0; i< arraySize; i++){
            finalString.append(" [" + truthTableItemArray[i] + "]");
        }
        return finalString.toString();
    }
}
