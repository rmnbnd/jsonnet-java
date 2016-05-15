package jsonnet.utils;

public final class StringUtils {

    public static String jsonnetStringParse(String string, boolean single) {
        return (single ? "\'" : "\"") +
                jsonnetStringEscape(string) +
                (single ? "\'" : "\"");
    }

    public static String jsonnetStringEscape(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            switch (c) {
                default: {
                    stringBuilder.append(c);
                }
            }
        }
        return stringBuilder.toString();
    }

}
