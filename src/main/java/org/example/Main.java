package org.example;

import static org.example.Line.Direction.E;

public class Main {
    public static void main(String[] args) {

        var x = new Line(Line.Direction.N, 5);
        x.addLine(new Line(E, 7));


        var cp = new ConsolePaint(40, 10, x);
        cp.draw();
    }
}
