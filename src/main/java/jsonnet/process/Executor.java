package jsonnet.process;

import jsonnet.core.Interpreter;

public class Executor {

    private Interpreter interpreter = new Interpreter();

    public String jsonnetExecute() {
        interpreter.evaluate();

        return null;
    }

}
