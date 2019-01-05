package org.kilian.rummy;

import org.kilian.rummy.material.Player;
import org.kilian.rummy.material.TileHeap;

public class Main {

    public static void main(String[] args) {
        TileHeap tileHeap = new TileHeap();
        System.out.println(tileHeap);
        Player player = new Player("Nicolai", tileHeap);
        System.out.println(player);
        System.out.println(tileHeap);
    }
}
