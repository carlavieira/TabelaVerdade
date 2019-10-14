package test;

public class Sentence {
    private String sentence;
    private TruthTableItem[] truthTableSentenceArray;
    private static int sentenceOrder;


    public Sentence(String sentence) {
        this.sentence = sentence;
        this.sentenceOrder = 1;
        this.truthTableSentenceArray = generateTruthTableSentenceArray();
    }

    public TruthTableItem[] generateTruthTableSentenceArray() {
        TruthTableItem[] truthTableSentenceArray = new TruthTableItem[sentence.length()];
        for (int i = 0; i<sentence.length(); i++){
            switch (sentence.charAt(i)){
                case "p": case "q": case "r": case "t":
                    truthTableSentenceArray[i]=generateTruthTableSentenceArrayProp(sentence.charAt(i));
                    break;
                case "∧": case "∨": case "⊕": case "|": case ">":

                    break;
                case "~":
                    switch (sentence.charAt(i+1)){
                        case "p": case "q": case "r": case "t":
                            truthTableSentenceArray[i]=generateTruthTableSentenceArrayNegation(sentence.charAt(i), this.truthTableSentenceArray[i+1]);
                        case "(":
                            Sentence posteriorSentence = findPosteriorSentence(i);
                            truthTableSentenceArray[i]=generateTruthTableSentenceArrayNegation(posteriorSentence);

                    }

                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + sentence.charAt(i));
            }

        }

        return null;
    }

    public TruthTableItem[] generateItemArray(char item, Sentence previous, Sentence posterior) {
        return null;
    }


    public TruthTableItem generateTruthTableSentenceArrayProp(char item){
        TruthTableItem vectorItem = new TruthTableItem(item, this.sentenceOrder);
        this.sentenceOrder++;
        switch (item){
            case "p":
                vectorItem.setTruthTableItemArray(["V", "V"]);
                return vectorItem;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item);
        }
        return null;
    }

    public Sentence findPosteriorSentence(int i) {
        int parentheses = 1;
        int j = i + 1;
        while (parentheses != 0 || j <= this.sentence.length()) {
            j++;
            switch (this.sentence.charAt(j)) {
                case '(':
                    parentheses++;
                    break;
                case ')':
                    parentheses--;
                    break;
                default:
                    break;
            }
        }
        return new Sentence(this.sentence.substring(i + 2, j - 1));
    }

    public TruthTableItem generateTruthTableSentenceArrayNegation(char item,TruthTableItem tableitemPosterior){
        TruthTableItem vectorItem = new TruthTableItem(item, this.sentenceOrder);
        this.sentenceOrder++;
        vectorItem.setTruthTableItemArray(tableitemPosterior.deny());
        return vectorItem;
    }

    public TruthTableItem generateTruthTableSentenceArrayNegation(char item,Sentence sentence){
        TruthTableItem vectorItem = new TruthTableItem(item, this.sentenceOrder);
        this.sentenceOrder++;
        vectorItem.setTruthTableItemArray(tableitemPosterior.deny());
        return vectorItem;
    }

}
