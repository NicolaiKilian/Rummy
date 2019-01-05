package org.kilian.rummy.material;

import java.util.Objects;

public class NumberTile implements Tile {
    private Color color;
    private int number;

    public NumberTile(Color color, int number) {
        this.color = color;
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

//    @Override
//    public String toString() {
//        return number + "_" + color.name();
//    }

    public String toString() {
        return color.getCode() + number + (char)27 + "[0m";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberTile that = (NumberTile) o;
        return number == that.number && color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, number);
    }
}
