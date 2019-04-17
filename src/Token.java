/**
 * Classe que representa um token.
 */

public class Token {

	private TipoLexema tipoLexema = null;

	private String valor = null; 

	public Token(TipoLexema tipo) {
		this.tipoLexema = tipo;
	}
	
	public Token(TipoLexema tipo, String valor) {
		this(tipo);
		this.valor = valor;
	}

	public String getValor() {

		return valor;
	}

	public TipoLexema getTipoLexema() {

		return tipoLexema;
	}
	
	public String toString() {
		return "<" + ((this.valor == null)? this.tipoLexema.getName() : this.tipoLexema.getName()+ "," + this.valor) + ">";
		
	}

}
