package jsonnet.core;

import jsonnet.core.model.ast.*;
import jsonnet.core.model.Kind;
import jsonnet.core.model.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static jsonnet.core.model.Kind.COMMA;
import static jsonnet.core.model.Kind.STRING_DOUBLE;
import static jsonnet.core.model.ast.LiteralString.TokenKind;

public class Parser {

    public AST doParse(Queue<Token> tokens) {
        return parse(tokens);
    }

    private AST parse(Queue<Token> tokens) {
        Token begin = tokens.peek();
        switch (begin.getKind()) {
            default:
                return parseTerminal(tokens);
        }
    }

    private AST parseTerminal(Queue<Token> tokens) {
        Token token = tokens.poll();
        switch (token.getKind()) {
            case BRACE_R:
            case BRACKET_R:
            case COMMA:
            case DOT:
            case SEMICOLON: {
                throw new RuntimeException("error parsing terminal");
            }
            case BRACE_L: {
                return parseObjectRemainder(tokens);
            }
            case BRACKET_L: {
                Token next = tokens.peek();
                AST first = parse(tokens);
                boolean gotComma = false;
                next = tokens.peek();
                if (!gotComma && next.getKind() == Kind.COMMA) {
                    Token comma = tokens.poll();
                    next = tokens.peek();
                    gotComma = true;
                }
                List<Element> elements = new ArrayList<>();
                elements.add(new Element(first));
                do {
                    if (next.getKind() == Kind.BRACKET_R) {
                        Token bracketR = tokens.poll();
                        return new Array(elements, gotComma);
                    }
                    AST expr = parse(tokens);
                    gotComma = false;
                    next = tokens.peek();
                    if (next.getKind() == Kind.COMMA) {
                        Token comma = tokens.poll();
                        next = tokens.peek();
                        gotComma = true;
                    }
                    elements.add(new Element(expr));
                } while (true);

            }
            case NUMBER: {
                return new LiteralNumber(token.getData());
            }
            case STRING_DOUBLE: {
                return new LiteralString(token.getData(), TokenKind.DOUBLE);
            }
        }
        return null;
    }

    private AST parseObjectRemainder(Queue<Token> tokens) {
        List<ObjectField> fields = new ArrayList<>();
        Token next = tokens.poll();
        do {
            if (next.getKind().equals(Kind.BRACE_R)) {
                return new ObjectElement(fields);
            }
            switch (next.getKind()) {
                case STRING_DOUBLE: {
                    ObjectField.Kind kind = null;
                    AST key = null;
                    if (next.getKind() == STRING_DOUBLE) {
                        kind = ObjectField.Kind.FIELD_STR;
                        key = new LiteralString(next.getData(), TokenKind.DOUBLE);
                    }
                    popExpect(tokens, Kind.OPERATOR);

                    AST body = parse(tokens);
                    next = tokens.poll();
                    fields.add(new ObjectField(kind, null, key, body));
                }
            }
        } while (true);
    }

    private Token popExpect(Queue<Token> tokens, Kind expected) {
        Token token = tokens.poll();
        if (token.getKind() != expected) {
            throw new RuntimeException("Expected token: " + expected + ", but got: " + token.getKind());
        }
        return token;
    }

}
