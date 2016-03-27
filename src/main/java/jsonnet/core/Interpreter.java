package jsonnet.core;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.ast.DesugaredObject;
import jsonnet.core.model.ast.Local;
import jsonnet.core.model.state.HeapSimpleObject;
import jsonnet.core.model.state.Value;
import jsonnet.core.model.vm.FrameKind;

public class Interpreter {

    private Value sctrach = new Value();
    private Stack stack = new Stack();

    public void evaluate(AST ast, int initialStackSize) {
        populateStack(ast, initialStackSize);
        processStack(initialStackSize);
    }

    private void processStack(int initialStackSize) {
        while (stack.getStack().size() > initialStackSize) {
            Frame frame = stack.getStack().peek();
            switch (frame.getKind()) {
                case FRAME_LOCAL: {
                    break;
                }
            }
            stack.getStack().pop();
        }
    }

    private void populateStack(AST ast, int initialStackSize) {
        switch (ast.getType()) {
            case AST_LOCAL: {
                Local localObject = (Local) ast;
                stack.newFrame(FrameKind.FRAME_LOCAL, ast);
                ast = localObject.getBody();
                evaluate(ast, initialStackSize);
                break;
            }
            case AST_DESUGARED_OBJECT: {
                DesugaredObject desugaredObject = (DesugaredObject) ast;
                if (desugaredObject.getFields().isEmpty()) {
                    sctrach = makeObject(new HeapSimpleObject());
                }
            }
        }
    }

    private Value makeObject(HeapSimpleObject heapSimpleObject) {
        Value r = new Value();
        r.setT(Value.Type.OBJECT);
        return r;
    }
}
