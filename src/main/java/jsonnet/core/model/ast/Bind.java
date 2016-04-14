package jsonnet.core.model.ast;

public class Bind {

    private Identifier var;
    private AST body;

    public Bind(Identifier id, AST body) {
        this.var = id;
        this.body = body;
    }

    public Identifier getVar() {
        return var;
    }

    public AST getBody() {
        return body;
    }
}
