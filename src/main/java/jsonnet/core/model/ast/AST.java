package jsonnet.core.model.ast;

public class AST {

    private ASTType type;

    public AST() {
    }

    public AST(ASTType type) {
        this.type = type;
    }

    public ASTType getType() {
        return type;
    }
}
