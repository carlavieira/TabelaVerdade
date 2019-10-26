package logicaDeConversao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TabelaVerdade {

	private String fraseLogica;
	private String[][] matrizLogicaString = null;
	private boolean[][] matrizLogica = null;
	private int tamanhoFraseLogica = 0;
	private int numeroDeLinhasUsadas = 0;

	public TabelaVerdade(String fraseLogica) {
		this.fraseLogica = fraseLogica;
		this.tamanhoFraseLogica = fraseLogica.length();
		this.matrizLogicaString = new String[fraseLogica.length()][17];
		this.matrizLogica = new boolean[tamanhoFraseLogica][17];
		this.rodaConversao();
	}

	private void rodaConversao() {
		pegaCaracterDeCadaColuna();
		completaTodosOsValoresVazios();
		completaValoresVaziosDosParenteses();
		pegaNumeroDeLinhasUsadas();
		pegaValoresSimplesDasPreposicoes();
	}

	private void pegaCaracterDeCadaColuna() {
		for (int i = 0; i < fraseLogica.length(); i++) {
			matrizLogicaString[i][0] = Character.toString(fraseLogica.charAt(i));
		}
	}

	private void completaTodosOsValoresVazios() {
		for (int i = 0; i < tamanhoFraseLogica; i++) {
			for (int j = 1; j < 17; j++) {
				matrizLogicaString[i][j] = "-";
			}
		}
	}

	private void completaValoresVaziosDosParenteses() {
		for (int i = 0; i < fraseLogica.length(); i++) {
			if (matrizLogicaString[i][0].matches("(|)")) {
				for (int j = 1; j < 17; j++) {
					matrizLogicaString[i][j] = "-";
				}
			}
		}
	}

	private void pegaNumeroDeLinhasUsadas() {
		Set<String> conjuntoPreposicoes = new HashSet<String>();
		for (int i = 0; i < tamanhoFraseLogica; i++) {
			if (matrizLogicaString[i][0].matches("p|q|r|s")) {
				conjuntoPreposicoes.add(matrizLogicaString[i][0]);
			}
		}
		this.numeroDeLinhasUsadas = (int) Math.pow(2, conjuntoPreposicoes.size());
		System.out.println("Numero de linhas usadas: " + numeroDeLinhasUsadas);
	}

	private void pegaValoresSimplesDasPreposicoes() {
		Set<String> conjuntoPreposicoes = new HashSet<String>();
		for (int i = 0; i < tamanhoFraseLogica; i++) {
			if (matrizLogicaString[i][0].matches("p|q|r|s")) {
				conjuntoPreposicoes.add(matrizLogicaString[i][0]);
			}
		}

		int casoDeVeF = 1;

		for (String proposicao : conjuntoPreposicoes) {

			String[] valores = pegaValoresDaProposicaoComCaso(casoDeVeF);

			for (int i = 0; i < tamanhoFraseLogica; i++) {
				if (matrizLogicaString[i][0].contentEquals(proposicao)) {
					for (int j = 0; j < numeroDeLinhasUsadas; j++) {
						matrizLogicaString[i][j + 1] = valores[j];
					}
				}
			}

			casoDeVeF++;

		}
	}

	private String[] pegaValoresDaProposicaoComCaso(int casoDeVeF) {
		List<String> retorno = new ArrayList<String>();

		for (int i = 0; i < numeroDeLinhasUsadas; i++) {
			switch (casoDeVeF) {
			case 1:
				if (i % 2 == 0) {
					retorno.add("V");
				} else {
					retorno.add("F");
				}
				break;
			case 2:
				if (i == 0 || i == 1 || i == 4 || i == 5 || i == 8 || i == 9 || i == 12
						|| i == 13) {
					retorno.add("V");
				} else {
					retorno.add("F");
				}
				break;
			case 3:
				if (i == 0 || i == 1 || i == 2 || i == 3 || i == 8 || i == 9 || i == 10
						|| i == 11) {
					retorno.add("V");
				} else {
					retorno.add("F");
				}
				break;
			case 4:
				if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6
						|| i == 7) {
					retorno.add("V");
				} else {
					retorno.add("F");
				}
				break;
			}
		}

		String[] retornoArray = new String[retorno.size()];

		for (int i = 0; i < retorno.size(); i++) {
			retornoArray[i] = retorno.get(i);
		}

		return retornoArray;
	}

	public String[][] retornaMatrizLogica() {

		return this.matrizLogicaString;
	}

}
