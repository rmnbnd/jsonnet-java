package jsonnet.core.model.ast;

public class ObjectField {

    private Kind kind;
    private Identifier id;
    private AST key;
    private AST body;

    public enum Kind {
        FIELD_EXPR,
        FIELD_STR,
        LOCAL
    }

    public ObjectField(Kind kind, Identifier identifier, AST body) {
        this.kind = kind;
        this.id = identifier;
        this.body = body;
    }

    public ObjectField(Kind kind, Identifier identifier, AST key, AST body) {
        this.kind = kind;
        this.id = identifier;
        this.key = key;
        this.body = body;
    }

    public static ObjectField getLocalField(Identifier identifier, AST body) {
        return new ObjectField(Kind.LOCAL, identifier, body);
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Identifier getId() {
        return id;
    }

    public AST getKey() {
        return key;
    }

    public AST getBody() {
        return body;
    }

    public void setBody(AST body) {
        this.body = body;
    }
}
