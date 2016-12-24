package com.drinkscabinet.aoc;

import java.util.Set;

/**
 * Created by jwhiting on 24/12/2016.
 */
public interface Node {
    Set<? extends Node> getNeighbours();
}
