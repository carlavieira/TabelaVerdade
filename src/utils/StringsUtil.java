package utils;

import java.util.ArrayList;

public class StringsUtil {
    private static final int arraySize = 17;

    private static char[] charToArray(char predicado, int propositionSize, int propositionPos){
        char[] propositionArray = new char[arraySize];
        propositionArray[0] = predicado;

        for(int i=1; i<propositionSize; i++){ //começando no 1 porque a pos 0 é a propria proposicao
            if(i<propositionSize/2){
                propositionArray[i] = 'V';
            }else{
                propositionArray[i] = 'F';
            }
        }

        return propositionArray;
    }

    public static ArrayList propositionsList(String equation){
        String noSpaces = equation.replaceAll("\\s+",""); // Retirando os espaços
        String lettersOnly = noSpaces.replaceAll("[^A-Za-z]+", ""); // Removendo tudo que nao é letra

        ArrayList propositionsInArray = new ArrayList(); //lista com todos as proposicoes em forma de array
        int stringSize = lettersOnly.length();
        int propositionSize = (int) Math.pow(2, stringSize); //tamanho real das proposicoes (nem sempre ocupam o vetor inteiro)

        for (int i=0; i<stringSize; i++){
            System.out.println(charToArray(lettersOnly.charAt(i), propositionSize, i));
            //propositionsInArray.add(charToArray(equation.charAt(i), propositionSize, i));
        }

        return propositionsInArray;
    }
}
