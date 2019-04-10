import java.util.ArrayList;
import java.util.List;

public class AnalisadorLexico {

	public List<Simbolo> analisarEPegarSimbolos(Codigo codigo) {

		List<Simbolo> listaDeSimbolos = new ArrayList<>();

		char digito;

		while (codigo.temProximoDigito()) {
			digito = codigo.proximoDigito();
			if (digito == 'I' && codigo.temProximoDigito()) {

				if (codigo.proximoDigito() == 'F') {

					listaDeSimbolos.add(new Simbolo("IF", TipoLexema.CONDICIONAL));

				}
			}
		}
		return listaDeSimbolos;
	}

}
