package utils;

import Objects.Proposition;
import Objects.TruthTableItem;

import java.util.ArrayList;

public class StringsUtil {
    //private static final int arraySize = 17;

    private static Proposition setPropositionDefaultValues(char propositionChar,int propositionSize, int propositionPos, String equation, int currentProposition){
        Proposition item = new Proposition(propositionChar);
        char[] propositionArray = item.getTruthTableItemArray();
        String propositions = getPropositions(equation);
        int amoutPropositions = propositions.length();
        //System.out.println(amoutPropositions);
        //System.out.println(currentProposition);

         int currentPropositions = amoutPropositions;
            if (currentProposition == 4) {
                for (int i = 1; i < propositionSize; i++) { //começando no 1 porque a pos 0 é a propria proposicao
                    if (i <= propositionSize / 2) {
                        propositionArray[i] = 'V';
                    } else {
                        propositionArray[i] = 'F';
                    }
                }
            } else if (currentProposition == 3) {
                int cont = 1;
                for (int i = 1; i < propositionSize; i++) {
                    if (cont <= 4 || cont > 8 && cont <= 12) {
                        propositionArray[i] = 'V';

                    } else if (cont > 4 && cont <= 8 || cont > 12 && cont <= 16) {
                        propositionArray[i] = 'F';
                    }
                    cont++;
                }
            } else if (currentProposition == 2) {
                int cont = 1;
                for (int i = 1; i < propositionSize; i+=2){
                    if (cont % 2 != 0) {
                        propositionArray[i] = 'V';
                        propositionArray[i+1] = 'V';
                    } else {
                        propositionArray[i] = 'F';
                        propositionArray[i+1] = 'F';
                    }
                    cont++;
                }
            }  else if (currentProposition == 1) {
                int cont = 1;
                for (int i = 1; i < propositionSize; i++){
                    if (cont % 2 != 0) {
                        propositionArray[i] = 'V';
                    } else {
                        propositionArray[i] = 'F';
                    }
                    cont++;
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
        int currentProposition = stringSize;
        for (int i = 0; i<stringSize; i++){
            propositionsInArray.add(setPropositionDefaultValues(propositions.charAt(i), propositionSize, i, equation, currentProposition));
            currentProposition--;

        }
        return propositionsInArray;
    }

    private static String removeSpaces(String equation){
        return equation.replaceAll("\\s+",""); // Retirando os espaços
    }

    private static String getPropositions(String equation) throws IllegalArgumentException{
        String noSpacesOnlyLetters = removeSpaces(equation).replaceAll("[^A-Za-z]+", ""); //Removendo tudo que nao é letra
        char[] inp = noSpacesOnlyLetters.toCharArray(); //Pegando cada caractere da string

        StringBuilder noSpacesOnlyLettersNoDuplicates = new StringBuilder();
        noSpacesOnlyLetters.chars().distinct().forEach(c -> noSpacesOnlyLettersNoDuplicates.append((char) c)); // Remove as duplicatas mas nao manda exception
                                                                                                               // pois a equacao pode repetir a mesma preposicao

        if(noSpacesOnlyLettersNoDuplicates.length()>4){
            throw new IllegalArgumentException("Maximum of 4 propositions allowed!");
        }
        return noSpacesOnlyLettersNoDuplicates.toString();
    }
}
