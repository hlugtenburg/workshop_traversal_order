package org.example;

public class Coordinate {
    public final int x;
    public final int y;
    public Coordinate(int x, int y){

        this.x = x;
        this.y = y;
    }

    public Coordinate add(Coordinate c) {
        return new Coordinate(x + c.x, y + c.y);
    }
}
