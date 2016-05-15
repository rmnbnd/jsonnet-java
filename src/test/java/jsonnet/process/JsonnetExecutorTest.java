package jsonnet.process;

import jsonnet.utils.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonnetExecutorTest {

    private JsonnetExecutor jsonnetExecutor = new JsonnetExecutor();
    private FileUtils fileUtils = new FileUtils();

    @Test
    public void shouldReturnEmptyObject() {
        // given
        String input = fileUtils.getFileWithUtil("jsonnet/1.jsonnet");
        String expected = fileUtils.getFileWithUtil("json/1.json");

        // when
        String actual = jsonnetExecutor.jsonnetParse(input);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnObjectWithOneField() {
        // given
        String input = fileUtils.getFileWithUtil("jsonnet/2.jsonnet");
        String expected = fileUtils.getFileWithUtil("json/2.json");

        // when
        String actual = jsonnetExecutor.jsonnetParse(input);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnObjectWithArray() {
        // given
        String input = fileUtils.getFileWithUtil("jsonnet/3.jsonnet");
        String expected = fileUtils.getFileWithUtil("json/3.json");

        // when
        String actual = jsonnetExecutor.jsonnetParse(input);

        // then
        assertEquals(expected, actual);
    }

}