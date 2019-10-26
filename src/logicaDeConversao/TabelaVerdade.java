package logicaDeConversao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TabelaVerdade {

	private String fraseLogica;
	private String[][] matrizLogicaString = null;
	private int tamanhoFraseLogica = 0;
	private int numeroDeLinhasUsadas = 0;

	public TabelaVerdade(String fraseLogica) {
		this.fraseLogica = fraseLogica;
		this.tamanhoFraseLogica = fraseLogica.length();
		this.matrizLogicaString = new String[fraseLogica.length()][17];
		this.rodaConversao();
	}

	private void rodaConversao() {
		pegaCaracterDeCadaColuna();
		completaTodosOsValoresVazios();
		completaValoresVaziosDosParenteses();
		pegaNumeroDeLinhasUsadas();
		pegaValoresSimplesDasPreposicoes();
		resolveDentroDosParenteses();
		resolve(0, tamanhoFraseLogica - 1);
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

	private void resolveDentroDosParenteses() {
		List<Integer> indiceParentesesJaTratados = new ArrayList<Integer>();
		int numeroDeParentesesTotal = pegaNumeroDeParentesesTotal();

		if (numeroDeParentesesTotal == 0) {
			return;
		} else {
			while (indiceParentesesJaTratados.size() < numeroDeParentesesTotal) {
				int[] parentesesTratados = pegaIndicesParentesesPraTratar(
						indiceParentesesJaTratados);
				resolve(parentesesTratados[0], parentesesTratados[1]);
				indiceParentesesJaTratados.add(new Integer(parentesesTratados[0]));
				indiceParentesesJaTratados.add(new Integer(parentesesTratados[1]));
			}
		}
	}

	private int pegaNumeroDeParentesesTotal() {
		int numeroDeParenteses = 0;

		for (int i = 0; i < tamanhoFraseLogica; i++) {

			if (this.matrizLogicaString[i][0].matches("\\(|\\)")) {
				numeroDeParenteses++;
			}
		}

		return numeroDeParenteses;
	}

	private int[] pegaIndicesParentesesPraTratar(List<Integer> indiceParentesesJaTratados) {

		int indiceInicio = 0;
		int indiceFinal = 0;

		for (int i = 0; i < tamanhoFraseLogica; i++) {
			if (matrizLogicaString[i][0].matches("\\(")
					&& !(indiceParentesesJaTratados.contains(new Integer(i)))) {
				indiceInicio = i;
			}
			if (matrizLogicaString[i][0].matches("\\)")
					&& !(indiceParentesesJaTratados.contains(new Integer(i)))) {
				indiceFinal = i;
			}
		}

		int[] retorno = new int[2];
		retorno[0] = indiceInicio;
		retorno[1] = indiceFinal;

		return retorno;
	}

	// TODO
	private void resolve(int indiceInicio, int indiceFinal) {
		resolveNegacoes(indiceInicio, indiceFinal);
		resolveAnd(indiceInicio, indiceFinal);
		/*
		 * resolveOr(indiceInicio, indiceFinal); NAO QUEBRA LINHA
		 * resolveXor(indiceInicio, indiceFinal); NAO QUEBRA LINHA
		 * resolveImplicacao(indiceInicio, indiceFinal); NAO QUEBRA LINHA
		 * resolveBicondicional(indiceInicio, indiceFinal); NAO QUEBRA LINHA
		 * 
		 */
	}

	private void resolveNegacoes(int indiceInicio, int indiceFinal) {
		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("¬")) {
				if (matrizLogicaString[i + 1][0].matches("\\(")) {
					int indiceValorParenteses = pegaIndiceValorParenteses(i + 1);
					nega(indiceValorParenteses, i);
				} else {
					nega(i + 1, i);
				}
			}
		}
	}

	private int pegaIndiceValorParenteses(int indiceParentesesInicio) {

		int parentesesFinalCorrespondente = pegaParentesesFinalCorrespondente(
				indiceParentesesInicio);

		int elementoDeMaiorPrioridade = pegaIndiceDeMaiorPrioridade(indiceParentesesInicio,
				parentesesFinalCorrespondente);

		return elementoDeMaiorPrioridade;
	}

	private int pegaParentesesFinalCorrespondente(int indiceParentesesInicio) {
		int parentesesAMais = 0;
		int retorno = 0;

		for (int i = indiceParentesesInicio; i < tamanhoFraseLogica; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				parentesesAMais++;
			} else if (matrizLogicaString[i][0].matches("\\)")) {
				if (parentesesAMais == 0) {
					retorno = i;
				} else {
					parentesesAMais--;
				}
			}
		}

		return retorno;
	}

	private int pegaIndiceDeMaiorPrioridade(int indiceInicio, int indiceFinal) {

		int indiceRetorno = indiceInicio;

		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("p|q|r|s")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("¬")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("∧")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("v")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("*")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("→")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("↔")) {
				indiceRetorno = i;
			}
		}

		return indiceRetorno;
	}

	private void nega(int indiceValor, int indiceDestino) {
		for (int i = 1; i < 17; i++) {
			if (matrizLogicaString[indiceValor][i].matches("V")) {
				matrizLogicaString[indiceDestino][i] = "F";
			} else if (matrizLogicaString[indiceValor][i].matches("F")) {
				matrizLogicaString[indiceDestino][i] = "V";
			}
		}
	}

	private void resolveAnd(int indiceInicio, int indiceFinal) {
		// TODO
	}

	/*
	 * private boolean[][] pegaMatrizLogicaBool() { boolean[][] retorno = new
	 * boolean[tamanhoFraseLogica][17];
	 * 
	 * for (int i = 0; i < tamanhoFraseLogica; i++) { for (int j = 1; j < 17; j++) {
	 * if (matrizLogicaString[i][j].matches("V")) { retorno[i][j] = true; } else if
	 * (matrizLogicaString[i][j].matches("F")) { retorno[i][j] = false; } } }
	 * 
	 * return retorno; }
	 */

	public String[][] retornaMatrizLogica() {

		return this.matrizLogicaString;
	}

}
