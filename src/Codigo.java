import java.util.List;

public class Codigo {
	
	private int posicao;
	
	private int posicaoLinha;
	
	private List<String> texto;
	
	public Codigo(List<String> texto) {
		
		this.texto = texto;
        this.posicao = 0;
        this.posicaoLinha = 0;
	}

    public char proximoDigito() throws ArrayIndexOutOfBoundsException {
		
		char digito = ';';

        if (temProximoDigito()) {

            digito = texto.get(this.posicaoLinha).charAt(this.posicao);

            this.posicao++;

        } else {

            this.posicaoLinha++;
            this.posicao = 0;

            return this.proximoDigito();

        }

        if (!(temProximoDigito() || temProximaLinha())) {
            throw new ArrayIndexOutOfBoundsException("Codigo acabou");
		}
		
		return digito;
	}
	
	public boolean temProximoDigito() {

        return texto.get(this.posicaoLinha).length() > this.posicao;

	}
	
	public boolean temProximaLinha(){
        return this.posicaoLinha != texto.size() - 1;
		
	}

    public boolean acabou() {
        return !temProximaLinha() && !temProximoDigito();
    }
    
    public String toString() {
		return new String("Coluna:" + Integer.toString(this.posicao + 1) +"\n" + "Linha:" + Integer.toString(this.posicaoLinha + 1) + "" );
    	
    }
}
