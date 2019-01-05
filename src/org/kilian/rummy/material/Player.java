package org.kilian.rummy.material;

import org.kilian.rummy.material.Tile;
import org.kilian.rummy.material.TileHeap;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private static final int INITIAL_NUMBER_OF_TILES = 14;
    Set<Tile> tiles = new HashSet<>();
    private String name;

    public Player(String name, TileHeap tileHeap) {
        this.name = name;
        for (int i= 0; i < INITIAL_NUMBER_OF_TILES; i++) {
            tiles.add(tileHeap.takeOne());
        }
    }

    public Set<Tile> getTiles() {
        return tiles;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name + ", " +
                "tiles=" + tiles +
                '}';
    }
}
