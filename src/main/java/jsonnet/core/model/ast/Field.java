package jsonnet.core.model.ast;

public class Field {

    private AST name;
    private AST body;

    public Field(AST name, AST body) {
        this.name = name;
        this.body = body;
    }

    public AST getName() {
        return name;
    }

    public AST getBody() {
        return body;
    }
}
