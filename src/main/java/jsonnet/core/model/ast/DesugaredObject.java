package jsonnet.core.model.ast;

import java.util.List;

public class DesugaredObject extends AST {

    private List<Field> fields;
    private List<AST> asts;

    public DesugaredObject(List<Field> fields, List<AST> asts) {
        this.fields = fields;
        this.asts = asts;
    }

    public class Field {

    }

}
