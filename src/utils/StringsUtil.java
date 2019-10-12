package utils;

import Objects.Proposition;
import Objects.TruthTableItem;

import java.util.ArrayList;

public class StringsUtil {
    //private static final int arraySize = 17;

    private static Proposition setPropositionDefaultValues(char propositionChar,int propositionSize, int propositionPos){
        Proposition item = new Proposition(propositionChar);
        char[] propositionArray = item.getTruthTableItemArray();
        for(int i=1; i<propositionSize; i++){ //começando no 1 porque a pos 0 é a propria proposicao
            if(i<=propositionSize/2){
                propositionArray[i] = 'V';
            }else{
                propositionArray[i] = 'F';
            }
        }
        item.setTruthTableItemArray(propositionArray);
        return item;
    }

    public static ArrayList propositionList(String equation){
        String propositions = getPropositions(equation);
        int stringSize = propositions.length();
        int propositionSize = (int) Math.pow(2, stringSize) + 1; //tamanho real das proposicoes (nem sempre ocupam o vetor inteiro)

        ArrayList propositionsInArray = new ArrayList();

        for (int i = 0; i<stringSize; i++){
            propositionsInArray.add(setPropositionDefaultValues(propositions.charAt(i), propositionSize, i));
        }
        return propositionsInArray;
    }

    private static String removeSpaces(String equation){
        return equation.replaceAll("\\s+",""); // Retirando os espaços
    }

    private static String getPropositions(String equation){
        return removeSpaces(equation).replaceAll("[^A-Za-z]+", ""); //Removendo tudo que nao é letra
    }
}
