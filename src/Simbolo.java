public class Simbolo {

	private final int id;
	private final String value;
	
	public Simbolo(String value) {

		this.id = TabelaDeSimbolos.counter++;
		this.value = value;

	}

	public String getValue() {

		return value;
	}
	
	public int getId() {

		return id;
	}

}
