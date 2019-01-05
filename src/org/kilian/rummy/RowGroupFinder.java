package org.kilian.rummy;

import org.kilian.rummy.material.Tile;

import java.util.*;

import static org.kilian.rummy.material.Constants.MIN_GROUP_SIZE;
import static org.kilian.rummy.Utils.calculateCoherentSubGroups;

public class RowGroupFinder {

    Groups findAllRowGroupsIgnoringOverlaps(Tile[][][] tileMatrix) {
        Map<Tile, List<Tile>> tilesInGroups = new HashMap<>();

        //TODO: Handle duplicates!
        //TODO: Handle Jokers!
        for (int currentColumn = 0; currentColumn < tileMatrix.length; currentColumn++) {
            Tile[][] colorsAndDuplicatesForNumber = tileMatrix[currentColumn];
            for (int currentColor = 0; currentColor < colorsAndDuplicatesForNumber.length; currentColor++) {
                Tile[] duplicates = colorsAndDuplicatesForNumber[currentColor];
                for (Tile tile : duplicates) {
                    if (tile != null && !tilesInGroups.containsKey(tile)) {
                        findMaxRowGroupForTile(tileMatrix, tile, tilesInGroups, currentColumn, currentColor);
                    }
                }
            }
        }

        Set<List<Tile>> subGroups = new HashSet<>();
        for (Map.Entry<Tile, List<Tile>> tileListEntry : tilesInGroups.entrySet()) {
            List<Tile> tiles = tileListEntry.getValue();
            Set<List<Tile>> subGroupsForTile = calculateCoherentSubGroups(tiles, MIN_GROUP_SIZE);
            if (!subGroupsForTile.isEmpty()) {
                subGroups.addAll(subGroupsForTile);
            }
        }

        return new Groups(subGroups);
    }

    private void findMaxRowGroupForTile(Tile[][][] tileMatrix, Tile tile, Map<Tile, List<Tile>> tilesInGroups, int currentColumn, int currentColorIndex) {
        List<Tile> maxGroupForTile = tilesInGroups.get(tile);
        if (maxGroupForTile == null) {
            maxGroupForTile = new ArrayList<>();
            maxGroupForTile.add(tile);
        }
        for (int k = currentColumn + 1; k < tileMatrix.length; k++) {
            boolean subSequentTileFound = false;
            Tile[] nextDuplicateInRow = tileMatrix[k][currentColorIndex];
            for (Tile nextTileInRow : nextDuplicateInRow) {
                if (nextTileInRow != null) {
                    subSequentTileFound = true;
                    maxGroupForTile.add(nextTileInRow);
                    tilesInGroups.put(nextTileInRow, maxGroupForTile);
                }
            }
            if (!subSequentTileFound) {
                break;
            }
        }
        if (maxGroupForTile.size() != 1) {
            tilesInGroups.put(tile, maxGroupForTile);
        }
    }
}
