package jsonnet.core;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.ast.DesugaredObject;
import jsonnet.core.model.ast.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class InterpreterTest {

    private Interpreter interpreter = new Interpreter();

    @Test
    public void testEvaluate() throws Exception {
        // given
        List<DesugaredObject.Field> fields = new ArrayList<>();
        List<AST> asts = new ArrayList<>();
        DesugaredObject object = new DesugaredObject(fields, asts);
        Local ast = new Local(object);

        // when
        String json = interpreter.evaluate(ast, 0);

        // then
        assertEquals("{}", json);
    }
}