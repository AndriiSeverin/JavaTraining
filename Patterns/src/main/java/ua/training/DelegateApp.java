package ua.training;

/**
 * Created by andrii on 19.11.16.
 */
public class DelegateApp {
    public static void main(String[] args) {

//        A a = new A();
//        a.f();

        Painter painter = new Painter();
        painter.setGraphics(new Square());
        painter.draw();

        painter.setGraphics(new Triangle());
        painter.draw();
    }
}

//class A {
//    void f() {
//        System.out.println("f()");
//    }
//}
//
//class B {
//    A a = new A();
//    void f() {
//        a.f();
//    }
//}

interface Graphics {
    void draw();
}

class Triangle implements Graphics {
    @Override
    public void draw() {
        System.out.println("draw triangle");
    }
}

class Square implements Graphics {
    @Override
    public void draw() {
        System.out.println("draw square");
    }
}

class Circle implements Graphics {
    @Override
    public void draw() {
        System.out.println("draw circle");
    }
}

class Painter {
    Graphics graphics;
    void setGraphics(Graphics g) {
        this.graphics = g;
    }
    void draw() {
        graphics.draw();
    }
}