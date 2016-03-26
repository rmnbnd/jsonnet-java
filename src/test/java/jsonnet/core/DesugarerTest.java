package jsonnet.core;

import jsonnet.core.model.ast.ObjectElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class DesugarerTest {

    private Desugarer desugarer = new Desugarer();

    @Test
    public void shouldCreateOneField() throws Exception {
        // given
        ObjectElement ast = new ObjectElement();

        // when
        desugarer.desugarFile(ast);

        // then
        assertEquals(1, ast.getFields().size());
    }
}