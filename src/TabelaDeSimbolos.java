import java.util.ArrayList;
import java.util.List;

public class TabelaDeSimbolos {

	public static int counter = 0;

	public List<Simbolo> getSimbulos() {

		return simbulos;
	}

	private List<Simbolo> simbulos = new ArrayList<>();

	public TabelaDeSimbolos(List<Simbolo> simbulos) {

		this.simbulos = simbulos;

	}

	public TabelaDeSimbolos() {


	}

	public int addSimbolo(Simbolo novoSimbolo) {
		simbulos.add(novoSimbolo);
		return counter;
	}

	//<VAR,1>token
	//<1,asgdgshd>

}
