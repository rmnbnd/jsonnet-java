package jsonnet.core.model.state;

import jsonnet.core.model.ast.AST;

public class HeapThunk extends HeapEntity {

    private AST body;

    public HeapThunk(AST body) {
        this.body = body;
    }

    public AST getBody() {
        return body;
    }
}
