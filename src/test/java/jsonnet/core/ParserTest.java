package jsonnet.core;

import jsonnet.core.model.ast.AST;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static jsonnet.core.model.ast.ASTType.AST_OBJECT;

@RunWith(JUnit4.class)
public class ParserTest extends TestCase {

    private Parser parser = new Parser();

    @Test
    public void shouldGetNotNullAST() {
        // given
        String input = "{}";

        // when
        AST ast = parser.doParse(input);

        // then
        assertNotNull(ast);
    }

    @Test
    public void shouldGetASTTypeObject() {
        // given
        String input = "{}";

        // when
        AST ast = parser.doParse(input);

        // then
        assertEquals("AST type should be a object", AST_OBJECT, ast.getType());
    }

}