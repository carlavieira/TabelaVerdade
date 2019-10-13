package utils;

import Objects.Conectives;
import Objects.Proposition;

import java.util.ArrayList;

public class StringsUtil {

    private static Proposition setPropositionDefaultValues(char propositionChar,int propositionSize, String equation, int currentProposition){
        Proposition item = new Proposition(propositionChar);
        char[] propositionArray = item.getTruthTableItemArray();
        String propositions = getPropositions(equation);
        int amountPropositions = propositions.length();
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

    private static int getRealSize(String propositions){ //Obs: pode ser usado pra calcular o tamanho dos conectivos
        return ((int) Math.pow(2, propositions.length()) + 1); //tamanho real das proposicoes (nem sempre ocupam o vetor inteiro).
    }

    public static ArrayList conectivesList(String equation){ //Devolve a lista dos conectivos vazios (somente o indice 0 preenchido com seu caractere)
        String conectives = getConectives(equation);
        int conectiveSize = getRealSize(getPropositions(equation));
        int stringSize = conectives.length();
        ArrayList conectivesInArray = new ArrayList();
        for (int i = 0; i<stringSize; i++){
            conectivesInArray.add(new Conectives(conectives.charAt(i)));
        }
        return conectivesInArray;
    }

    public static ArrayList propositionList(String equation){ //Devolve a lista de propositivos preenchidos com os valores Default
        String propositions = getPropositions(equation);
        int propositionSize = getRealSize(propositions);
        int stringSize = propositions.length();
        ArrayList propositionsInArray = new ArrayList();

        int currentProposition = stringSize;
        for (int i = 0; i<stringSize; i++){
            propositionsInArray.add(setPropositionDefaultValues(propositions.charAt(i), propositionSize, equation, currentProposition));
            currentProposition--;
        }
        return propositionsInArray;
    }

    private static String removeSpaces(String equation){
        return equation.replaceAll("\\s+",""); // Retirando os espaços
    }

    private static String getConectives(String equation) throws IllegalArgumentException{
        String noSpacesOnlySymbols = removeSpaces(equation).replaceAll("[A-Za-z]", ""); //Removendo tudo que nao é simbolo

        if(noSpacesOnlySymbols.length()>6){
            throw new IllegalArgumentException("Maximum of 6 conectives allowed!");
        }
        return noSpacesOnlySymbols;
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
