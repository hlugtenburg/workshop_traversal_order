package org.example;

public class ConsolePaint {

    char[][] coordinateSystem;

    public ConsolePaint(int width, int height, Line v) {
        coordinateSystem = new char[width][height];
        for (var i = 0; i< coordinateSystem[0].length; i++){
            for (var j = 0; j < coordinateSystem.length; j++){
                coordinateSystem[j][i] = ' ';
            }
        }
        v.draw(coordinateSystem, new Coordinate(0,0));
    }

    public void draw() {
        for (int i = 0; i < coordinateSystem.length + 2; i++) {
            System.out.print('.');

        }
        System.out.println();
        for (int y = coordinateSystem[0].length - 1; y >= 0; y--) {
            System.out.print(':');
            for (int x =0; x < coordinateSystem.length; x++) {
                System.out.print(coordinateSystem[x][y]);
            }
            System.out.print(':');
            System.out.println();
        }

        for (int i = 0; i < coordinateSystem.length + 2; i++) {
            System.out.print('.');
        }
        System.out.println();
    }

}
