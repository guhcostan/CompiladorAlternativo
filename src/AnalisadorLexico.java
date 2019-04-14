import java.util.ArrayList;
import java.util.List;

public class AnalisadorLexico {

    Codigo codigo;


    public List<Token> criarTokens(Codigo codigo) {

        this.codigo = codigo;

        List<Token> tokens = new ArrayList<>();

        char bufferDigito = ' ';

        try {
            while (!codigo.acabou()) {
                bufferDigito = codigo.proximoDigito();
                String buffer = "";
                switch (bufferDigito) {
                    case 'e'://else
                        //id missing
                        bufferDigito = codigo.proximoDigito();
                        if (bufferDigito == 'l') {
                            bufferDigito = codigo.proximoDigito();
                            if (bufferDigito == 's') {
                                bufferDigito = codigo.proximoDigito();
                                if (bufferDigito == 'e') {
                                    bufferDigito = codigo.proximoDigito();
                                    if (bufferDigito == ' ') {
                                        tokens.add(new Token(TipoLexema.CONDICIONAL, "else"));
                                    } else {
                                        //do later
                                    }
                                }
                            }
                        }
                        break;
                    case 'c'://else
                        //id missing
                        buffer += bufferDigito;
                        bufferDigito = codigo.proximoDigito();
                        if (bufferDigito == 'h') {
                            bufferDigito = codigo.proximoDigito();
                            if (bufferDigito == 'a') {
                                bufferDigito = codigo.proximoDigito();
                                if (bufferDigito == 'r') {
                                    bufferDigito = codigo.proximoDigito();
                                    if (bufferDigito == ' ') {
                                        tokens.add(new Token(TipoLexema.CHAR, "char"));
                                    } else {
                                        //do later
                                    }
                                }
                            }
                        } else {
                            criaIdentificador(tokens, bufferDigito, buffer);
                        }
                        break;
                    case 's'://struct
                        bufferDigito = codigo.proximoDigito();
                        if (bufferDigito == 't') {
                            bufferDigito = codigo.proximoDigito();
                            if (bufferDigito == 'r') {
                                bufferDigito = codigo.proximoDigito();
                                if (bufferDigito == 'u') {
                                    bufferDigito = codigo.proximoDigito();
                                    if (bufferDigito == 'c') {
                                        bufferDigito = codigo.proximoDigito();
                                        if (bufferDigito == 't') {
                                            bufferDigito = codigo.proximoDigito();
                                            if (bufferDigito == ' ') {
                                                tokens.add(new Token(TipoLexema.ESTRUTURA, "struct"));
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
                        bufferDigito = codigo.proximoDigito();
                        if (bufferDigito == 'l') {
                            bufferDigito = codigo.proximoDigito();
                            if (bufferDigito == 'o') {
                                bufferDigito = codigo.proximoDigito();
                                if (bufferDigito == 'a') {
                                    bufferDigito = codigo.proximoDigito();
                                    if (bufferDigito == 't') {
                                        bufferDigito = codigo.proximoDigito();
                                        if (bufferDigito == ' ') {
                                            tokens.add(new Token(TipoLexema.FLOAT, "float"));
                                        } else {
                                            //do later
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 'i'://int,if
                        buffer += bufferDigito;
                        bufferDigito = codigo.proximoDigito();
                        if (bufferDigito == 'n') {
                            buffer += bufferDigito;
                            bufferDigito = codigo.proximoDigito();
                            if (bufferDigito == 't') {
                                buffer += bufferDigito;
                                bufferDigito = codigo.proximoDigito();
                                if (bufferDigito == ' ') {
                                    tokens.add(new Token(TipoLexema.INT, "int"));
                                } else {
                                    buffer += bufferDigito;
                                    Token t = this.identificador(buffer);
                                    if (t != null) {
                                        tokens.add(t);
                                    }
                                    //do later
                                }
                            }
                        } else if (bufferDigito == 'f') {
                            bufferDigito = codigo.proximoDigito();
                            if (bufferDigito == ' ') {
                                tokens.add(new Token(TipoLexema.CONDICIONAL, "if"));
                            } else {
                                //do later
                            }
                        } else {
                            criaIdentificador(tokens, bufferDigito, buffer);

                            //do later
                        }
                        break;
                    case 'w'://while
                        bufferDigito = codigo.proximoDigito();
                        if (bufferDigito == 'h') {
                            bufferDigito = codigo.proximoDigito();
                            if (bufferDigito == 'i') {
                                bufferDigito = codigo.proximoDigito();
                                if (bufferDigito == 'l') {
                                    bufferDigito = codigo.proximoDigito();
                                    if (bufferDigito == 'e') {
                                        bufferDigito = codigo.proximoDigito();
                                        if (bufferDigito == ' ') {
                                            tokens.add(new Token(TipoLexema.LOOP, "while"));
                                        } else {
                                            //do later
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 'v'://void
                        bufferDigito = codigo.proximoDigito();
                        if (bufferDigito == 'o') {
                            bufferDigito = codigo.proximoDigito();
                            if (bufferDigito == 'i') {
                                bufferDigito = codigo.proximoDigito();
                                if (bufferDigito == 'd') {
                                    bufferDigito = codigo.proximoDigito();
                                    if (bufferDigito == ' ') {
                                        tokens.add(new Token(TipoLexema.VOID, "void"));
                                    } else {
                                        //do later
                                    }
                                }
                            }
                        }
                        break;
                    case 'r'://return
                        bufferDigito = codigo.proximoDigito();
                        if (bufferDigito == 'e') {
                            bufferDigito = codigo.proximoDigito();
                            if (bufferDigito == 't') {
                                bufferDigito = codigo.proximoDigito();
                                if (bufferDigito == 'u') {
                                    bufferDigito = codigo.proximoDigito();
                                    if (bufferDigito == 'r') {
                                        bufferDigito = codigo.proximoDigito();
                                        if (bufferDigito == 'n') {
                                            bufferDigito = codigo.proximoDigito();
                                            if (bufferDigito == ' ') {
                                                tokens.add(new Token(TipoLexema.RETURN, "return"));
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
                        tokens.add(new Token(TipoLexema.ASPAS, "'"));
                        bufferDigito = codigo.proximoDigito();
                        while (bufferDigito != '\'') {
                            buffer += bufferDigito;
                            bufferDigito = codigo.proximoDigito();
                        }
                        tokens.add(new Token(TipoLexema.CHAR, buffer));
                        tokens.add(new Token(TipoLexema.ASPAS, "'"));

                        break;

                    case '-':
                        tokens.add(new Token(TipoLexema.OPERADORES, "-"));
                        break;
                    case '+':
                        tokens.add(new Token(TipoLexema.OPERADORES, "+"));
                        break;
                    case '=':
                        bufferDigito = codigo.proximoDigito();
                        if (bufferDigito == ' ') {
                            tokens.add(new Token(TipoLexema.IGUAL, "="));
                        } else {
                            //do later
                        }
                        break;
                    case '<':

                        break;
                    case '>':
                        break;
                    case ';':
                        tokens.add(new Token(TipoLexema.SEMICON, ";"));
                        break;
                    default:
                        if (Character.isDigit(bufferDigito)) {
                            //number
                            String number = "";
                            while (Character.isDigit(bufferDigito)) {
                                number += bufferDigito;
                                bufferDigito = codigo.proximoDigito();
                            }
                            if (bufferDigito == '.') {
                                number += bufferDigito;
                                bufferDigito = codigo.proximoDigito();
                                while (Character.isDigit(bufferDigito)) {
                                    number += bufferDigito;
                                    bufferDigito = codigo.proximoDigito();
                                }
                                tokens.add(new Token(TipoLexema.NUMFLOAT, number));
                            } else if (bufferDigito == ';' || bufferDigito == ',' || bufferDigito == ' ') {
                                tokens.add(new Token(TipoLexema.NUMINT, number));
                            }
                        } else if (Character.isAlphabetic(bufferDigito)) {
                            //id
                            String id = "";
                            while (bufferDigito != ';' && bufferDigito != ' ') {
                                id += bufferDigito;
                                bufferDigito = codigo.proximoDigito();
                            }
                            tokens.add(new Token(TipoLexema.IDENTIFICADOR, id));
                        }
                        //resto
                        break;

                }

                //bufferDigito = codigo.proximoDigito();

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
        for (Token t : tokens) {
            System.out.println(t.toString());
        }
        return tokens;
    }

    private void criaIdentificador(List<Token> tokens, char bufferDigito, String buffer) {
        if (bufferDigito != ' ') {
            buffer += bufferDigito;
            Token t = this.identificador(buffer);
            if (t != null) {
                tokens.add(t);
            }
        } else {
            tokens.add(new Token(TipoLexema.IDENTIFICADOR, buffer));
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
