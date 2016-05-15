package jsonnet.core;

import jsonnet.core.model.Kind;
import jsonnet.core.model.Token;
import jsonnet.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class LexerTest {

    private Lexer lexer = new Lexer();
    private FileUtils fileUtils = new FileUtils();

    @Test
    public void shouldGetEmptyTokensList() {
        // given
        String input = "";

        // when
        Queue<Token> tokens = lexer.lex(input);

        // then
        assertTrue(tokens.isEmpty());
    }

    @Test
    public void shouldGetBracesTokens() {
        // given
        String input = fileUtils.getFileWithUtil("jsonnet/1.jsonnet");

        // when
        Queue<Token> tokens = lexer.lex(input);

        // then
        assertTrue(tokens.size() == 2);
    }

    @Test
    public void shouldGetBracesDataTokens() {
        // given
        String input = fileUtils.getFileWithUtil("jsonnet/1.jsonnet");

        // when
        Queue<Token> tokens = lexer.lex(input);

        // then
        assertEquals("{", tokens.poll().getData());
        assertEquals("}", tokens.poll().getData());
    }

    @Test
    public void shouldGetBracesKindTokens() {
        // given
        String input = fileUtils.getFileWithUtil("jsonnet/1.jsonnet");

        // when
        Queue<Token> tokens = lexer.lex(input);

        // then
        assertEquals(Kind.BRACE_L, tokens.poll().getKind());
        assertEquals(Kind.BRACE_R, tokens.poll().getKind());
    }

    @Test
    public void shouldReturnFourTokens() {
        // given
        String input = fileUtils.getFileWithUtil("jsonnet/2.jsonnet");

        // when
        Queue<Token> tokens = lexer.lex(input);

        // then
        assertEquals(5, tokens.size());
    }

    @Test
    public void shouldReturnFieldNameAndValue() {
        // given
        String input = fileUtils.getFileWithUtil("jsonnet/2.jsonnet");

        // when
        Queue<Token> tokens = lexer.lex(input);

        // then
        assertEquals("{", tokens.poll().getData());
        assertEquals("fieldKey", tokens.poll().getData());
        assertEquals(":", tokens.poll().getData());
        assertEquals("fieldValue", tokens.poll().getData());
        assertEquals("}", tokens.poll().getData());
    }

    @Test
    public void shouldReturnKindStringDoubleForFieldNameAndValue() {
        // given
        String input = fileUtils.getFileWithUtil("jsonnet/2.jsonnet");

        // when
        Queue<Token> tokens = lexer.lex(input);

        // then
        assertEquals(Kind.BRACE_L, tokens.poll().getKind());
        assertEquals(Kind.STRING_DOUBLE, tokens.poll().getKind());
        assertEquals(Kind.OPERATOR, tokens.poll().getKind());
        assertEquals(Kind.STRING_DOUBLE, tokens.poll().getKind());
        assertEquals(Kind.BRACE_R, tokens.poll().getKind());
    }

    @Test
    public void shouldReturnStructureOfArrayKinds() {
        // given
        String input = fileUtils.getFileWithUtil("jsonnet/3.jsonnet");

        // when
        Queue<Token> tokens = lexer.lex(input);

        // then
        assertEquals(Kind.BRACE_L, tokens.poll().getKind());
        assertEquals(Kind.STRING_DOUBLE, tokens.poll().getKind());
        assertEquals(Kind.OPERATOR, tokens.poll().getKind());
        assertEquals(Kind.BRACKET_L, tokens.poll().getKind());
        assertEquals(Kind.NUMBER, tokens.poll().getKind());
        assertEquals(Kind.COMMA, tokens.poll().getKind());
        assertEquals(Kind.NUMBER, tokens.poll().getKind());
        assertEquals(Kind.COMMA, tokens.poll().getKind());
        assertEquals(Kind.NUMBER, tokens.poll().getKind());
        assertEquals(Kind.BRACKET_R, tokens.poll().getKind());
        assertEquals(Kind.BRACE_R, tokens.poll().getKind());
    }

    @Test
    public void shouldReturnStructureOfArrayValues() {
        // given
        String input = fileUtils.getFileWithUtil("jsonnet/3.jsonnet");

        // when
        Queue<Token> tokens = lexer.lex(input);

        // then
        assertEquals("{", tokens.poll().getData());
        assertEquals("array", tokens.poll().getData());
        assertEquals(":", tokens.poll().getData());
        assertEquals("[", tokens.poll().getData());
        assertEquals("1", tokens.poll().getData());
        assertEquals(",", tokens.poll().getData());
        assertEquals("2", tokens.poll().getData());
        assertEquals(",", tokens.poll().getData());
        assertEquals("3", tokens.poll().getData());
        assertEquals("]", tokens.poll().getData());
        assertEquals("}", tokens.poll().getData());
    }

}