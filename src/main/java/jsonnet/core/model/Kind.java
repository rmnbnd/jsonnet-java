package jsonnet.core.model;

public enum Kind {
    // symbols
    BRACE_L,
    BRACE_R,
    BRACKET_L,
    BRACKET_R,
    COMMA,
    DOT,
    SEMICOLON,

    // Arbitrary length lexemes
    OPERATOR,
    NUMBER,
    STRING_DOUBLE
}
