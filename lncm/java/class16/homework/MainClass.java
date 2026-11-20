package class16.homework;
//梁敏健 2608090202027

//Geometry.java
abstract class Geometry {
    public abstract double getArea();
}

//TotalArea.java
class TotalArea {
    Geometry[] tuxing;
    double totalArea = 0;

    public void setTuxing(Geometry[] t) {
        tuxing = t;
    }

    public double computerTotalArea() {
        for (int i = 0; i < tuxing.length; i++) {
            totalArea += tuxing[i].getArea();
        }
        return totalArea;
    }
}

//Rect.java
class Rect extends Geometry {
    double a, b;

    Rect(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getArea() {
        return a * b;
    }
}

//Circle.java
class Circle extends Geometry {
    double r;

    Circle(double r) {
        this.r = r;
    }

    public double getArea() {
        return Math.PI * r * r;
    }
}

//MainClass.java
public class MainClass {
    public static void main(String args[]) {
        Geometry[] tuxing = new Geometry[29];

        for (int i = 0; i < tuxing.length; i++) {
            if (i % 2 == 0)
                tuxing[i] = new Rect(16 + i, 68);
            else if (i % 2 == 1)
                tuxing[i] = new Circle(10 + i);
        }

        TotalArea computer = new TotalArea();
        computer.setTuxing(tuxing);

        System.out.printf("各种图形的面积之和：\n%f",
                computer.computerTotalArea());
    }
}
