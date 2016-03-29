package jsonnet.process;

import jsonnet.core.Desugarer;
import jsonnet.core.Interpreter;
import jsonnet.core.Parser;
import jsonnet.core.Lexer;
import jsonnet.core.model.Token;
import jsonnet.core.StaticAnalysis;
import jsonnet.core.model.ast.AST;

import java.util.Queue;

public class JsonnetExecutor {

    private Lexer lexer = new Lexer();
    private Parser parser = new Parser();
    private Desugarer desugarer = new Desugarer();
    private StaticAnalysis staticAnalysis = new StaticAnalysis();
    private Interpreter interpreter = new Interpreter();

    public String jsonnetParse(String input) {
        Queue<Token> tokens = lexer.lex(input);
        AST ast = parser.doParse(tokens);
        AST desugaredAST = desugarer.desugarFile(ast);
        staticAnalysis.jsonnetStaticAnalysis(desugaredAST);
        return interpreter.evaluate(desugaredAST, 0);
    }

}
