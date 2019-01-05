package org.kilian.rummy.material;

import org.kilian.rummy.material.*;

import java.util.*;

public class TileHeap {
    Set<Tile> tiles = new HashSet<>();

    public TileHeap() {
        List<Tile> randomList = new ArrayList<>();
        for (int j = 0; j < Constants.NUMBER_OF_TILE_DUPLICATES; j++) {
            for (int i = 1; i <= Constants.MAX_TILE_NUMBER; i++) {
                for (Color color : Color.values()) {
                    randomList.add(new NumberTile(color, i));
                }
            }
        }
        for (int i = 0; i < Constants.NUMBER_OF_JOKERS; i++) {
            randomList.add(new JokerTile());
        }
        Collections.shuffle(randomList, new Random());
        tiles.addAll(randomList);
    }


    public Tile takeOne() {
        if (tiles.isEmpty()) {
            return null;
        }

        Iterator<Tile> iterator = tiles.iterator();
        Tile randomTile = iterator.next();
        iterator.remove();

        return randomTile;
    }

    public int getSize() {
        return tiles.size();
    }

    @Override
    public String toString() {
        return "TileHeap{" +
                "tiles=" + tiles +
                '}';
    }
}
