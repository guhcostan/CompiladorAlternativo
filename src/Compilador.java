import java.io.FileInputStream;

public class Compilador {

	public TabelaDeSimbolos tabelaDeSimbolos;
	public static AnalisadorLexico analisadorLexico;
	public AnalisadorSintatico analisadorSintatico;
	public AnalisadorSemantico analisadorSemantico;

	public static void compilar(String codigo) {

		TabelaDeSimbolos tabelaDeSimbolos = new TabelaDeSimbolos(analisadorLexico.analisarEPegarSimbolos(codigo));
	}

}
