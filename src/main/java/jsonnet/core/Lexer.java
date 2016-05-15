package jsonnet.core;

import jsonnet.core.model.Kind;
import jsonnet.core.model.State;
import jsonnet.core.model.Token;

import java.util.LinkedList;
import java.util.Queue;

import static jsonnet.utils.LexerUtils.isSymbol;

public class Lexer {

    public Queue<Token> lex(String input) {
        Queue<Token> tokens = new LinkedList<>();

        for (int i = 0; i < input.length(); i++) {
            Kind kind = null;
            StringBuilder data = new StringBuilder("");

            i = lexWs(i, input);

            char symbol = input.charAt(i);
            switch (symbol) {
                case ' ':
                    continue;
                case '{': {
                    kind = Kind.BRACE_L;
                    data.append("{");
                    break;
                }
                case '}': {
                    kind = Kind.BRACE_R;
                    data.append("}");
                    break;
                }
                case '[': {
                    kind = Kind.BRACKET_L;
                    data.append("[");
                    break;
                }
                case ']': {
                    kind = Kind.BRACKET_R;
                    data.append("]");
                    break;
                }
                case ',': {
                    kind = Kind.COMMA;
                    data.append(",");
                    break;
                }
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    kind = Kind.NUMBER;
                    data.append(lexNumber(input, i));
                    break;
                }
                case '"': {
                    i++;
                    for (; ; i++) {
                        if (input.charAt(i) == '"') {
                            break;
                        }
                        data.append(input.charAt(i));
                    }
                    kind = Kind.STRING_DOUBLE;
                    break;
                }
                default: {
                    if (isSymbol(symbol)) {
                        int operatorBegin = i;
                        for (; isSymbol(input.charAt(i)); i++) {

                        }
                        data.append(input.substring(operatorBegin, i));
                        kind = Kind.OPERATOR;
                    }
                }
            }
            Token token = new Token(kind, data.toString());
            tokens.add(token);
        }

        return tokens;
    }

    private String lexNumber(String input, int i) {
        StringBuilder result = new StringBuilder();
        State state = State.BEGIN;
        while (true) {
            switch (state) {
                case BEGIN: {
                    switch (input.charAt(i)) {
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            state = State.AFTER_ONE_TO_NINE;
                            break;
                        }
                    }
                    break;
                }
                case AFTER_ONE_TO_NINE: {
                    switch (input.charAt(i)) {
                        default: {
                            return result.toString();
                        }
                    }
                }
            }
            result.append(input.charAt(i));
            i++;
        }
    }

    private int lexWs(int index, String input) {
        for (; (input.charAt(index) == ' ' || input.charAt(index) == '\n' || input.charAt(index) == '\r')
                && index < input.length(); index++) {
            // TODO: need calculate indent
        }
        return index;
    }

}
