package com.drinkscabinet.aoc;

/**
 * Created by jwhiting on 15/12/2016.
 */
public class Day15 {

    public static void main(String[] args) {
        for( int i = 0; i < 100000000; i++) {
//            if( (4+i+1)%5 + (1+i+2)%2 == 0 ) {
            if( (2+i+1)%5 + (7+i+2)%13 + (10+i+3)%17 + (2+i+4)%3 + (9+i+5)%19 + (0+i+6)%7 + (0+i+7)%11 == 0 ) {

                System.out.println("Time: " + i);
                return;
            }
        }
    }
}
