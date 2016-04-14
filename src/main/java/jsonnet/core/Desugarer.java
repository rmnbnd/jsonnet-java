package jsonnet.core;

import jsonnet.core.model.ast.*;

import java.util.ArrayList;
import java.util.List;

public class Desugarer {

    private AST astRef;

    public AST desugarFile(AST ast) {

        desugar(ast, 0);

        return new Local(astRef);
    }

    private void desugar(AST ast, int objectLevel) {
        if (ast.getType() == ASTType.AST_OBJECT) {
            ObjectElement astObject = (ObjectElement) ast;
            if (objectLevel == 0) {
                Identifier identifier = new Identifier("$");
                AST body = new Self();
                astObject.getFields().add(ObjectField.getLocalField(identifier, body));
            }
            desugarFields(ast, astObject.getFields(), objectLevel);

            List<Field> newFields = new ArrayList<>();
            List<AST> newAsts = new ArrayList<>();
            for (ObjectField field : astObject.getFields()) {
                if (field.getKind() == ObjectField.Kind.FIELD_EXPR) {
                    Field newField = new Field(field.getKey(), field.getBody());
                    newFields.add(newField);
                }
            }
            astRef = new DesugaredObject(newFields, newAsts);
        } else if (ast.getType() == ASTType.AST_LITERAL_STRING) {
            // Nothing to do.
        } else if (ast.getType() == ASTType.AST_SELF) {
            // Nothing to do.
        }
    }

    private void desugarFields(AST ast, List<ObjectField> fields, int objectLevel) {
        for (ObjectField field : fields) {
            if (field.getKey() != null) {
                desugar(field.getKey(), objectLevel);
            }
            desugar(field.getBody(), objectLevel + 1);
        }

        List<ObjectField> desugarFields = new ArrayList<>();
        List<Bind> binds = new ArrayList<>();
        for (ObjectField field : fields) {
            if (field.getKind() != ObjectField.Kind.LOCAL) {
                continue;
            }
            Bind bind = new Bind(field.getId(), field.getBody());
            binds.add(bind);
        }
        for (ObjectField field : fields) {
            if (field.getKind() == ObjectField.Kind.LOCAL) {
                continue;
            }
            if (!binds.isEmpty()) {
                field.setBody(new Local(field.getBody()));
            }
            desugarFields.add(field);
        }
        for (ObjectField desugarField : desugarFields) {
            switch (desugarField.getKind()) {
                case FIELD_STR: {
                    desugarField.setKind(ObjectField.Kind.FIELD_EXPR);
                }
            }
        }
        fields = desugarFields;
    }

}
