package jsonnet.core.model.ast;

import static jsonnet.core.model.ast.ASTType.AST_LITERAL_STRING;

public class LiteralString extends AST {

    private String value;
    private TokenKind tokenKind;

    public enum TokenKind {
        DOUBLE
    }

    public LiteralString(String value, TokenKind tokenKind) {
        super(AST_LITERAL_STRING);
        this.value = value;
        this.tokenKind = tokenKind;
    }

    public String getValue() {
        return value;
    }

    public TokenKind getTokenKind() {
        return tokenKind;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setTokenKind(TokenKind tokenKind) {
        this.tokenKind = tokenKind;
    }
}
