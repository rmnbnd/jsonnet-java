package jsonnet.core;

import jsonnet.core.model.ast.*;
import jsonnet.core.model.ast.Field;
import jsonnet.core.model.state.*;
import jsonnet.core.model.vm.FrameKind;

import java.util.*;

import static jsonnet.core.model.state.Value.Type.OBJECT;
import static jsonnet.core.model.state.Value.Type.STRING;

public class Interpreter {

    private Value scratch = new Value();
    private Stack stack = new Stack();

    private HeapObject self;

    public String evaluate(AST ast, int initialStackSize) {
        populateStack(ast, initialStackSize);
        processStack(initialStackSize);
        return manifestJson();
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
                    scratch = makeObject(new HeapSimpleObject(new HashMap<Identifier, jsonnet.core.model.state.Field>()));
                } else {
                    stack.newFrame(FrameKind.FRAME_OBJECT, ast);
                    List<Field> fit = desugaredObject.getFields();
                    stack.getStack().peek().setFit(fit);
                    ast = fit.get(0).getName();
                    evaluate(ast, initialStackSize);
                }
                break;
            }
            case AST_LITERAL_STRING: {
                LiteralString literalString = (LiteralString) ast;
                scratch = makeString(literalString.getValue());
                break;
            }
        }
    }

    private void processStack(int initialStackSize) {
        while (stack.getStack().size() > initialStackSize) {
            Frame frame = stack.getStack().peek();
            switch (frame.getKind()) {
                case FRAME_LOCAL: {
                    break;
                }
                case FRAME_OBJECT: {
                    DesugaredObject desugaredObject = (DesugaredObject) frame.getAst();
                    if (scratch.getT() != null) {
                        if (scratch.getT() != Value.Type.STRING) {
                            throw new RuntimeException("Field name was not a string.");
                        }
                        String fname = ((HeapString) scratch.getV().getH()).getValue();
                        Identifier fid = new Identifier(fname);
                        frame.getObjectFields().put(fid, new jsonnet.core.model.state.Field(frame.getFit().get(0).getBody()));
                    }
                    frame.getFit().remove(0);

                    scratch = makeObject(new HeapSimpleObject(frame.getObjectFields()));
                }
            }
            stack.getStack().pop();
        }
    }

    private String manifestJson() {
        StringBuilder result = new StringBuilder();
        switch (scratch.getT()) {
            case OBJECT: {
                HeapObject heapObject = (HeapObject) scratch.getV().getH();
                Map<String, Identifier> fields = new HashMap<>();
                for (Identifier field : objectFields(heapObject)) {
                    fields.put(field.getName(), field);
                }
                if (fields.isEmpty()) {
                    result.append("{}");
                } else {
                    // TODO: implement the parsing to string
                    String prefix = "{";
                    for (Map.Entry<String, Identifier> field : fields.entrySet()) {
                        AST body = objectIndex(heapObject, field.getValue(), 0);
                        stack.getStack().peek().setVal(scratch);
                        populateStack(body, stack.getStack().size());
                        processStack(stack.getStack().size());
                        String vstr = manifestJson();
                        scratch = stack.getStack().peek().getVal();
                        stack.getStack().pop();
                        result.append(prefix).append(field.getKey()).append(":").append(vstr);
                    }
                    result.append("}");
                }
                break;
            }
            case STRING: {
                String str = ((HeapString) scratch.getV().getH()).getValue();
                result.append(str);
                break;
            }
        }
        return result.toString();
    }

    private AST objectIndex(HeapObject heapObject, Identifier value, int offset) {
        int foundAt = 0;
        self = null;
        HeapLeafObject found = findObject(value, heapObject, heapObject, offset, foundAt);
        if (found instanceof HeapSimpleObject) {
            HeapSimpleObject simp = (HeapSimpleObject) found;
            jsonnet.core.model.state.Field it = simp.getFields().get(value);
            AST body = it.getBody();
            stack.newCall();
            return body;
        }
        return null;
    }

    private HeapLeafObject findObject(Identifier value, HeapObject root, HeapObject curr,
                                      int startFrom, int counter) {
        if (counter >= startFrom) {
            if (curr instanceof HeapSimpleObject) {
                HeapSimpleObject simp = (HeapSimpleObject) curr;
                jsonnet.core.model.state.Field it = simp.getFields().get(value);
                self = root;
                return simp;
            }
        }
        return null;
    }

    private Set<Identifier> objectFields(HeapObject heapObject) {
        int counter = 0;
        Set<Identifier> r = new HashSet<>();
        for (Map.Entry<Identifier, ObjectField.Hide> field : objectFields(heapObject, counter).entrySet()) {
            r.add(field.getKey());
        }
        return r;
    }

    private Map<Identifier, ObjectField.Hide> objectFields(HeapObject heapObject, int counter) {
        Map<Identifier, ObjectField.Hide> map = new HashMap<>();
        if (heapObject instanceof HeapSimpleObject) {
            HeapSimpleObject simpleObject = (HeapSimpleObject) heapObject;
            counter++;
            for (Map.Entry<Identifier, jsonnet.core.model.state.Field> field : simpleObject.getFields().entrySet()) {
                map.put(field.getKey(), ObjectField.Hide.VISIBLE);
            }
        }
        return map;
    }

    private Value makeObject(HeapSimpleObject heapSimpleObject) {
        Value r = new Value();
        r.setT(OBJECT);
        r.getV().setH(heapSimpleObject);
        return r;
    }

    private Value makeString(String value) {
        Value r = new Value();
        r.setT(STRING);
        r.getV().setH(new HeapString(value));
        return r;
    }
}
