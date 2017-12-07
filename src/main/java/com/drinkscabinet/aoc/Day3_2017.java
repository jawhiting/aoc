package com.drinkscabinet.aoc;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public class Day3_2017 {

    public static void main(String[] args) {
        System.out.println(distance(1));
        System.out.println(distance(12));
        System.out.println(distance(23));
        System.out.println(distance(1024));
        System.out.println(distance(277678));

        System.out.println(gridValues(11, 277678));


    }

    private static final int gridValues(int size, int limit) {
        int[][] grid = new int[size][size];

        int mid = size / 2;
        int cellNo = 1;
        int layer = 0;

        int row = mid;
        int col = mid;
        grid[mid][mid] = 1;

        int currentValue = 0;
        while(currentValue <= limit) {
            if( cellNo > layerLimit(layer) ) {
                // move to next layer
                layer++;
                col++;  // move right
            }
            // calculate cell
            currentValue =
                    grid[row-1][col-1] + grid[row-1][col] + grid[row-1][col+1] +
                    grid[row][col-1] + grid[row][col] + grid[row][col+1] +
                    grid[row+1][col-1] + grid[row+1][col] + grid[row+1][col+1];
            grid[row][col] = currentValue;
            // next cell
            Pair<Integer, Integer> delta = nextCell(cellNo, corners(layer));
            row += delta.getLeft();
            col += delta.getRight();
            cellNo++;
            if( cellNo > layerLimit(layer) ) {
                layer++;
                System.out.println(print(grid));
            }
         }
        System.out.println(print(grid));
        return currentValue;
    }

    public static String print(int[][] grid) {
        StringBuilder sb = new StringBuilder(1024);
        for( int row = 0; row < grid.length; row++ ) {
            for( int col = 0; col < grid[row].length; col++) {
                sb.append(grid[row][col]).append('\t').append('\t');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private static Pair<Integer, Integer> nextCell(int currentCell, int[] corners) {
        if( currentCell < corners[0] ) return Pair.of(1, 0);
        if( currentCell < corners[1] ) return Pair.of(0, -1);
        if( currentCell < corners[2] ) return Pair.of(-1, 0);
        if( currentCell < corners[3] ) return Pair.of(0, 1);
        return Pair.of(0, 1);
    }

    private static int[] corners(int layer) {
        int n = edgeSize(layer);
        int bottomRight = n*n;
        int bottomLeft = bottomRight - (n-1);
        int upperLeft = bottomRight - 2*(n-1);
        int upperRight = bottomRight - 3*(n-1);


        int[] corners = {upperRight, upperLeft, bottomLeft, bottomRight};
        return corners;
    }

    private static int edgeSize(int layer) {
        return layer * 2 + 1;
    }

    private static int layerLimit(int layer) {
        return edgeSize(layer) * edgeSize(layer);
    }

    private static final int distance(int cell) {
        // bottom corner is lowest odd number squared that is above the item
        int n = 1;
        double sqrt = Math.sqrt(cell);
        if( sqrt == (int)sqrt ) n = (int) sqrt;
        else {
            n = (int)sqrt + 1;
            if( n % 2 == 0 ) n++;
        }

        int bottomRight = n*n;
        int bottomLeft = bottomRight - (n-1);
        int upperLeft = bottomRight - 2*(n-1);
        int upperRight = bottomRight - 3*(n-1);

        System.out.println(String.join(",", ""+bottomRight, ""+bottomLeft, ""+upperLeft, ""+upperRight));

        int[] corners = {upperRight, upperLeft, bottomLeft, bottomRight};
        // find the corner
        int corner = corners[0];
        for (int i : corners) {
            if( cell > i ) corner = i;
        }

        int midPoint = corner + n / 2;
        System.out.println("Mid: " + midPoint);
        int stepsToMid = Math.abs(cell-midPoint);

        return stepsToMid + (n/2);


    }
}
