package jsonnet.process;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class ExecutorTest {

    private Executor executor = new Executor();

    @Test
    public void shouldReturnNull() {
        // when
        String json = executor.jsonnetExecute();

        // then
        assertNull(json);
    }

}