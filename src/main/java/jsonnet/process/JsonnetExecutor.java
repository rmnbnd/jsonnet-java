package jsonnet.process;

import jsonnet.core.Desugarer;
import jsonnet.core.Interpreter;
import jsonnet.core.Parser;
import jsonnet.core.StaticAnalysis;
import jsonnet.core.model.ast.AST;

public class JsonnetExecutor {

    private Parser parser = new Parser();
    private Desugarer desugarer = new Desugarer();
    private StaticAnalysis staticAnalysis = new StaticAnalysis();
    private Interpreter interpreter = new Interpreter();

    public String jsonnetParse(String input) {
        AST ast = parser.doParse(input);
        AST desugaredAST = desugarer.desugarFile(ast);
        staticAnalysis.jsonnetStaticAnalysis(desugaredAST);
        return interpreter.evaluate(desugaredAST, 0);
    }

}
