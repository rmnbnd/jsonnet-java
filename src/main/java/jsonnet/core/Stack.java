package jsonnet.core;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.vm.FrameKind;

import java.util.List;

public class Stack {

    private List<Frame> stack;

    public List<Frame> getStack() {
        return stack;
    }

    public void newFrame(FrameKind frame, AST ast) {
        stack.add(new Frame(frame, ast));
    }
}
