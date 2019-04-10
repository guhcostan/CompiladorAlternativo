public class Compilador {

	private TabelaDeSimbolos tabelaDeSimbolos;

	private AnalisadorLexico analisadorLexico;

	private AnalisadorSintatico analisadorSintatico;

	private AnalisadorSemantico analisadorSemantico;

	public Compilador() {

		this.tabelaDeSimbolos = new TabelaDeSimbolos();
		this.analisadorLexico = new AnalisadorLexico();
		this.analisadorSintatico = new AnalisadorSintatico();
		this.analisadorSemantico = new AnalisadorSemantico();
	}

	public void compilar(Codigo codigo) {

		TabelaDeSimbolos tabelaDeSimbolos = new TabelaDeSimbolos(this.analisadorLexico.analisarEPegarSimbolos(codigo));

		System.out.println(tabelaDeSimbolos.toString());
	}

}
