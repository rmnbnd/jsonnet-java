package jsonnet.process;

import jsonnet.core.Interpreter;
import jsonnet.core.model.ast.AST;

public class Executor {

    private Interpreter interpreter = new Interpreter();

    public String jsonnetExecute(AST ast) {
        interpreter.evaluate(ast, 0);

        return null;
    }

}
