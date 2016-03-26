package jsonnet.core.model.ast;

public class Local extends AST {

    private AST body;

    public Local(AST ast) {
        super(ASTType.AST_LOCAL);
        this.body = ast;
    }

    public AST getBody() {
        return body;
    }
}
