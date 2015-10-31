package jsonnet.core;

import jsonnet.core.model.Kind;
import jsonnet.core.model.Token;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    public List<Token> lex(String input) {
        List<Token> tokens = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            Kind kind = null;
            String data = "";
            char symbol = input.charAt(i);
            switch (symbol) {
                case ' ':
                    continue;
                case '{':
                    kind = Kind.BRACE_L;
                    break;
                case '}':
                    kind = Kind.BRACE_R;
                    break;
            }
            Token token = new Token(kind, data);
            tokens.add(token);
        }

        return tokens;
    }

}
