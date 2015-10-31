package jsonnet.core;

import jsonnet.core.model.Token;

import java.util.Queue;

public class Parser {

    private Lexer lexer = new Lexer();

    public void doParse(String input) {
        Queue<Token> tokens = lexer.lex(input);
        parse(tokens);
    }

    private void parse(Queue<Token> tokens) {

    }

}
