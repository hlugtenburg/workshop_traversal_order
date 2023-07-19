package org.example;

import static org.example.Line.Direction.E;
import static org.example.Line.Direction.NE;

public class Main {
    public static void main(String[] args) {

        var x0 = new Line(E, 5, true);
        var x = new Line(Line.Direction.N, 5);
        x0.addLine(x);
        x.addLine(new Line(E, 7));
        x.addLine(new Line(NE, 7));


        var cp = new ConsolePaint(40, 40, x0);
        cp.draw();
    }
}
