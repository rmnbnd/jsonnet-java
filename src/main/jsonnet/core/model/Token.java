package jsonnet.core.model;

public class Token {

    private Kind kind;
    private String data;

    public Token(Kind kind, String data) {
        this.kind = kind;
        this.data = data;
    }

    public Kind getKind() {
        return kind;
    }

    public String getData() {
        return data;
    }

}
