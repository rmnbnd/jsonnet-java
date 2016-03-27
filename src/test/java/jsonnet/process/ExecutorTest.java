package jsonnet.process;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.ast.DesugaredObject;
import jsonnet.core.model.ast.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class ExecutorTest {

    private Executor executor = new Executor();

    @Test
    public void shouldReturnNull() {
        // given
        List<DesugaredObject.Field> fields = new ArrayList<>();
        List<AST> asts = new ArrayList<>();
        DesugaredObject object = new DesugaredObject(fields, asts);
        Local ast = new Local(object);

        // when
        String json = executor.jsonnetExecute(ast);

        // then
        assertNull(json);
    }

}