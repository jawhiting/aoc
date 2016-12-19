package com.drinkscabinet.aoc;

import java.util.*;

/**
 * Created by jwhiting on 19/12/2016.
 */
public class Day19 {

    private static class Elf {
        int id;
        Elf next;
        Elf prev;

        Elf(int i) {
            id = i;
        }

        void setNext(Elf next) {
            this.next = next;
            next.prev = this;
        }

        void setPrev(Elf prev) {
            this.prev = prev;
        }

        Elf remove() {
            prev.setNext(next);
            return prev;
        }
    }



    public static void main(String[] args) {
        new Day19().run(5);
        new Day19().run(3001330);
    }

    public void run(int count) {
        // init
        Elf first = new Elf(1);
        Elf end = first;
        int elfCount = count;
        for( int i = 0; i < count-1; i++) {
            Elf e = new Elf(i+2);
            end.setNext(e);
            end = e;
        }
        // Now at the end, so join into a ring
        end.setNext(first);

        Elf current = first;
        Elf opposite = first;
        // Now get to the opposite one
        for( int pos = 0; pos < count / 2; pos++) {
            opposite = opposite.next;
        }

        System.out.println("Current: " + current.id + " Opposite: " + opposite.id);

        while( current.id != current.next.id) {
            opposite = opposite.remove();
            elfCount--;
            current = current.next;
            opposite = opposite.next;
            if( elfCount % 2 == 0) opposite = opposite.next;
        }
        System.out.println("Winner: " + current.id);
    }
}
