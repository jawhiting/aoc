package com.drinkscabinet.aoc;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jwhiting on 11/12/2016.
 */
public class Day11 {

    private static class Floor implements Comparable<Floor> {

        private static final Map<String, Floor> floorCache = new HashMap<>(1000001);

        public static Floor create(String cts) {
            TreeSet<String> ts = new TreeSet<>();
            for (char c : cts.toCharArray()) {
                ts.add(""+c);
            }
            String str = String.join("", ts);

            return floorCache.computeIfAbsent(str, Floor::new);
        }

        private final Set<String> contents = new TreeSet<>();

        private Floor(String cts) {
            for (char c : cts.toCharArray()) {
                contents.add(""+c);
            }
        }

        public String getContents() {
            return String.join("", contents);
        }

        private Set<String> getGenerators() {
            return contents.stream().filter(c -> c.charAt(0) > 'z').collect(Collectors.toSet());
        }

        private Set<String> getMicrochips() {
            return contents.stream().filter(c -> c.charAt(0) <= 'z').collect(Collectors.toSet());
        }

        public boolean isSafe() {
            // A floor is safe if there are no chips that don't have a corresponding generator
            Set<String> generators = getGenerators();
            for (String microchip : getMicrochips()) {
                if(!generators.isEmpty() && !generators.contains(microchip.toUpperCase())) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int compareTo(Floor o) {
            return getContents().compareTo(o.getContents());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Floor floor = (Floor) o;

            return contents.equals(floor.contents);
        }

        @Override
        public int hashCode() {
            return contents.hashCode();
        }
    }

    private static class Board {

        private final Floor[] floors;
        private final int elevator;
        private double distance;

        public Board(int elevator, Floor[] floors) {

            this.elevator = elevator;
            this.floors = floors;
            distance = calcDistance();
        }

        public double getDistance() {
            return distance;
        }

        private double calcDistance() {
            double total = 0;
            double count = 0;
            for( int i = 0; i < floors.length; i++ ) {
                int fc = floors[i].getContents().length();
                count += fc;
                total += fc * (floors.length-1-i);
            }
            return total / count;
        }

        public boolean isValid() {
            for (Floor floor : floors) {
                if( !floor.isSafe() ) {
                    return false;
                }
            }
            return true;
        }

        public Board up(String... values) {
            if( elevator == floors.length-1 ) throw new RuntimeException("Elevator off the top");
            Floor[] f = new Floor[floors.length];
            for( int i = 0; i < floors.length; i++) {
                if( i == elevator) {
                    String fc = floors[i].getContents();
                    for (String value : values) {
                        fc = fc.replaceAll(value, "");
                    }
                    f[i] = Floor.create(fc);
                }
                else if( i == elevator+1 ){
                    f[i] = Floor.create(floors[i].getContents()+String.join("",  values));
                }
                else {
                    f[i] = floors[i];
                }
            }
            return new Board(elevator+1, f);
        }

        public Board down(String... values) {
            if( elevator == 0 ) throw new RuntimeException("Elevator off the bottom");
            Floor[] f = new Floor[floors.length];
            for( int i = 0; i < floors.length; i++) {
                if( i == elevator) {
                    String fc = floors[i].getContents();
                    for (String value : values) {
                        fc = fc.replaceAll(value, "");
                    }
                    f[i] = Floor.create(fc);
                }
                else if( i == elevator-1 ){
                    f[i] = Floor.create(floors[i].getContents()+String.join("",  values));
                }
                else {
                    f[i] = floors[i];
                }
            }
            return new Board(elevator-1, f);
        }

        public List<Board> getNextValidBoards() {
            List<Board> result = new LinkedList<>();

            String currentFloor = floors[elevator].getContents();

            for( int i = 0; i < 2; i++ ) {
                if( i == 0 && elevator == 0 ) continue;
                if( i == 1 && elevator == floors.length-1) continue;
                // try each char by itself, then with the others
                for( int c = 0; c < currentFloor.length(); c++ ) {
                    Board single = i == 1 ? up(""+currentFloor.charAt(c)) : down(""+currentFloor.charAt(c));
                    if( single.isValid() ) result.add(single);
                    for( int y = c+1; y < currentFloor.length(); y++) {
                        Board b = i == 1 ? up(""+currentFloor.charAt(c), ""+currentFloor.charAt(y)) : down(""+currentFloor.charAt(c), ""+currentFloor.charAt(y));
                        if( b.isValid() ) result.add(b);
                    }
                }
            }
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Board board = (Board) o;
            if (elevator != board.elevator) return false;
            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            return Arrays.equals(floors, board.floors);

        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(floors);
            result = 31 * result + elevator;
            return result;
        }
    }

    private static class BoardDist {
        private final int dist;
        private final Board board;

        public BoardDist(int dist, Board board) {
            this.dist = dist;
            this.board = board;
        }

        public int getDist() {
            return dist;
        }

        public Board getBoard() {
            return board;
        }

    }

    private Map<Board, Integer> distances = new HashMap<>();
    private Set<Board> visited = new HashSet<>();
    private Set<Board> unsettled = new HashSet<>();
    private PriorityQueue<BoardDist> unsettledPriority = new PriorityQueue<>((bd1, bd2) -> Double.compare(bd1.getBoard().getDistance(), bd2.getBoard().getDistance()));

    public static void main(String[] args) {
        Board start = new Board(0, new Floor[]{new Floor("hl"), new Floor("H"), new Floor("L"), new Floor("")});
        Board target = new Board(3, new Floor[]{new Floor(""), new Floor(""), new Floor(""), new Floor("hlHL")});

        Board start2 = new Board(0, new Floor[]{ Floor.create("eEdDsSpP"),  Floor.create("TRrCc"),  Floor.create("t"),  Floor.create("")});
        Board target2 = new Board(3, new Floor[]{ Floor.create(""), Floor.create(""), Floor.create(""), Floor.create("eEdDcCpPsSrRtT")});

        //new Day11().run(start, target);
        new Day11().run(start2, target2);
    }

    public void run(Board start, Board target) {
        // Initialise
        distances.put(start, 0);
        unsettled.add(start);
        unsettledPriority.add(new BoardDist(0, start));
        int iter = 0;
        while(!unsettled.isEmpty()) {
            Board b = getNext();
            if( b.equals(target)) {
                System.out.println("Found target, distance: " + distances.get(target));
                System.exit(0);
            }
            else {
                calcDistance(b);
            }
            if( iter++ % 1000 == 0 ) {
                System.out.println("Iter: " + iter + " visited: " + visited.size() + " unsettled: " + unsettled.size());
            }
        }
    }

    public Board getNext() {
        return unsettledPriority.poll().getBoard();
        /*
        int minDist = Integer.MAX_VALUE;
        Board minBoard = null;
        for (Board board : unsettled) {
            int dist = distances.get(board);
            if( dist < minDist ) {
                minDist = dist;
                minBoard = board;
            }
        }
        return minBoard;
        */
    }

    public void calcDistance(Board current) {
        if( visited.contains(current) ) return;

        int nextDistance = distances.get(current)+1;

        // Now get all the next possibilities
        List<Board> nextBoards = current.getNextValidBoards();
        // Remove all the ones we've already visited
        Set<Board> toVisit = nextBoards.parallelStream().filter(b -> !visited.contains(b)).collect(Collectors.toSet());
        // Remove all the ones we already know about
        toVisit = toVisit.parallelStream().filter(b -> !unsettled.contains(b)).collect(Collectors.toSet());

        // Initialise distances
        for (Board board : toVisit) {
            int d = distances.merge(board, nextDistance, Integer::min);
            unsettled.add(board);
            unsettledPriority.add(new BoardDist(d, board));
        }

        unsettled.addAll(toVisit);

        unsettled.remove(current);
        visited.add(current);
    }

    private static final String input = "" +
            "The first floor contains a hydrogen-compatible microchip and a lithium-compatible microchip.\n" +
            "The second floor contains a hydrogen generator.\n" +
            "The third floor contains a lithium generator.\n" +
            "The fourth floor contains nothing relevant.";
}
