package com.drinkscabinet.aoc;

import java.util.*;

/**
 * Created by jwhiting on 13/12/2016.
 */
public class Day13 {

    private final int seed;

    public Day13(int seed) {
        this.seed = seed;
    }

    private final boolean isOpen(int x, int y) {
        if( x < 0 || y < 0 ) return false;
        int v = x*x + 3*x + 2*x*y + y + y*y;
        v += seed;
        int bits = Integer.bitCount(v);
        return bits % 2 == 0;
    }

    public void printGrid(int width, int height) {
        for( int y = 0; y < height; y++ ) {
            StringBuilder row = new StringBuilder();

            for( int x = 0; x < width; x++) {
                row.append(isOpen(x, y) ? "." : "#");
            }
            System.out.println(row);
        }
    }

    private final Node[] directions = new Node[]{new Node(1, 0), new Node(-1, 0), new Node(0, 1), new Node(0, -1)};

    private class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Set<Node> getNeighbours() {
            HashSet<Node> result = new HashSet<>(3);

            for (Node direction : directions) {
                Node next = add(direction);
                if (next.isValid()) {
                    result.add(next);
                }
            }
            return result;
        }

        public boolean isValid() {
            return isOpen(x, y);
        }

        public Node add(Node node) {
            return new Node(x + node.x, y + node.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (x != node.x) return false;
            return y == node.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    private Map<Node, Integer> distances = new HashMap<>();
    private Set<Node> visited = new HashSet<>();
    private Set<Node> unsettled = new HashSet<>();

    public void shortestPath(int startX, int startY, int targetX, int targetY) {
        // Initialise
        Node start = new Node(startX, startY);
        Node target = new Node(targetX, targetY);

        unsettled.add(start);
        distances.put(start, 0);
        while (!unsettled.isEmpty()) {
            Node node = nextNode();
            if (node.equals(target)) {

                System.out.println("Found target: " + distances.get(node));
                return;
            }
            calcDistances(node);
            System.out.println("Visited: " + visited.size() + " Unsettled: " + unsettled.size());
        }
        System.out.println("Finished");
        distances.entrySet().forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
    }

    private void calcDistances(Node node) {
        if( visited.contains(node)) return;
        int nextDistance = distances.get(node)+1;
        // Prune at 50 distance for part2
        if( nextDistance <= 50 ) {

            Set<Node> neighbours = node.getNeighbours();
            // remove already visited
            for (Node neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    distances.merge(neighbour, nextDistance, Integer::min);
                    unsettled.add(neighbour);
                }
            }
        }
        unsettled.remove(node);
        visited.add(node);
    }

    private Node nextNode() {
        int minDist = Integer.MAX_VALUE;
        Node minNode = null;
        for (Node node : unsettled) {
            int dist = distances.get(node);
            if (dist < minDist) {
                minDist = dist;
                minNode = node;
            }
        }
        return minNode;
    }

    public static void main(String[] args) {
        Day13 d13 = new Day13(10);
        d13.printGrid(10, 7);
        System.out.println(new Day13(10).isOpen(7,4));
        System.out.println(new Day13(10).isOpen(9,6));
        System.out.println(new Day13(10).isOpen(9,5));

        d13.shortestPath(1, 1, 7, 4);

        Day13 d132 = new Day13(1358);
        d132.shortestPath(1, 1, 100, 100);
    }
}
