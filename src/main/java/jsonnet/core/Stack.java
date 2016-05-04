package jsonnet.core;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.vm.FrameKind;

public class Stack {

    private int calls;

    private java.util.Stack<Frame> stack = new java.util.Stack<>();

    public Stack() {
        this.calls = 0;
    }

    public void newFrame(FrameKind frame, AST ast) {
        stack.push(new Frame(frame, ast));
    }

    public java.util.Stack<Frame> getStack() {
        return stack;
    }

    public void newCall() {
        tailCallTrimStack();
        stack.push(new Frame(FrameKind.FRAME_CALL));
        calls++;
    }

    private void tailCallTrimStack() {
        for (int i = stack.size() - 1; i >= 0; --i) {
            switch (stack.get(i).getKind()) {
                case FRAME_LOCAL: {
                    break;
                }
                default: {
                    return;
                }
            }
        }
    }
}
