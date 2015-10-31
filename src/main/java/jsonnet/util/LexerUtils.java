package jsonnet.util;

public final class LexerUtils {

    public static boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isSymbol(char c) {
        switch (c) {
            case '&':
            case '|':
            case '^':
            case '=':
            case '<':
            case '>':
            case '*':
            case '/':
            case '%':
            case '#':
                return true;
        }
        return false;
    }

    private LexerUtils() {
    }

}
