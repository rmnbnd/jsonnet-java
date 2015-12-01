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
            String data = "";
            char symbol = input.charAt(i);
            switch (symbol) {
                case ' ':
                    continue;
                case '{':
                    kind = Kind.BRACE_L;
                    data = "{";
                    break;
                case '}':
                    kind = Kind.BRACE_R;
                    data = "}";
                    break;
            }
            Token token = new Token(kind, data);
            tokens.add(token);
        }

        return tokens;
    }

}
