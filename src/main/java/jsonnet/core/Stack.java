package jsonnet.core;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.vm.FrameKind;

public class Stack {

    private java.util.Stack<Frame> stack = new java.util.Stack<>();

    public void newFrame(FrameKind frame, AST ast) {
        stack.push(new Frame(frame, ast));
    }

    public java.util.Stack<Frame> getStack() {
        return stack;
    }
}
