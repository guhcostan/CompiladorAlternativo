public class Simbolo {

	private final int id;

	public String getValue() {

		return value;
	}

	private final String value;

	public Simbolo(String value) {

		this.id = TabelaDeSimbolos.counter++;
		this.value = value;

	}

	public int getId() {

		return id;
	}

}
