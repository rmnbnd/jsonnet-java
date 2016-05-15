package jsonnet.core.model.ast;

public class Element {

    private AST expr;

    public Element(AST expr) {
        this.expr = expr;
    }

    public AST getExpr() {
        return expr;
    }
}
