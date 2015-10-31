package jsonnet.core;

import jsonnet.core.model.Token;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LexerTest {

    Lexer lexer = new Lexer();

    @Test
    public void shouldGetEmptyTokensList() {
        // given
        String input = "";

        // when
        List<Token> tokens = lexer.lex(input);

        // then
        assertTrue(tokens.isEmpty());
    }

}