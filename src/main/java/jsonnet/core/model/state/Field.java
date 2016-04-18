package jsonnet.core.model.state;

import jsonnet.core.model.ast.AST;

public class Field {

    private AST body;

    public Field(AST body) {
        this.body = body;
    }

    public AST getBody() {
        return body;
    }
}
