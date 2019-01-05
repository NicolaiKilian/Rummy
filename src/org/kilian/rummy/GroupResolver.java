package org.kilian.rummy;

import org.kilian.rummy.material.*;

import java.util.*;

import static org.kilian.rummy.material.Constants.MAX_TILE_NUMBER;

public class GroupResolver {
    Tile[][][] tileMatrix = new Tile[MAX_TILE_NUMBER][Color.values().length][Constants.NUMBER_OF_TILE_DUPLICATES];
    private Map<Color, Integer> colorPositions = new HashMap<>();
    private Map<NumberTile, JokerTile> jokersReplacingNumberTiles = new HashMap<>();
    private Set<JokerTile> jokerTiles = new HashSet<>();

    public GroupResolver() {
        colorPositions.put(Color.BLACK, 0);
        colorPositions.put(Color.GRAY, 1);
        colorPositions.put(Color.GREEN, 2);
        colorPositions.put(Color.MAGENTA, 3);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new GroupResolver().tileMatrix));
    }

    public JokerTile putNumberTile(NumberTile tile) {
        Tile[] positionForNumberAndColor = this.tileMatrix[tile.getNumber() - 1][colorPositions.get(tile.getColor())];
        if (jokersReplacingNumberTiles.get(tile) != null) {
            JokerTile jokerTile = jokersReplacingNumberTiles.remove(tile);
            for (int i = 0; i < Constants.NUMBER_OF_TILE_DUPLICATES; i++) {
                if (positionForNumberAndColor[i] == jokerTile) {
                    positionForNumberAndColor[i] = tile;
                    return jokerTile;
                }
            }
        }

        for (int i = 0; i < Constants.NUMBER_OF_TILE_DUPLICATES; i++) {
            if (positionForNumberAndColor[i] == null) {
                positionForNumberAndColor[i] = tile;
                break;
            }
        }

        return null;
    }

    public void putJokerTile(JokerTile jokerTile) {
        jokerTiles.add(jokerTile);
    }

    public List<Groups> findGroupCombinations() {

        Groups allGroups = new RowGroupFinder().findAllRowGroupsIgnoringOverlaps(tileMatrix);
        allGroups.merge(new ColorGroupFinder().findAllColorGroupsIgnoringOverlaps(tileMatrix));
        return new DisjunctGroupCombinationsFinder().findCombinations(allGroups);
    }

}
