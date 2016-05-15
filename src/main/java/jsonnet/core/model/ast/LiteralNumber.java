package jsonnet.core.model.ast;

public class LiteralNumber extends AST {

    private int value;
    private String originalString;

    public LiteralNumber(String string) {
        super(ASTType.AST_LITERAL_NUMBER);
        this.value = Integer.parseInt(string);
        this.originalString = string;
    }

    public int getValue() {
        return value;
    }

    public String getOriginalString() {
        return originalString;
    }
}
