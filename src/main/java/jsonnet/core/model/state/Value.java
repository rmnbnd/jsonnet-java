package jsonnet.core.model.state;

public class Value {

    private Type t;
    private ValueHeapObject v = new ValueHeapObject();

    public enum Type {
        ARRAY,
        OBJECT,
        STRING,
        DOUBLE
    }

    public class ValueHeapObject {
        private HeapEntity h;
        private double d;

        public HeapEntity getH() {
            return h;
        }

        public void setH(HeapEntity h) {
            this.h = h;
        }

        public double getD() {
            return d;
        }

        public void setD(double d) {
            this.d = d;
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
