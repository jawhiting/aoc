package com.drinkscabinet.aoc;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jwhiting on 19/12/2016.
 */
public class Day17 {

    private enum Direction {
        U(0, -1), D(0, 1), L(-1, 0), R(1, 0);

        private int xInc;
        private int yInc;

        private Direction(int x, int y) {
            xInc = x;
            yInc = y;
        }
    }

    private final int width = 4;
    private final int height = 4;

    private final String key;

    private List<Direction> shortest = null;
    private List<Direction> longest = null;

    public static void main(String[] args) {
        new Day17("ihgpwlah").run();
        new Day17("kglvqrro").run();
        new Day17("ulqzkmiv").run();
        new Day17("dmypynyp").run();
    }

    public Day17(String key) {
        this.key = key;
    }

    public void run() {
        List<Direction> shortest  = visit(0, 0, Lists.newArrayList());
        System.out.println("Result: " + shortest.size() + " " + shortest.stream().map(Direction::toString).collect(Collectors.joining()));
    }

    public List<Direction> visit(int x, int y, List<Direction> path) {
        if( x == width-1 && y == height-1) {
            System.out.println("Found path: " + path.size() + " " + path);
            return path;
        }

//        if (shortest != null && shortest.size() < path.size() ) {
//            // prune
//            return null;
//        }
        // Find options from here
        Set<Direction> dirs = open(x, y, path);
        for (Direction dir : dirs) {
            List<Direction> cp = new LinkedList<>(path);
            cp.add(dir);
            // try each
            List<Direction> childPath = visit(x+dir.xInc, y+dir.yInc, cp);
            if( childPath == null ) continue;
            if( shortest == null ) {
                shortest = childPath;
                longest = childPath;
            }
            if( childPath.size() < shortest.size()) shortest = childPath;
            if( childPath.size() > longest.size()) longest = childPath;
        }
        return longest;
    }

    public Set<Direction> open(int x, int y, List<Direction> path) {
        String toHash = key;
        for (Direction direction : path) {
            toHash += direction.toString();
        }
        String hash = DigestUtils.md5Hex(toHash).substring(0, 4);

        Set<Direction> result = new TreeSet<>();
        for (Direction direction : Direction.values()) {
            char c = hash.charAt(direction.ordinal());
            if( c > 'a') result.add(direction);
        }
        // Remove any edge ones
        if( x == 0 ) result.remove(Direction.L);
        if( x == width-1) result.remove(Direction.R);
        if( y == 0 ) result.remove(Direction.U);
        if( y == height-1 ) result.remove(Direction.D);
        return result;
    }
}
