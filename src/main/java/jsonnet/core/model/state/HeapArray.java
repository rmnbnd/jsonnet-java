package jsonnet.core.model.state;

import java.util.List;

public class HeapArray extends HeapEntity {

    private List<HeapThunk> elements;

    public HeapArray(List<HeapThunk> elements) {
        this.elements = elements;
    }

    public List<HeapThunk> getElements() {
        return elements;
    }
}
