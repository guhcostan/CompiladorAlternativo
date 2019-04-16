import java.util.ArrayList;
import java.util.List;

public class AnalisadorLexico {

    private Codigo codigo;
    private List<Token> tokens;

    public List<Token> criarTokens(Codigo codigo) {

        this.codigo = codigo;

        tokens = new ArrayList<>();

        char bufferDigito = ' ';

        try {
            while (!this.codigo.acabou()) {
                bufferDigito = this.codigo.proximoDigito();
                String buffer = "";
                switch (bufferDigito) {
                    case 'e'://else
                    	criarElseOrId(bufferDigito, buffer);
                        break;
                    case 'c'://else
                        //id missing
                    	criarCharOrId(bufferDigito, buffer);
                        break;
                    case 's'://struct
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
                                                this.tokens.add(new Token(TipoLexema.ESTRUTURA, "struct"));
                                            } else {
                                                //do later
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 'f'://float
                    	criarFloatOrId(bufferDigito, buffer);
                        break;
                    case 'i'://int,if
                    	criarIntOrIfOrId(bufferDigito, buffer);
                        break;
                    case 'w'://while
                    	criarWhileOrId(bufferDigito, buffer);
                        break;
                    case 'v'://void
                        bufferDigito = this.codigo.proximoDigito();
                        if (bufferDigito == 'o') {
                            bufferDigito = this.codigo.proximoDigito();
                            if (bufferDigito == 'i') {
                                bufferDigito = this.codigo.proximoDigito();
                                if (bufferDigito == 'd') {
                                    bufferDigito = this.codigo.proximoDigito();
                                    if (bufferDigito == ' ') {
                                        this.tokens.add(new Token(TipoLexema.VOID, "void"));
                                    } else {
                                        //do later
                                    }
                                }
                            }
                        }
                        break;
                    case 'r'://return
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
                                                this.tokens.add(new Token(TipoLexema.RETURN, "return"));
                                            } else {
                                                //do later
                                            }
                                        }
                                    }
                                }
                            }
                        }

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
                        this.tokens.add(new Token(TipoLexema.OPERADORES, "-"));
                        break;
                    case '+':
                        this.tokens.add(new Token(TipoLexema.OPERADORES, "+"));
                        break;
                    case '=':
                        bufferDigito = this.codigo.proximoDigito();
                        if (bufferDigito == ' ') {
                            this.tokens.add(new Token(TipoLexema.IGUAL, "="));
                        } else {
                            //do later
                        }
                        break;
                    case '<':

                        break;
                    case '>':
                        break;
                    case ';':
                        this.tokens.add(new Token(TipoLexema.SEMICON, ";"));
                        break;
                    default:
                        if (Character.isDigit(bufferDigito)) {
                            //number
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
                                this.tokens.add(new Token(TipoLexema.NUMFLOAT, number));
                            } else if (bufferDigito == ';' || bufferDigito == ',' || bufferDigito == ' ') {
                                this.tokens.add(new Token(TipoLexema.NUMINT, number));
                            }
                        } else if (Character.isAlphabetic(bufferDigito)) {
                            //id
                            String id = "";
                            while (bufferDigito != ';' && bufferDigito != ' ') {
                                id += bufferDigito;
                                bufferDigito = this.codigo.proximoDigito();
                            }
                            this.tokens.add(new Token(TipoLexema.IDENTIFICADOR, id));
                        }
                        //resto
                        break;

                }

                //bufferDigito = this.codigo.proximoDigito();

            }
        } catch (ArrayIndexOutOfBoundsException e) {
        	System.out.println(this.codigo.toString());
            System.out.println(e);
        }
        for (Token t : this.tokens) {
            System.out.println(t.toString());
        }
        return this.tokens;
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
		                    this.tokens.add(new Token(TipoLexema.LOOP, "while"));
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
		                this.tokens.add(new Token(TipoLexema.CONDICIONAL, "else"));
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
		                this.tokens.add(new Token(TipoLexema.CHAR, "char"));
		            } else {
		                //do later
		            }
		        }
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
		                    this.tokens.add(new Token(TipoLexema.FLOAT, "float"));
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
		            this.tokens.add(new Token(TipoLexema.INT, "int"));
		        } else {
		        	criaIdentificador(bufferDigito, buffer);
		        }
		    }else {
		    	criaIdentificador(bufferDigito, buffer);
		    }
		} else if (bufferDigito == 'f') {
		    bufferDigito = this.codigo.proximoDigito();
		    if (bufferDigito == ' ') {
		        this.tokens.add(new Token(TipoLexema.CONDICIONAL, "if"));
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
            Token t = this.identificador(buffer);
            if (t != null) {
                this.tokens.add(t);
            }
        } else {
            this.tokens.add(new Token(TipoLexema.IDENTIFICADOR, buffer));
        }
    }

    public Token identificador(String id) {

        char bufferDigito = this.codigo.proximoDigito();
        if (Character.isAlphabetic(bufferDigito) || Character.isDigit(bufferDigito)) {
            return identificador(id + bufferDigito);
        } else {
            return new Token(TipoLexema.IDENTIFICADOR, id);
        }
    }
}
