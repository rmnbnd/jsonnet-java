package jsonnet.core.model;

public class AST {

    private ASTType type;

    public AST(ASTType type) {
        this.type = type;
    }

    public ASTType getType() {
        return type;
    }
}
