package jsonnet.core;

import jsonnet.core.model.ast.*;

import java.util.ArrayList;
import java.util.List;

public class Desugarer {

    public void desugarFile(AST ast) {
        desugar(ast, 0);

        ast = new Local(ast);
    }

    private void desugar(AST ast, int objectLevel) {
        if (ast.getType() == ASTType.AST_OBJECT) {
            ObjectElement astObject = (ObjectElement)ast;
            if (objectLevel == 0) {
                Identifier identifier = new Identifier("$");
                AST body = new Self();
                astObject.getFields().add(ObjectField.getLocalField(identifier, body));
            }
            desugarFields(ast, astObject.getFields(), objectLevel);

            List<DesugaredObject.Field> newFields = new ArrayList<>();
            List<AST> newAsts = new ArrayList<>();

            ast = new DesugaredObject(newFields, newAsts);
        }
        else if (ast.getType() == ASTType.AST_SELF) {
            // Nothing to do.
        }
    }

    private void desugarFields(AST ast, List<ObjectField> fields, int objectLevel) {
        for (ObjectField field : fields) {
            desugar(field.getBody(), objectLevel + 1);
        }
    }

}
