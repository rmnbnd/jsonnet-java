package jsonnet.core.model.state;

public class Value {

    private Type t;
    private ValueHeapObject v = new ValueHeapObject();

    public enum Type {
        OBJECT
    }

    public class ValueHeapObject {
        private HeapEntity h;

        public HeapEntity getH() {
            return h;
        }

        public void setH(HeapEntity h) {
            this.h = h;
        }
    }

    public void setT(Type t) {
        this.t = t;
    }

    public Type getT() {
        return t;
    }

    public ValueHeapObject getV() {
        return v;
    }

}
