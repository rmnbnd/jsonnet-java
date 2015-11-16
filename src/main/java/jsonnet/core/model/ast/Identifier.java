package jsonnet.core.model.ast;

public class Identifier {

    private String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
