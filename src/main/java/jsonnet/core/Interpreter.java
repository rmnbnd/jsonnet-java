package jsonnet.core;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.ast.Local;
import jsonnet.core.model.vm.FrameKind;

public class Interpreter {

    private Stack stack;

    public void evaluate(AST ast, int initialStackSize) {
        recurse:

        switch (ast.getType()) {
            case AST_LOCAL: {
                Local localObject = (Local)ast;
                stack.newFrame(FrameKind.FRAME_LOCAL, ast);
                ast = localObject.getBody();
                break recurse;
            }
        }
    }
}
