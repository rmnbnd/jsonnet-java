package jsonnet.core;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.Kind;
import jsonnet.core.model.Token;
import jsonnet.core.model.ast.ObjectElement;

import java.util.Queue;

public class Parser {

    private Lexer lexer = new Lexer();

    public void doParse(String input) {
        Queue<Token> tokens = lexer.lex(input);
        parse(tokens);
    }

    private void parse(Queue<Token> tokens) {
        Token begin = tokens.peek();
        switch (begin.getKind()) {
            default:
                parseTerminal(tokens);
        }
    }

    private AST parseTerminal(Queue<Token> tokens) {
        Token token = tokens.poll();
        switch (token.getKind()) {
            case BRACE_R:
            case BRACKET_R:
            case COMMA:
            case DOT:
            case SEMICOLON:
                throw new RuntimeException("error parsing terminal");
            case BRACE_L:
                return parseObjectRemainder(tokens);
        }
        return null;
    }

    private AST parseObjectRemainder(Queue<Token> tokens) {
        do {
            Token next = tokens.poll();
            if (next.getKind().equals(Kind.BRACE_R)) {
                return new ObjectElement();
            }
        } while (true);
    }

}
