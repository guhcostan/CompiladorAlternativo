public class Compilador {

	private AnalisadorLexico analisadorLexico;

	private AnalisadorSintatico analisadorSintatico;

	private AnalisadorSemantico analisadorSemantico;

	public Compilador() {

		this.analisadorLexico = new AnalisadorLexico();
		this.analisadorSintatico = new AnalisadorSintatico();
		this.analisadorSemantico = new AnalisadorSemantico();
	}

	public void compilar(Codigo codigo) {

		this.analisadorLexico.criarTokens(codigo);

	}

}
