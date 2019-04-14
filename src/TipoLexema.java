public enum TipoLexema {
	NUMINT("NUM"), // int
	NUMFLOAT("NUMFLOAT"), // float
	INT("INT"),//int
	FLOAT("FLOAT"),//float
	CHAR("CHAR"),
	SEQUENCIA("SEQUENCIA"),
	ESTRUTURA("ESTRUTURA"), // struct
	LOOP("LOOP"), // while
	VOID("VOID"), // void
	OPERADORES("OPERADORES"), // +,-
	IGUAL("IGUAL"),//=
	RETURN("RETURN"),
	CONDICIONAL("CONDICIONAL"),//  int, float, struct, if, else, while, void, return (caixa baixa)
	IDENTIFICADOR("IDENTIFICADOR")
	;

	private String name;
	private TipoLexema(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
//inteiro, real, caractere, arranjo e registro