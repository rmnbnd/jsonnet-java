package jsonnet.core;

import jsonnet.core.model.ast.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class InterpreterTest {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Interpreter interpreter = new Interpreter();

    @Test
    public void testEvaluate() throws Exception {
        // given
        List<Field> fields = new ArrayList<>();
        List<AST> asts = new ArrayList<>();
        DesugaredObject object = new DesugaredObject(fields, asts);
        Local ast = new Local(object);

        // when
        String json = interpreter.evaluate(ast, 0);

        // then
        assertEquals("{}", json);
    }

    @Test
    public void shouldReturnObjectWithField() throws Exception {
        // given
        LiteralString key = new LiteralString("fieldKey", LiteralString.TokenKind.DOUBLE);
        LiteralString value = new LiteralString("fieldValue", LiteralString.TokenKind.DOUBLE);
        Local body = new Local(value);
        Field field = new Field(key, body);
        List<Field> fields = new ArrayList<>();
        fields.add(field);
        List<AST> asts = new ArrayList<>();
        DesugaredObject object = new DesugaredObject(fields, asts);
        Local ast = new Local(object);

        // when
        String json = interpreter.evaluate(ast, 0);

        // then
        assertEquals("{" + LINE_SEPARATOR + "  \"fieldKey\": \"fieldValue\"" + LINE_SEPARATOR + "}", json);
    }

    @Test
    public void shouldReturnObjectWithArray() throws Exception {
        // given
        LiteralString key = new LiteralString("array", LiteralString.TokenKind.DOUBLE);
        List<Element> elements = new ArrayList<>();
        elements.add(new Element(new LiteralNumber("1")));
        elements.add(new Element(new LiteralNumber("2")));
        elements.add(new Element(new LiteralNumber("3")));
        Array value = new Array(elements, false);
        Local body = new Local(value);
        Field field = new Field(key, body);
        List<Field> fields = new ArrayList<>();
        fields.add(field);
        List<AST> asts = new ArrayList<>();
        DesugaredObject object = new DesugaredObject(fields, asts);
        Local ast = new Local(object);

        // when
        String json = interpreter.evaluate(ast, 0);

        // then
        assertEquals("{" + LINE_SEPARATOR +
                "  \"array\": [" + LINE_SEPARATOR +
                "    1," + LINE_SEPARATOR +
                "    2," + LINE_SEPARATOR +
                "    3" + LINE_SEPARATOR +
                "  ]" + LINE_SEPARATOR +
                "}", json);
    }
}