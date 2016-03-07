package jsonnet.core.model.ast;

import static jsonnet.core.model.ast.ASTType.AST_OBJECT;

public class ObjectElement extends AST {

    public ObjectElement() {
        super(AST_OBJECT);
    }

    public ObjectElement(ASTType type) {
        super(type);
    }

}
