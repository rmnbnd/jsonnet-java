package jsonnet.core;

import jsonnet.core.model.Kind;
import jsonnet.core.model.Token;
import jsonnet.core.model.ast.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static jsonnet.core.model.ast.ASTType.AST_OBJECT;

@RunWith(JUnit4.class)
public class ParserTest extends TestCase {

    private Parser parser = new Parser();

    @Test
    public void shouldGetNotNullAST() {
        // given
        Token token1 = new Token(Kind.BRACE_L, "{");
        Token token2 = new Token(Kind.BRACE_R, "}");
        Queue<Token> tokens = new LinkedList<>();
        tokens.add(token1);
        tokens.add(token2);

        // when
        AST ast = parser.doParse(tokens);

        // then
        assertNotNull(ast);
    }

    @Test
    public void shouldGetASTTypeObject() {
        // given
        Token token1 = new Token(Kind.BRACE_L, "{");
        Token token2 = new Token(Kind.BRACE_R, "}");
        Queue<Token> tokens = new LinkedList<>();
        tokens.add(token1);
        tokens.add(token2);

        // when
        AST ast = parser.doParse(tokens);

        // then
        assertEquals("AST type should be a object", AST_OBJECT, ast.getType());
    }

    @Test
    public void shouldGetASTTypeObjectWithFields() {
        // given
        Token token1 = new Token(Kind.BRACE_L, "{");
        Token token2 = new Token(Kind.STRING_DOUBLE, "fieldKey");
        Token token3 = new Token(Kind.OPERATOR, ":");
        Token token4 = new Token(Kind.STRING_DOUBLE, "fieldValue");
        Token token5 = new Token(Kind.BRACE_R, "}");
        Queue<Token> tokens = new LinkedList<>();
        tokens.add(token1);
        tokens.add(token2);
        tokens.add(token3);
        tokens.add(token4);
        tokens.add(token5);

        // when
        ObjectElement ast = (ObjectElement) parser.doParse(tokens);

        // then
        assertEquals("AST type should be a object", AST_OBJECT, ast.getType());
        assertEquals(1, ast.getFields().size());
        ObjectField objectField = ast.getFields().get(0);
        assertEquals(ObjectField.Kind.FIELD_STR, objectField.getKind());
        assertEquals("fieldKey", ((LiteralString) objectField.getKey()).getValue());
        assertEquals(LiteralString.TokenKind.DOUBLE, ((LiteralString) objectField.getKey()).getTokenKind());
        assertEquals(ASTType.AST_LITERAL_STRING, objectField.getKey().getType());
        assertEquals("fieldValue", ((LiteralString) objectField.getBody()).getValue());
        assertEquals(LiteralString.TokenKind.DOUBLE, ((LiteralString) objectField.getBody()).getTokenKind());
        assertEquals(ASTType.AST_LITERAL_STRING, objectField.getBody().getType());
    }

    @Test
    public void shouldReturnASTStructureArray() {
        // given
        Token token1 = new Token(Kind.BRACE_L, "{");
        Token token2 = new Token(Kind.STRING_DOUBLE, "array");
        Token token3 = new Token(Kind.OPERATOR, ":");
        Token token4 = new Token(Kind.BRACKET_L, "[");
        Token token5 = new Token(Kind.NUMBER, "1");
        Token token6 = new Token(Kind.COMMA, ",");
        Token token7 = new Token(Kind.NUMBER, "2");
        Token token8 = new Token(Kind.COMMA, ",");
        Token token9 = new Token(Kind.NUMBER, "3");
        Token token10 = new Token(Kind.BRACKET_R, "]");
        Token token11 = new Token(Kind.BRACE_R, "}");
        Queue<Token> tokens = new LinkedList<>();
        tokens.add(token1);
        tokens.add(token2);
        tokens.add(token3);
        tokens.add(token4);
        tokens.add(token5);
        tokens.add(token6);
        tokens.add(token7);
        tokens.add(token8);
        tokens.add(token9);
        tokens.add(token10);
        tokens.add(token11);

        // when
        ObjectElement ast = (ObjectElement) parser.doParse(tokens);

        // then
        assertEquals("AST type should be a object", AST_OBJECT, ast.getType());
        assertEquals(1, ast.getFields().size());
        ObjectField objectField = ast.getFields().get(0);
        assertEquals(ObjectField.Kind.FIELD_STR, objectField.getKind());
        assertEquals("array", ((LiteralString) objectField.getKey()).getValue());
        assertEquals(LiteralString.TokenKind.DOUBLE, ((LiteralString) objectField.getKey()).getTokenKind());
        assertEquals(ASTType.AST_LITERAL_STRING, objectField.getKey().getType());
        Array array = (Array) objectField.getBody();
        assertEquals(ASTType.AST_ARRAY, array.getType());
        assertEquals(false, array.isTrailingComma());
        assertEquals(3, array.getElements().size());
        List<Element> elements = array.getElements();
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            LiteralNumber expr = (LiteralNumber) element.getExpr();
            assertEquals(i + 1, expr.getValue());
            assertEquals(String.valueOf(i + 1), expr.getOriginalString());
            assertEquals(ASTType.AST_LITERAL_NUMBER, expr.getType());
        }
    }

}