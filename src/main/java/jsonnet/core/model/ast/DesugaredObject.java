package jsonnet.core.model.ast;

import java.util.List;

public class DesugaredObject extends AST {

    private List<Field> fields;
    private List<AST> asts;

    public DesugaredObject(List<Field> fields, List<AST> asts) {
        super(ASTType.AST_DESUGARED_OBJECT);
        this.fields = fields;
        this.asts = asts;
    }

    public class Field {

    }

    public List<Field> getFields() {
        return fields;
    }

    public List<AST> getAsts() {
        return asts;
    }
}
