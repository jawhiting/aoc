package com.drinkscabinet.aoc;

import com.google.common.collect.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Day12_2017 {



    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("Day12_2017.txt")));

        Multimap<Integer, Integer> nodes = HashMultimap.create();

        r.lines().forEach(l -> {
            String name = l.split(" ")[0];
            String[] dests = l.substring(l.indexOf('>')+2).split(", ");
            for (String dest : dests) {
                nodes.put(Integer.valueOf(name.trim()), Integer.valueOf(dest.trim()));
            }
        });
        System.out.println(nodes);

        TreeSet<Integer> visited = new TreeSet<>();
        visit(0, nodes, visited);
        System.out.println(visited);
        System.out.println(visited.size());

        Set<Set<Integer>> groups = new HashSet<>();
        for (Integer integer : nodes.keySet()) {
            TreeSet<Integer> v = new TreeSet<>();
            visit(integer, nodes, v);
            groups.add(v);
        }
        System.out.println(groups.size());

    }

    private static void visit(int node, Multimap<Integer, Integer> nodes, Set<Integer> visited) {
        for (Integer integer : nodes.get(node)) {
            if( !visited.contains(integer)) {
                visited.add(integer);
                visit(integer, nodes, visited);
            }
        }
    }
}
