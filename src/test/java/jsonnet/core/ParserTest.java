package jsonnet.core;

import jsonnet.core.model.ast.AST;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ParserTest extends TestCase {

    private Parser parser = new Parser();

    @Test
    public void shouldGetAST() {
        // given
        String input = "{}";

        // when
        AST ast = parser.doParse(input);

        // then
        assertNotNull(ast);
    }
}