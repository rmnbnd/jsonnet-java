package jsonnet.core;

import jsonnet.core.model.ast.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static jsonnet.core.model.ast.ASTType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class DesugarerTest {

    private Desugarer desugarer = new Desugarer();

    @Test
    public void shouldCreateFieldsFromObject() {
        // given
        List<ObjectField> fields = new ArrayList<>();
        LiteralString key = new LiteralString("fieldKey", LiteralString.TokenKind.DOUBLE);
        LiteralString body = new LiteralString("fieldValue", LiteralString.TokenKind.DOUBLE);
        ObjectField field = new ObjectField(ObjectField.Kind.FIELD_STR, null, key, body);
        fields.add(field);
        ObjectElement ast = new ObjectElement(fields);

        // when
        Local actualAST = (Local) desugarer.desugarFile(ast);

        // then
        assertEquals(AST_LOCAL, actualAST.getType());

        DesugaredObject actualASTBody = (DesugaredObject) actualAST.getBody();
        assertEquals(AST_DESUGARED_OBJECT, actualASTBody.getType());
        assertEquals(1, actualASTBody.getFields().size());

        Field actualASTField = actualASTBody.getFields().get(0);

        LiteralString actualASTFieldName = (LiteralString) actualASTField.getName();
        assertEquals("fieldKey", actualASTFieldName.getValue());
        assertEquals(LiteralString.TokenKind.DOUBLE, actualASTFieldName.getTokenKind());
        assertEquals(AST_LITERAL_STRING, actualASTFieldName.getType());

        Local actualASTFieldBody = (Local) actualASTField.getBody();
        assertEquals(AST_LOCAL, actualASTFieldBody.getType());

        LiteralString actualASTFieldStringBody = (LiteralString) actualASTFieldBody.getBody();
        assertEquals("fieldValue", actualASTFieldStringBody.getValue());
        assertEquals(LiteralString.TokenKind.DOUBLE, actualASTFieldStringBody.getTokenKind());
        assertEquals(AST_LITERAL_STRING, actualASTFieldStringBody.getType());
    }
}