package Objects;

public abstract class TruthTableItem {
    private static int arraySize = 17;
    private char[] truthTableItemArray;

    public TruthTableItem(char itemChar){
        this.truthTableItemArray = new char[arraySize];
        this.truthTableItemArray[0] = itemChar;
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
        finalString.append("\n");
        for(int i=0; i< arraySize; i++){
            finalString.append(" [" + truthTableItemArray[i] + "]");
        }
        finalString.append("\n");

        return finalString.toString();
    }
}
