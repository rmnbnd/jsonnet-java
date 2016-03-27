package jsonnet.process;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JsonnetExecutorTest {

    private JsonnetExecutor jsonnetExecutor = new JsonnetExecutor();

    @Test
    public void shouldReturnEmptyObject() {
        // given
        String input = getFileWithUtil("jsonnet/1.jsonnet");
        String expected = getFileWithUtil("json/1.json");

        // when
        String actual = jsonnetExecutor.jsonnetParse(input);

        // then
        assertEquals(expected, actual);
    }

    private String getFileWithUtil(String fileName) {
        String result = null;

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}