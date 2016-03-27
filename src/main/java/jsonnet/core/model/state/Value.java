package jsonnet.core.model.state;

public class Value {

    private Type t;

    public enum Type {
        OBJECT
    }

    public void setT(Type t) {
        this.t = t;
    }

    public Type getT() {
        return t;
    }
}
