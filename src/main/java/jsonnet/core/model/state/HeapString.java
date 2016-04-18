package jsonnet.core.model.state;

public class HeapString extends HeapEntity {

    private String value;

    public HeapString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
