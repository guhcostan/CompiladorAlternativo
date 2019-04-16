/**
 * Classe que representa um token.
 */

public class Token {

	private TipoLexema tipoLexema;

	private String valor; 

	public Token(TipoLexema tipo, String valor) {

		this.tipoLexema = tipo;
		this.valor = valor;
	}

	public String getValor() {

		return valor;
	}

	public TipoLexema getTipoLexema() {

		return tipoLexema;
	}

}
