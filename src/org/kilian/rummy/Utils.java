package org.kilian.rummy;

import org.kilian.rummy.material.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    public static Set<List<Tile>> calculateCoherentSubGroups(List<Tile> tiles, int minSize) {
        Set<List<Tile>> result = new HashSet<>();
        for (int size = minSize; size <= tiles.size(); size++) {
            for (int i = 0; i < tiles.size(); i++) {
                if (i + size > tiles.size()) {
                    break;
                }
                result.add(tiles.subList(i, i + size));

            }
        }

        return result;
    }

    public static Set<List<Tile>> calculateSubGroupsWithoutCoherence(List<Tile> inputGroup, int minSize) {
        Set<List<Tile>> result = new HashSet<>();
        List<Tile> groupWithoutNulls = inputGroup.stream().dropWhile(Objects::isNull).collect(Collectors.toList());
        if (groupWithoutNulls.size() < minSize) {
            return result;
        } else if (groupWithoutNulls.size() == minSize) {
            result.add(inputGroup);
            return result;
        }
        result.add(inputGroup);

        for (int currentSize = minSize; currentSize < inputGroup.size(); currentSize++) {
            Set<List<Tile>> combinations = collectCombinations(inputGroup.toArray(new Tile[0]), currentSize);
            result.addAll(combinations);
        }

        return result;
    }

    public static Set<List<Tile>> collectCombinations(Tile[] inputGroup, int size) {
        // A temporary array to store all combination one by one
        Tile[] data=new Tile[size];

        // Print all combination using temprary array 'data[]'
        Set<List<Tile>> result = new HashSet<>();
        collectCombinations(inputGroup, data, 0, inputGroup.length-1, 0, size, result);
        return result;
    }

    static void collectCombinations(Tile[] arr, Tile[] data, int start,
                                    int end, int index, int r, Set<List<Tile>> result)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
            List<Tile> newGroup = new ArrayList<>(Arrays.asList(data).subList(0, r));
            result.add(newGroup);
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i=start; i<=end && end-i+1 >= r-index; i++)
        {
            data[index] = arr[i];
            collectCombinations(arr, data, i+1, end, index+1, r, result);
        }
    }
}
