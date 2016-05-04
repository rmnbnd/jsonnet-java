package jsonnet.core;

import jsonnet.core.model.ast.AST;
import jsonnet.core.model.ast.Identifier;
import jsonnet.core.model.state.Field;
import jsonnet.core.model.state.Value;
import jsonnet.core.model.vm.FrameKind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frame {

    private FrameKind kind;
    private AST ast;
    private List<jsonnet.core.model.ast.Field> fit;
    private Map<Identifier, Field> objectFields = new HashMap<>();
    private Value val;

    public Frame(FrameKind frameKind, AST ast) {
        this.kind = frameKind;
        this.ast = ast;
    }

    public Frame(FrameKind frameKind) {
        this.kind = frameKind;
    }

    public FrameKind getKind() {
        return kind;
    }

    public AST getAst() {
        return ast;
    }

    public void setFit(List<jsonnet.core.model.ast.Field> fit) {
        this.fit = fit;
    }

    public List<jsonnet.core.model.ast.Field> getFit() {
        return fit;
    }

    public Map<Identifier, Field> getObjectFields() {
        return objectFields;
    }

    public void setVal(Value val) {
        this.val = val;
    }

    public Value getVal() {
        return val;
    }
}
