package com.drinkscabinet.aoc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jwhiting on 24/12/2016.
 */
public class PathFinder {
    private Map<Node, Integer> distances = new HashMap<>();
    private Set<Node> visited = new HashSet<>();
    private Set<Node> unsettled = new HashSet<>();
    private Node start;
    private Node target;

    public PathFinder(Node start, Node target) {
        this.start = start;
        this.target = target;
    }

    public int shortestPath() {
        // Initialise

        unsettled.add(start);
        distances.put(start, 0);
        while (!unsettled.isEmpty()) {
            Node node = nextNode();
            if (node.equals(target)) {

                System.out.println("Found target: " + distances.get(node));
                return distances.get(node);
            }
            calcDistances(node);
//            System.out.println("Visited: " + visited.size() + " Unsettled: " + unsettled.size());
        }
        System.out.println("Finished");
        distances.entrySet().forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
        throw new RuntimeException("Unable to find path");
    }

    private void calcDistances(Node node) {
        if (visited.contains(node)) return;
        int nextDistance = distances.get(node) + 1;

        Set<? extends Node> neighbours = node.getNeighbours();
        // remove already visited
        for (Node neighbour : neighbours) {
            if (!visited.contains(neighbour)) {
                distances.merge(neighbour, nextDistance, Integer::min);
                unsettled.add(neighbour);
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
}
