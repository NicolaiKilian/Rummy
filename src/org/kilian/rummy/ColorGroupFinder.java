package org.kilian.rummy;

import org.kilian.rummy.material.Color;
import org.kilian.rummy.material.Tile;

import java.util.*;

import static org.kilian.rummy.Utils.calculateSubGroupsWithoutCoherence;
import static org.kilian.rummy.material.Constants.MIN_GROUP_SIZE;
import static org.kilian.rummy.Utils.calculateCoherentSubGroups;

public class ColorGroupFinder {

    Groups findAllColorGroupsIgnoringOverlaps(Tile[][][] tileMatrix) {
        Map<Tile, List<Tile>> tilesInGroups = new HashMap<>();

        //TODO: Handle duplicates!
        //TODO: Handle Jokers!
        for (int currentColumn = 0; currentColumn < tileMatrix.length; currentColumn++) {
            Tile[][] colorsAndDuplicatesForNumber = tileMatrix[currentColumn];
            for (int currentColor = 0; currentColor < colorsAndDuplicatesForNumber.length; currentColor++) {
                Tile[] duplicates = colorsAndDuplicatesForNumber[currentColor];
                for (Tile tile : duplicates) {
                    if (tile != null && !tilesInGroups.containsKey(tile)) {
                        findColorGroupsForTile(tileMatrix, tile, tilesInGroups, currentColumn, currentColor);
                    }
                }
            }
        }

        Set<List<Tile>> subGroups = new HashSet<>();
        for (Map.Entry<Tile, List<Tile>> tileListEntry : tilesInGroups.entrySet()) {
            List<Tile> tiles = tileListEntry.getValue();
            Set<List<Tile>> subGroupsForTile = calculateSubGroupsWithoutCoherence(tiles, MIN_GROUP_SIZE);
            if (!subGroupsForTile.isEmpty()) {
                subGroups.addAll(subGroupsForTile);
            }
        }

        return new Groups(subGroups);
    }

    private void findColorGroupsForTile(Tile[][][] tileMatrix, Tile tile, Map<Tile, List<Tile>> tilesInGroups, int currentColumn, int colorIndex) {
        List<Tile> maxGroupForTile = tilesInGroups.get(tile);
        if (maxGroupForTile == null) {
            maxGroupForTile = new ArrayList<>();
            maxGroupForTile.add(tile);
        }
        for (int nextColorIndex = colorIndex + 1; nextColorIndex < Color.values().length; nextColorIndex++) {
            Tile[] nextDuplicateInColumn = tileMatrix[currentColumn][nextColorIndex];
            for (Tile nextTileInColumn : nextDuplicateInColumn) {
                if (nextTileInColumn != null) {
                    maxGroupForTile.add(nextTileInColumn);
                    tilesInGroups.put(nextTileInColumn, maxGroupForTile);
                }
            }
        }
        if (maxGroupForTile.size() != 1) {
            tilesInGroups.put(tile, maxGroupForTile);
        }
    }
}
