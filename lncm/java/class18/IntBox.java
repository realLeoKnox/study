package class18;
/*
public class IntBox {
    private int anInt;

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public int getAnInt() {
        return anInt;
    }
}
*/

public class IntBox<T> {
    private T anInt;

    public void setAnInt(T anInt) {
        this.anInt = anInt;
    }

    public T getAnInt() {
        return anInt;
    }
}
