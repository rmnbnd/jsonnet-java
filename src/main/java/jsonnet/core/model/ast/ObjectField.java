package jsonnet.core.model.ast;

public class ObjectField {

    private Kind kind;
    private Identifier id;
    private AST body;

    enum Kind {
        LOCAL
    }

    public ObjectField(Kind kind, Identifier identifier, AST body) {
        this.kind = kind;
        this.id = identifier;
        this.body = body;
    }

    public static ObjectField getLocalField(Identifier identifier, AST body) {
        return new ObjectField(Kind.LOCAL, identifier, body);
    }

    public Kind getKind() {
        return kind;
    }

    public Identifier getId() {
        return id;
    }

    public AST getBody() {
        return body;
    }
}
