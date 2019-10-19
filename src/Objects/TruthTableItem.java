package Objects;

public abstract class TruthTableItem {
    private static int arraySize = 16;
    private char[] truthTableItemArray;
    private char character;

    public TruthTableItem(char itemChar) {
        this.setTruthTableItemArray(new char[arraySize]);
        this.setCharacter(itemChar);
    }

    public char[] getTruthTableItemArray() {
        return truthTableItemArray;
    }

    public void setTruthTableItemArray(char[] truthTableItemArray) {
        this.truthTableItemArray = truthTableItemArray;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    @Override
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        finalString.append("\n");
        finalString.append("Character: " + this.getCharacter() + ", Array: ");
        for (int i = 0; i < arraySize; i++) {
            finalString.append(" [" + this.getTruthTableItemArray()[i] + "]");
        }
        finalString.append("\n");

        return finalString.toString();
    }
}
