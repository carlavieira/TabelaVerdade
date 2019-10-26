package logicaDeConversao;

public class Testes {

	private static void imprimeValoresDaTabela(String frase, String[][] tabela) {
		for (int i = 0; i < frase.length(); i++) {
			for (int j = 0; j < 17; j++) {
				System.out.print(tabela[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {

		String fraseLogica = "p+q∧(¬p+r)∧s";
		String fraseLogica2 = "!(p∧q∧(r∧p∧(p+r)))";

		TabelaVerdade tv = new TabelaVerdade(fraseLogica);

		imprimeValoresDaTabela(fraseLogica, tv.retornaMatrizLogica());
	}

}
