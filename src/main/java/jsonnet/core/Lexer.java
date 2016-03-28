package jsonnet.core;

import jsonnet.core.model.Kind;
import jsonnet.core.model.Token;

import java.util.LinkedList;
import java.util.Queue;

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
                case '"': {
                    i++;
                    for (; ; i++) {
                        if (input.charAt(i) == '"') {
                            break;
                        }
                        data.append(input.charAt(i));
                    }
                    i++;
                    kind = Kind.STRING_DOUBLE;
                    break;
                }
            }
            Token token = new Token(kind, data.toString());
            tokens.add(token);
        }

        return tokens;
    }

    private int lexWs(int index, String input) {
        for (; (input.charAt(index) == ' ' || input.charAt(index) == '\n' || input.charAt(index) == '\r')
                && index < input.length(); index++) {
            // TODO: need calculate indent
        }
        return index;
    }

}
