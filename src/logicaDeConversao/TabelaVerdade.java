package logicaDeConversao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TabelaVerdade {

	private String fraseLogica;
	private String[][] matrizLogicaString = null;
	private int tamanhoFraseLogica = 0;
	private int numeroDeLinhasUsadas = 0;
	private Map<String, Integer> dicionarioDePrioridadesDeConectivos = new HashMap<String, Integer>();

	public TabelaVerdade(String fraseLogica) {
		this.fraseLogica = fraseLogica;
		this.tamanhoFraseLogica = fraseLogica.length();
		this.matrizLogicaString = new String[fraseLogica.length()][17];
		this.completaDicionarioDePrioridades();
		this.rodaConversao();
	}

	private void completaDicionarioDePrioridades() {
		dicionarioDePrioridadesDeConectivos.put("¬", 0);
		dicionarioDePrioridadesDeConectivos.put("∧", 1);
		dicionarioDePrioridadesDeConectivos.put("v", 2);
		dicionarioDePrioridadesDeConectivos.put("\\*", 3);
		dicionarioDePrioridadesDeConectivos.put("→", 4);
		dicionarioDePrioridadesDeConectivos.put("↔", 5);
	}

	private void rodaConversao() {
		pegaCaracterDeCadaColuna();
		completaTodosOsValoresVazios();
		pegaNumeroDeLinhasUsadas();
		pegaValoresSimplesDasPreposicoes(); // Até aqui tá TOP
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
		boolean achouIndiceFinal = false;

		for (int i = 0; i < tamanhoFraseLogica; i++) {
			if (matrizLogicaString[i][0].matches("\\(")
					&& !(indiceParentesesJaTratados.contains(new Integer(i)))) {
				indiceInicio = i;
			}
			if (matrizLogicaString[i][0].matches("\\)")
					&& !(indiceParentesesJaTratados.contains(new Integer(i)))) {
				if (!achouIndiceFinal) {
					indiceFinal = i;
					achouIndiceFinal = true;
				}
			}
		}

		int[] retorno = new int[2];
		retorno[0] = indiceInicio;
		retorno[1] = indiceFinal;

		return retorno;
	}

	private void resolve(int indiceInicio, int indiceFinal) {
		resolveNegacoes(indiceInicio, indiceFinal);
		resolveAnd(indiceInicio, indiceFinal);
		resolveOr(indiceInicio, indiceFinal);
		resolveXor(indiceInicio, indiceFinal);
		resolveImplicacao(indiceInicio, indiceFinal);
		resolveBicondicional(indiceInicio, indiceFinal);
	}

	private void resolveNegacoes(int indiceInicio, int indiceFinal) {
		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("¬")) {
				if (matrizLogicaString[i + 1][0].matches("\\(")) {
					int indiceValorInternoParenteses = pegaIndiceValorParentesesDireita(i + 1);
					nega(indiceValorInternoParenteses, i);
				} else {
					nega(i + 1, i);
				}
			}
		}
	}

	private int pegaIndiceValorParentesesDireita(int indiceParentesesInicio) {

		int parentesesFinalCorrespondente = pegaParentesesFinalCorrespondente(
				indiceParentesesInicio);

		int elementoDeMaiorPrioridade = pegaIndiceDeMaiorPrioridade(indiceParentesesInicio,
				parentesesFinalCorrespondente);

		return elementoDeMaiorPrioridade;
	}

	private int pegaIndiceValorParentesesEsquerda(int indiceParentesesFinal) {
		int parentesesInicialCorrespondente = pegaParentesesInicialCorrespondente(
				indiceParentesesFinal);

		int elementoDeMaiorPriordad = pegaIndiceDeMaiorPrioridade(
				parentesesInicialCorrespondente, indiceParentesesFinal);

		return elementoDeMaiorPriordad;
	}

	private int pegaParentesesFinalCorrespondente(int indiceParentesesInicio) {
		int parentesesAMais = 0;
		int retorno = 0;

		for (int i = indiceParentesesInicio + 1; i < tamanhoFraseLogica; i++) {
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

	private int pegaParentesesInicialCorrespondente(int indiceParentesesFinal) {
		int parentesesAMais = 0;
		int retorno = 0;

		for (int i = indiceParentesesFinal - 1; i > 0; i--) {
			if (matrizLogicaString[i][0].matches("\\)")) {
				parentesesAMais++;
			} else if (matrizLogicaString[i][0].matches("\\(")) {
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

		int indiceInicioReal = indiceInicio, indiceFinalReal = indiceFinal;

		if (matrizLogicaString[indiceInicio][0].matches("\\(")) {
			indiceInicioReal = indiceInicio + 1;
		}

		if (matrizLogicaString[indiceFinal][0].matches("\\)")) {
			indiceFinalReal = indiceFinal - 1;
		}

		int indiceRetorno = indiceInicioReal;

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("p|q|r|s")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("¬")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("∧")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("v")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("\\*")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("→")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("↔")) {
				indiceRetorno = i;
			}
		}

		return indiceRetorno;
	}

	private void nega(int indiceValor, int indiceDestino) {
		for (int i = 1; i < numeroDeLinhasUsadas + 1; i++) {
			if (matrizLogicaString[indiceValor][i].matches("V")) {
				matrizLogicaString[indiceDestino][i] = "F";
			} else if (matrizLogicaString[indiceValor][i].matches("F")) {
				matrizLogicaString[indiceDestino][i] = "V";
			}
		}
	}

	private void resolveAnd(int indiceInicio, int indiceFinal) {
		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("∧")) {
				int indiceValorDireita = 0, indiceValorEsquerda = 0;

				if (matrizLogicaString[i + 1][0].matches("\\(")) {
					indiceValorDireita = pegaIndiceValorParentesesDireita(i + 1);

				} else {
					indiceValorDireita = i + 1;
				}

				int[] conectivoAnterior = temConectivoAntes(i, "∧");

				if (conectivoAnterior[0] != -1) {
					indiceValorEsquerda = conectivoAnterior[1];
				} else if (matrizLogicaString[i - 1][0].matches("\\)")) {
					indiceValorEsquerda = pegaIndiceValorParentesesEsquerda(i - 1);
				} else {
					indiceValorEsquerda = i - 1;
				}

				and(indiceValorEsquerda, indiceValorDireita, i);

			}
		}
	}

	private void and(int indiceValorEsquerda, int indiceValorDireita, int indiceDestino) {
		for (int i = 1; i < numeroDeLinhasUsadas + 1; i++) {
			if (matrizLogicaString[indiceValorEsquerda][i].matches("V")) {
				if (matrizLogicaString[indiceValorDireita][i].matches("V")) {
					matrizLogicaString[indiceDestino][i] = "V";
				} else {
					matrizLogicaString[indiceDestino][i] = "F";
				}
			} else {
				matrizLogicaString[indiceDestino][i] = "F";
			}
		}
	}

	private void resolveOr(int indiceInicio, int indiceFinal) {
		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("v")) {
				int indiceValorDireita = 0, indiceValorEsquerda = 0;

				int[] conectivoSubsequente = temConectivoDepois(i, "v");

				if (conectivoSubsequente[0] != -1) {
					indiceValorDireita = conectivoSubsequente[1];
				} else if (matrizLogicaString[i + 1][0].matches("\\(")) {
					indiceValorDireita = pegaIndiceValorParentesesDireita(i + 1);
				} else {
					indiceValorDireita = i + 1;
				}

				int[] conectivoAnterior = temConectivoAntes(i, "v");

				if (conectivoAnterior[0] != -1) {
					indiceValorEsquerda = conectivoAnterior[1];
				} else if (matrizLogicaString[i - 1][0].matches("\\)")) {
					indiceValorEsquerda = pegaIndiceValorParentesesEsquerda(i - 1);
				} else {
					indiceValorEsquerda = i - 1;
				}

				or(indiceValorEsquerda, indiceValorDireita, i);

			}
		}
	}

	private void or(int indiceValorEsquerda, int indiceValorDireita, int indiceDestino) {
		for (int i = 1; i < numeroDeLinhasUsadas + 1; i++) {
			if (matrizLogicaString[indiceValorEsquerda][i].matches("V")
					|| matrizLogicaString[indiceValorDireita][i].matches("V")) {
				matrizLogicaString[indiceDestino][i] = "V";
			} else {
				matrizLogicaString[indiceDestino][i] = "F";
			}
		}
	}

	private int[] temConectivoAntes(int indiceAtual, String conectivo) {

		int[] retorno = new int[2];

		retorno[0] = -1;
		retorno[1] = -1;

		for (int i = indiceAtual - 1; i >= 0; i--) {
			if (matrizLogicaString[i][0].matches("\\)")) {
				i = pegaParentesesInicialCorrespondente(i);
			} else if (matrizLogicaString[i][0].matches("\\(")) {
				return retorno;
			} else if (matrizLogicaString[i][0].matches("v|∧|\\*|→|↔|¬")) {
				retorno[1] = i;
				if (dicionarioDePrioridadesDeConectivos
						.get(conectivo) >= dicionarioDePrioridadesDeConectivos
								.get(matrizLogicaString[i][0])) {
					retorno[0] = 1;

					return retorno;
				} else {
					retorno[0] = -1;
					return retorno;
				}
			}
		}

		return retorno;
	}

	private int[] temConectivoDepois(int indiceAtual, String conectivo) {

		int[] retorno = new int[2];

		retorno[0] = -1;
		retorno[1] = -1;

		for (int i = indiceAtual + 1; i < tamanhoFraseLogica; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			} else if (matrizLogicaString[i][0].matches("\\)")) {
				return retorno;
			} else if (matrizLogicaString[i][0].matches("v|∧|\\*|→|↔|¬")) {
				retorno[1] = i;
				if (dicionarioDePrioridadesDeConectivos
						.get(conectivo) > dicionarioDePrioridadesDeConectivos
								.get(matrizLogicaString[i][0])) {
					retorno[0] = 1;
					return retorno;
				} else {
					retorno[0] = -1;
					return retorno;
				}
			}
		}

		return retorno;

	}

	private void resolveXor(int indiceInicio, int indiceFinal) {
		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("\\*")) {
				int indiceValorDireita = 0, indiceValorEsquerda = 0;

				int[] conectivoSubsequente = temConectivoDepois(i, "\\*");

				if (conectivoSubsequente[0] != -1) {
					indiceValorDireita = conectivoSubsequente[1];
				} else if (matrizLogicaString[i + 1][0].matches("\\(")) {
					indiceValorDireita = pegaIndiceValorParentesesDireita(i + 1);
				} else {
					indiceValorDireita = i + 1;
				}

				int[] conectivoAnterior = temConectivoAntes(i, "\\*");

				if (conectivoAnterior[0] != -1) {
					indiceValorEsquerda = conectivoAnterior[1];
				} else if (matrizLogicaString[i - 1][0].matches("\\)")) {
					indiceValorEsquerda = pegaIndiceValorParentesesEsquerda(i - 1);
				} else {
					indiceValorEsquerda = i - 1;
				}

				xor(indiceValorEsquerda, indiceValorDireita, i);

			}
		}
	}

	private void xor(int indiceValorEsquerda, int indiceValorDireita, int indiceDestino) {
		for (int i = 1; i < numeroDeLinhasUsadas + 1; i++) {
			if (matrizLogicaString[indiceValorEsquerda][i].matches("V")
					^ matrizLogicaString[indiceValorDireita][i].matches("V")) {
				matrizLogicaString[indiceDestino][i] = "V";
			} else {
				matrizLogicaString[indiceDestino][i] = "F";
			}
		}
	}

	private void resolveImplicacao(int indiceInicio, int indiceFinal) {
		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("→")) {
				int indiceValorDireita = 0, indiceValorEsquerda = 0;

				int[] conectivoSubsequente = temConectivoDepois(i, "→");

				if (conectivoSubsequente[0] != -1) {
					indiceValorDireita = conectivoSubsequente[1];
				} else if (matrizLogicaString[i + 1][0].matches("\\(")) {
					indiceValorDireita = pegaIndiceValorParentesesDireita(i + 1);
				} else {
					indiceValorDireita = i + 1;
				}

				int[] conectivoAnterior = temConectivoAntes(i, "→");

				if (conectivoAnterior[0] != -1) {
					indiceValorEsquerda = conectivoAnterior[1];
				} else if (matrizLogicaString[i - 1][0].matches("\\)")) {
					indiceValorEsquerda = pegaIndiceValorParentesesEsquerda(i - 1);
				} else {
					indiceValorEsquerda = i - 1;
				}

				implicacao(indiceValorEsquerda, indiceValorDireita, i);

			}
		}
	}

	private void implicacao(int indiceValorEsquerda, int indiceValorDireita,
			int indiceDestino) {
		for (int i = 1; i < numeroDeLinhasUsadas + 1; i++) {
			if (matrizLogicaString[indiceValorEsquerda][i].matches("V")
					&& matrizLogicaString[indiceValorDireita][i].matches("F")) {
				matrizLogicaString[indiceDestino][i] = "F";
			} else {
				matrizLogicaString[indiceDestino][i] = "V";
			}
		}
	}

	private void resolveBicondicional(int indiceInicio, int indiceFinal) {
		for (int i = indiceInicio; i < indiceFinal; i++) {
			if (matrizLogicaString[i][0].matches("↔")) {
				int indiceValorDireita = 0, indiceValorEsquerda = 0;

				int[] conectivoSubsequente = temConectivoDepois(i, "↔");

				if (conectivoSubsequente[0] != -1) {
					indiceValorDireita = conectivoSubsequente[1];
				} else if (matrizLogicaString[i + 1][0].matches("\\(")) {
					indiceValorDireita = pegaIndiceValorParentesesDireita(i + 1);
				} else {
					indiceValorDireita = i + 1;
				}

				int[] conectivoAnterior = temConectivoAntes(i, "↔");

				if (conectivoAnterior[0] != -1) {
					indiceValorEsquerda = conectivoAnterior[1];
				} else if (matrizLogicaString[i - 1][0].matches("\\)")) {
					indiceValorEsquerda = pegaIndiceValorParentesesEsquerda(i - 1);
				} else {
					indiceValorEsquerda = i - 1;
				}

				implicacaoBicondicional(indiceValorEsquerda, indiceValorDireita, i);

			}
		}
	}

	private void implicacaoBicondicional(int indiceValorEsquerda, int indiceValorDireita,
			int indiceDestino) {
		for (int i = 1; i < numeroDeLinhasUsadas + 1; i++) {
			if (matrizLogicaString[indiceValorEsquerda][i]
					.matches(matrizLogicaString[indiceValorDireita][i])) {
				matrizLogicaString[indiceDestino][i] = "V";
			} else {
				matrizLogicaString[indiceDestino][i] = "F";
			}
		}
	}

	public int pegaIndiceColunaFinal() {
		int indiceInicioReal = 0, indiceFinalReal = tamanhoFraseLogica - 1;

		int indiceRetorno = indiceInicioReal;

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("p|q|r|s")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("¬")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("∧")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("v")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("\\*")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("→")) {
				indiceRetorno = i;
			}
		}

		for (int i = indiceInicioReal; i < indiceFinalReal; i++) {
			if (matrizLogicaString[i][0].matches("\\(")) {
				i = pegaParentesesFinalCorrespondente(i);
			}
			if (matrizLogicaString[i][0].matches("↔")) {
				indiceRetorno = i;
			}
		}

		return indiceRetorno;
	}

	public String[][] retornaMatrizLogica() {

		return this.matrizLogicaString;
	}

}
