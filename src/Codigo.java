import java.util.List;

public class Codigo {
	
	public static int posicao;
	
	public static int posicaoLinha;
	
	private List<String> texto;
	
	public Codigo(List<String> texto) {
		
		this.texto = texto;
		this.posicao = 0;
		this.posicaoLinha = 0;
	}
	
	public char proximoDigito() {
		
		char digito = ';';

		if(temProximaLinha()){

			if(temProximoDigito()){
			
			digito = texto.get(this.posicaoLinha).charAt(posicao);
			
			this.posicao++;
			
			}else{

				this.posicaoLinha++;
				this.posicao = 0;

				return proximoDigito();
			
			}
		}
		
		return digito;
	}
	
	public boolean temProximoDigito() {
			
		return texto.get(this.posicaoLinha).length() > this.posicao;

	}
	
	public boolean temProximaLinha(){
		return posicaoLinha != texto.size();	
		
	}
	
}
