package class18;
/*
public class ObjBox {
    ObjBox a;

    public ObjBox(ObjBox a) {
        this.a = a;
    }

    public ObjBox getA() {
        return a;
    }

    public static void main(String[] args) {
        ObjBox objBox=new ObjBox();
    }
}
*/
public class ObjBox<T> {
    private T a;

    public ObjBox(T anInt) {
        this.a = a;
    }

    public T getAnInt() {
        return a;
    }

    public static void main(String[] args) {
        ObjBox <String>objBox=new ObjBox<>();
    }
}