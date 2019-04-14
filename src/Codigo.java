import java.util.List;

public class Codigo {
	
	public static int posicao;
	
	public static int posicaoLinha;
	
	private List<String> texto;
	
	public Codigo(List<String> texto) {
		
		this.texto = texto;
        posicao = 0;
        posicaoLinha = 0;
	}

    public char proximoDigito() throws ArrayIndexOutOfBoundsException {
		
		char digito = ';';

        if (temProximoDigito()) {

            digito = texto.get(posicaoLinha).charAt(posicao);

            posicao++;

        } else {

            posicaoLinha++;
            posicao = 0;

            return this.proximoDigito();

        }

        if (!(temProximoDigito() || temProximaLinha())) {
            throw new ArrayIndexOutOfBoundsException("Codigo acabou");
		}
		
		return digito;
	}
	
	public boolean temProximoDigito() {

        return texto.get(posicaoLinha).length() > posicao;

	}
	
	public boolean temProximaLinha(){
        return posicaoLinha != texto.size() - 1;
		
	}

    public boolean acabou() {
        return !temProximaLinha() && !temProximoDigito();
    }
}
