package jsonnet.core;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.vm.FrameKind;

public class Frame {

    private FrameKind kind;
    private AST ast;

    public Frame(FrameKind frameKind, AST ast) {
        this.kind = frameKind;
        this.ast = ast;
    }

    public FrameKind getKind() {
        return kind;
    }

    public AST getAst() {
        return ast;
    }
}
