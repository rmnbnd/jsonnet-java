package jsonnet.core;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.ast.ASTType;
import jsonnet.core.model.ast.Local;

public class StaticAnalysis {

    public void jsonnetStaticAnalysis(AST ast) {
        staticAnalysis(ast, false);
    }

    private void staticAnalysis(AST ast, boolean inObject) {
        if (ast.getType() == ASTType.AST_LOCAL) {
            Local localAst = (Local)ast;
            staticAnalysis(localAst.getBody(), inObject);
        }
    }

}
