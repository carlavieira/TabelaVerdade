package test;

public class TruthTableItem {
    private static int arraySize = 16;
    private char itemChar;
    private int order;
    private char[] truthTableItemArray;

    public TruthTableItem(char itemChar, int order){
        this.truthTableItemArray = new char[arraySize];
        this.itemChar = itemChar;
        this.order = order;
    }

    public char[] getTruthTableItemArray() {
        return truthTableItemArray;
    }

    public void setTruthTableItemArray(char[] truthTableItemArray) {
        this.truthTableItemArray = truthTableItemArray;
    }

    public char getItemChar() {
        return itemChar;
    }

    @Override
    public String toString(){
        StringBuilder finalString = new StringBuilder();
        finalString.append("\n");
        for(int i=0; i< arraySize; i++){
            finalString.append(" [" + truthTableItemArray[i] + "]");
        }
        finalString.append("\n");

        return "["+this.itemChar+"] ["+this.order+"] "+finalString.toString();
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public char[] deny(){
        char[] denied = new char[arraySize];
        for (int k = 0; k<arraySize; k++){
            switch (this.truthTableItemArray[k]){
                case 'V':
                    truthTableItemArray[k]='F';
                    break;
                 case 'F':
                    truthTableItemArray[k]='V';
                    break;
            }
        }
        return denied;

    }
}
