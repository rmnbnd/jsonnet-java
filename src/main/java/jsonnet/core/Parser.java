package jsonnet.core;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.Kind;
import jsonnet.core.model.Token;
import jsonnet.core.model.ast.LiteralString;
import jsonnet.core.model.ast.ObjectElement;
import jsonnet.core.model.ast.ObjectField;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
                    AST body = parse(tokens);
                    next = tokens.poll();
                    fields.add(new ObjectField(kind, null, key, body));
                }
            }
        } while (true);
    }

}
