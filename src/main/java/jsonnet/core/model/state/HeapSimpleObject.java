package jsonnet.core.model.state;

import jsonnet.core.model.ast.Identifier;

import java.util.HashMap;
import java.util.Map;

public class HeapSimpleObject extends HeapLeafObject {

    private Map<Identifier, Field> fields = new HashMap<>();

    public HeapSimpleObject(Map<Identifier, Field> fields) {
        this.fields = fields;
    }

    public Map<Identifier, Field> getFields() {
        return fields;
    }
}
