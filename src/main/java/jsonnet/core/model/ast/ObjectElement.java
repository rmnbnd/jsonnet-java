package jsonnet.core.model.ast;

import java.util.ArrayList;
import java.util.List;

import static jsonnet.core.model.ast.ASTType.AST_OBJECT;

public class ObjectElement extends AST {

    private List<ObjectField> fields = new ArrayList<>();

    public ObjectElement() {
        super(AST_OBJECT);
    }

    public ObjectElement(ASTType type) {
        super(type);
    }

    public List<ObjectField> getFields() {
        return fields;
    }
}
