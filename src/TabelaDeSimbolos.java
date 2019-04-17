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
		if(!this.alreadyExist(novoSimbolo)) {
			simbulos.add(novoSimbolo);
		}
		return counter;
	}
	
	public boolean alreadyExist(Simbolo novoSimbolo) {
		for(Simbolo s : this.simbulos) {
			if(s.getValue().equals(novoSimbolo.getValue())) {
				return true;
			}
		}
		return false;
	}

	//<VAR,1>token
	//<1,asgdgshd>

}
