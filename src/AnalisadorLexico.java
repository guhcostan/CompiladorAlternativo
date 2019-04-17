import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AnalisadorLexico {

    private Codigo codigo;

    private List<Token> tokens;

    private TabelaDeSimbolos tabelaSimbolos = new TabelaDeSimbolos();
    
	public List<Token> getTokens() {

		return tokens;
	}

	public TabelaDeSimbolos getTabelaSimbolos() {

		return tabelaSimbolos;
	}

	public List<Token> criarTokens(Codigo codigo) {

        this.codigo = codigo;

        tokens = new ArrayList<>();

        char bufferDigito = ' ';

        try {
            while (!this.codigo.acabou()) {
                bufferDigito = this.codigo.proximoDigito();
                String buffer = "";
                switch (bufferDigito) {
                    case 'e':
                    	criarElseOrId(bufferDigito, buffer);
                        break;
                    case 'c':
                    	criarCharOrId(bufferDigito, buffer);
                        break;
                    case 's':
                    	criarStructOrId(bufferDigito, buffer);
                        break;
                    case 'f':
                    	criarFloatOrId(bufferDigito, buffer);
                        break;
                    case 'i':
                    	criarIntOrIfOrId(bufferDigito, buffer);
                        break;
                    case 'w':
                    	criarWhileOrId(bufferDigito, buffer);
                        break;
                    case 'v':
                    	criarVoidOrId(bufferDigito, buffer);
                        break;
                    case 'r':
                    	criarReturnOrId(bufferDigito, buffer);
                        break;
                    case '\'':
                        buffer = "";
                        this.tokens.add(new Token(TipoLexema.ASPAS, "'"));
                        bufferDigito = this.codigo.proximoDigito();
                        while (bufferDigito != '\'') {
                            buffer += bufferDigito;
                            bufferDigito = this.codigo.proximoDigito();
                        }
                        this.tokens.add(new Token(TipoLexema.CHAR, buffer));
                        this.tokens.add(new Token(TipoLexema.ASPAS, "'"));
                        break;
                    case '-':
                        this.tokens.add(new Token(TipoLexema.OPERADORES));
                        break;
                    case '+':
                        this.tokens.add(new Token(TipoLexema.OPERADORES));
                        break;
                    case '=':
                    	buffer += bufferDigito;
                        bufferDigito = this.codigo.proximoDigito();
                        if (bufferDigito == ' ') {
                            this.tokens.add(new Token(TipoLexema.IGUAL));
                        }else if (bufferDigito == '=') {
                        	buffer += bufferDigito;
                        	this.tokens.add(new Token(TipoLexema.IGUALLOGICO));
                        }
                        break;
                    case '<':
                    	buffer += bufferDigito;
                        bufferDigito = this.codigo.proximoDigito();
                        if (bufferDigito == '=') {
                        	buffer += bufferDigito;
                        	this.tokens.add(new Token(TipoLexema.MENORIGUAL));
                        }else{
                        	this.tokens.add(new Token(TipoLexema.MENOR));
                        }
                        break;
                    case '>':
                    	buffer += bufferDigito;
                        bufferDigito = this.codigo.proximoDigito();
                        if (bufferDigito == '=') {
                        	buffer += bufferDigito;
                        	this.tokens.add(new Token(TipoLexema.MAIORIGUAL));
                        }else{
                        	this.tokens.add(new Token(TipoLexema.MAIOR));
                        }
                    	break;
                    case ';':
                        this.tokens.add(new Token(TipoLexema.SEMICON));
                        break;
                    case '(':
                    	this.tokens.add(new Token(TipoLexema.PARENTESEESQUERDO));
                        break;
                    case ')':
                    	this.tokens.add(new Token(TipoLexema.PARENTESEESQUERDO));
                        break;
                    case '{':
                    	this.tokens.add(new Token(TipoLexema.CHAVESESQUERDA));
                        break;
                    case '}':
                    	this.tokens.add(new Token(TipoLexema.CHAVESDIREITA));
                        break;
                    default:
                        if (Character.isDigit(bufferDigito)) {
                            this.criarConstanteNumerica(bufferDigito);
                        } else if (Character.isAlphabetic(bufferDigito)) {
                        	buffer += bufferDigito;
                        	bufferDigito = this.codigo.proximoDigito();
                        	this.criaIdentificador(bufferDigito, buffer);
                        }
                        //resto
                        break;

                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
        	System.out.println(this.codigo.toString());
            System.out.println(e);
        }
		mostrarDados();
		return this.tokens;
    }

	private void criarConstanteNumerica(char bufferDigito) {
		String number = "";
		while (Character.isDigit(bufferDigito)) {
		    number += bufferDigito;
		    bufferDigito = this.codigo.proximoDigito();
		}
		if (bufferDigito == '.') {
		    number += bufferDigito;
		    bufferDigito = this.codigo.proximoDigito();
		    while (Character.isDigit(bufferDigito)) {
		        number += bufferDigito;
		        bufferDigito = this.codigo.proximoDigito();
		    }
		    Simbolo novoSimbolo = new Simbolo(number);
			this.tabelaSimbolos.addSimbolo(novoSimbolo);
		    this.tokens.add(new Token(TipoLexema.NUMFLOAT, Integer.toString(novoSimbolo.getId())));
		} else if (bufferDigito == ';' || bufferDigito == ',' || bufferDigito == ' ') {
			Simbolo novoSimbolo = new Simbolo(number);
			this.tabelaSimbolos.addSimbolo(novoSimbolo);
		    this.tokens.add(new Token(TipoLexema.NUMINT, Integer.toString(novoSimbolo.getId())));
		    if(bufferDigito == ';' ) {
		    	this.tokens.add(new Token(TipoLexema.SEMICON));
		    }
		}
	}

	private void criarReturnOrId(char bufferDigito, String buffer) {
		buffer += bufferDigito;
		bufferDigito = this.codigo.proximoDigito();
		if (bufferDigito == 'e') {
		    bufferDigito = this.codigo.proximoDigito();
		    if (bufferDigito == 't') {
		        bufferDigito = this.codigo.proximoDigito();
		        if (bufferDigito == 'u') {
		            bufferDigito = this.codigo.proximoDigito();
		            if (bufferDigito == 'r') {
		                bufferDigito = this.codigo.proximoDigito();
		                if (bufferDigito == 'n') {
		                    bufferDigito = this.codigo.proximoDigito();
		                    if (bufferDigito == ' ') {
		                        this.tokens.add(new Token(TipoLexema.RETURN));
		                    } else {
		                       this.criaIdentificador(bufferDigito, buffer);
		                    }
		                } else {
		                    this.criaIdentificador(bufferDigito, buffer);
		                }
		            } else {
		                this.criaIdentificador(bufferDigito, buffer);
		            }
		        } else {
		            this.criaIdentificador(bufferDigito, buffer);
		        }
		    } else {
		        this.criaIdentificador(bufferDigito, buffer);
		    }
		} else {
		    this.criaIdentificador(bufferDigito, buffer);
		}
	}

	private void criarStructOrId(char bufferDigito, String buffer) {
		buffer += bufferDigito;
		bufferDigito = this.codigo.proximoDigito();
		if (bufferDigito == 't') {
		    bufferDigito = this.codigo.proximoDigito();
		    if (bufferDigito == 'r') {
		        bufferDigito = this.codigo.proximoDigito();
		        if (bufferDigito == 'u') {
		            bufferDigito = this.codigo.proximoDigito();
		            if (bufferDigito == 'c') {
		                bufferDigito = this.codigo.proximoDigito();
		                if (bufferDigito == 't') {
		                    bufferDigito = this.codigo.proximoDigito();
		                    if (bufferDigito == ' ') {
		                        this.tokens.add(new Token(TipoLexema.ESTRUTURA));
		                    } else {
		                    	this.criaIdentificador(bufferDigito, buffer);
		                    }
		                }else {
		                	this.criaIdentificador(bufferDigito, buffer);
		                }
		            }else {
		            	this.criaIdentificador(bufferDigito, buffer);
		            }
		        }else {
		        	this.criaIdentificador(bufferDigito, buffer);
		        }
		    }else {
		    	this.criaIdentificador(bufferDigito, buffer);
		    }
		}else {
			this.criaIdentificador(bufferDigito, buffer);
		}
	}

	private void criarVoidOrId(char bufferDigito, String buffer) {
		buffer += bufferDigito;
		bufferDigito = this.codigo.proximoDigito();
		if (bufferDigito == 'o') {
		    bufferDigito = this.codigo.proximoDigito();
		    if (bufferDigito == 'i') {
		        bufferDigito = this.codigo.proximoDigito();
		        if (bufferDigito == 'd') {
		            bufferDigito = this.codigo.proximoDigito();
		            if (bufferDigito == ' ') {
		                this.tokens.add(new Token(TipoLexema.VOID));
		            } else {
		                this.criaIdentificador(bufferDigito, buffer);
		            }
		        }else {
		        	this.criaIdentificador(bufferDigito, buffer);
		        }
		    }else {
		    	this.criaIdentificador(bufferDigito, buffer);
		    }
		}else {
			this.criaIdentificador(bufferDigito, buffer);
		}
	}

	private void criarWhileOrId(char bufferDigito, String buffer) {
		buffer += bufferDigito;
		bufferDigito = this.codigo.proximoDigito();
		if (bufferDigito == 'h') {
			buffer += bufferDigito;
		    bufferDigito = this.codigo.proximoDigito();
		    if (bufferDigito == 'i') {
		    	buffer += bufferDigito;
		        bufferDigito = this.codigo.proximoDigito();
		        if (bufferDigito == 'l') {
		        	buffer += bufferDigito;
		            bufferDigito = this.codigo.proximoDigito();
		            if (bufferDigito == 'e') {
		            	buffer += bufferDigito;
		                bufferDigito = this.codigo.proximoDigito();
		                if (bufferDigito == ' ') {
		                    this.tokens.add(new Token(TipoLexema.LOOP));
		                } else {
		                	criaIdentificador(bufferDigito, buffer);
		                }
		            }else {
		            	criaIdentificador(bufferDigito, buffer);
		            }
		        }else {
		        	criaIdentificador(bufferDigito, buffer);
		        }
		    }else {
		    	criaIdentificador(bufferDigito, buffer);
		    }
		}else {
			criaIdentificador(bufferDigito, buffer);
		}
	}

	private void criarElseOrId(char bufferDigito, String buffer) {
		buffer += bufferDigito;
		bufferDigito = this.codigo.proximoDigito();
		if (bufferDigito == 'l') {
			buffer += bufferDigito;
		    bufferDigito = this.codigo.proximoDigito();
		    if (bufferDigito == 's') {
		    	buffer += bufferDigito;
		        bufferDigito = this.codigo.proximoDigito();
		        if (bufferDigito == 'e') {
		        	buffer += bufferDigito;
		            bufferDigito = this.codigo.proximoDigito();
		            if (bufferDigito == ' ') {
		                this.tokens.add(new Token(TipoLexema.CONDICIONAL));
		            } else {
		            	criaIdentificador(bufferDigito, buffer);
		            }
		        }else {
		        	criaIdentificador(bufferDigito, buffer);
		        }
		    }else {
		    	criaIdentificador(bufferDigito, buffer);
		    }
		}else {
			criaIdentificador(bufferDigito, buffer);
		}
	}

	private void criarCharOrId(char bufferDigito, String buffer) {
		buffer += bufferDigito;
		bufferDigito = this.codigo.proximoDigito();
		if (bufferDigito == 'h') {
		    bufferDigito = this.codigo.proximoDigito();
		    if (bufferDigito == 'a') {
		        bufferDigito = this.codigo.proximoDigito();
		        if (bufferDigito == 'r') {
		            bufferDigito = this.codigo.proximoDigito();
		            if (bufferDigito == ' ') {
		                this.tokens.add(new Token(TipoLexema.CHAR));
		            } else {
		            	criaIdentificador(bufferDigito, buffer);
		            }
		        }else {
		        	criaIdentificador(bufferDigito, buffer);
		        }
		    }else {
		    	criaIdentificador(bufferDigito, buffer);
		    }
		} else {
		    criaIdentificador(bufferDigito, buffer);
		}
	}

	private void criarFloatOrId(char bufferDigito, String buffer) {
		buffer += bufferDigito;
		bufferDigito = this.codigo.proximoDigito();
		if (bufferDigito == 'l') {
			buffer += bufferDigito;
		    bufferDigito = this.codigo.proximoDigito();
		    if (bufferDigito == 'o') {
		    	buffer += bufferDigito;
		        bufferDigito = this.codigo.proximoDigito();
		        if (bufferDigito == 'a') {
		        	buffer += bufferDigito;
		            bufferDigito = this.codigo.proximoDigito();
		            if (bufferDigito == 't') {
		            	buffer += bufferDigito;
		                bufferDigito = this.codigo.proximoDigito();
		                if (bufferDigito == ' ') {
		                    this.tokens.add(new Token(TipoLexema.FLOAT));
		                } else {
		                	criaIdentificador(bufferDigito, buffer);
		                }
		            }else {
		            	criaIdentificador(bufferDigito, buffer);
		            }
		        }else {
		        	criaIdentificador(bufferDigito, buffer);
		        }
		    }else {
		    	criaIdentificador(bufferDigito, buffer);
		    }
		}else {
			criaIdentificador(bufferDigito, buffer);
		}
	}
	

	private void criarIntOrIfOrId(char bufferDigito, String buffer) {
		buffer += bufferDigito;
		bufferDigito = this.codigo.proximoDigito();
		if (bufferDigito == 'n') {
		    buffer += bufferDigito;
		    bufferDigito = this.codigo.proximoDigito();
		    if (bufferDigito == 't') {
		        buffer += bufferDigito;
		        bufferDigito = this.codigo.proximoDigito();
		        if (bufferDigito == ' ') {
		            this.tokens.add(new Token(TipoLexema.INT));
		        } else {
		        	criaIdentificador(bufferDigito, buffer);
		        }
		    }else {
		    	criaIdentificador(bufferDigito, buffer);
		    }
		} else if (bufferDigito == 'f') {
		    bufferDigito = this.codigo.proximoDigito();
		    if (bufferDigito == ' ') {
		        this.tokens.add(new Token(TipoLexema.CONDICIONAL));
		    } else {
		    	criaIdentificador(bufferDigito, buffer);
		    }
		} else {
		    criaIdentificador(bufferDigito, buffer);
		}
	}

    private void criaIdentificador(char bufferDigito, String buffer) {
        if (bufferDigito != ' ') {
            buffer += bufferDigito;
            this.identificador(buffer);
        } else {
        	Simbolo novoSimbolo = new Simbolo(buffer);
        	this.tabelaSimbolos.addSimbolo(novoSimbolo);
            this.tokens.add(new Token(TipoLexema.IDENTIFICADOR, Integer.toString(novoSimbolo.getId())));
        }
    }
    

    public void identificador(String id) {

        char bufferDigito = this.codigo.proximoDigito();
        if (Character.isAlphabetic(bufferDigito) || Character.isDigit(bufferDigito)) {
        	this.identificador(id + bufferDigito);
        } else {
        	Simbolo novoSimbolo = new Simbolo(id);
        	this.tabelaSimbolos.addSimbolo(novoSimbolo);
        	Token t = new Token(TipoLexema.IDENTIFICADOR, Integer.toString(novoSimbolo.getId()));
        	this.tokens.add(t);
            
        }
    }

    private void mostrarDados() {

		System.out.println("\n|--------------------------------------------------------|\n" +
				             "|                       Tolkens                          |" +
				           "\n|--------------------------------------------------------|\n" );
		for (Token t : this.tokens) {
			System.out.println(t.toString());
		}

		System.out.println(
				"\n|--------------------------------------------------------|\n" +
				  "|                  Tabela de Simbolos                    |" +
				"\n|--------------------------------------------------------|\n" );
		for (Simbolo s : this.tabelaSimbolos.getSimbulos()) {
			System.out.println(s.getId() + " - " + s.getValue());
		}

	}
}
