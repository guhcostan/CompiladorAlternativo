public class Codigo {

	public int posicao;

	private String texto;

	public Codigo(String texto) {

		this.texto = texto;
		this.posicao = 0;
	}

	public char proximoDigito() {

		char digito = texto.charAt(posicao);

		posicao++;

		return digito;
	}

	public boolean temProximoDigito() {

		return texto.length() > posicao;
	}

}
