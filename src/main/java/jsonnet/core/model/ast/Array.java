package jsonnet.core.model.ast;

import java.util.List;

public class Array extends AST {

    private List<Element> elements;
    private boolean trailingComma;

    public Array(List<Element> elements, boolean trailingComma) {
        super(ASTType.AST_ARRAY);
        this.elements = elements;
        this.trailingComma = trailingComma;
    }

    public List<Element> getElements() {
        return elements;
    }

    public boolean isTrailingComma() {
        return trailingComma;
    }
}
