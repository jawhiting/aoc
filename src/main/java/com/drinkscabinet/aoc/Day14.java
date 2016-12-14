package com.drinkscabinet.aoc;

import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.TreeMultimap;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Created by jwhiting on 14/12/2016.
 */
public class Day14 {

    private final String seed;
    private static final Pattern p3 = Pattern.compile("(.)\\1\\1");
    private static final Pattern p5 = Pattern.compile("(.)\\1\\1\\1\\1");

    private SetMultimap<String, Integer> fives = Multimaps.synchronizedSortedSetMultimap(TreeMultimap.create());

    private ConcurrentMap<Integer, String> hashCache = new ConcurrentHashMap<>(100001);
//    private Multimap<Integer, String> fives = ArrayListMultimap.create();
    private int check5limit = 0;

    public Day14(String seed) {
        this.seed = seed;

    }

    public void run() throws NoSuchAlgorithmException {

        int foundCount = 0;
        int i = 0;
        while( foundCount < 64 ) {

            if( check5limit < i + 1005) {
                next1000();
            }
            // prep the 5s
//            for( int c5 = check5limit; c5 < i+1005; c5++) {
//                check5(c5);
//            }
            // Now check the next c3
            Set<String> c3 = check3(i);
            for (String s : c3) {
                int finalI = i;
                Optional<Integer> found5 = fives.get(s).parallelStream().filter(x -> x > finalI && x <= finalI+1000).findAny();
                if( found5.isPresent()) {
                    // Found one:
                    System.out.println("Found a hash: " + ++foundCount + " " + i + " " + hash(i) + " based on " + s + " in " + found5.get() + " " + hash(found5.get()));
                }
            }
            i++;
        }
    }

    private void next1000() {
        IntStream.range(check5limit+1, check5limit+1001).parallel().forEach(this::check5);
        check5limit+=1000;
    }

    private Set<String> check3(int index) {
        String hash = hash(index);
        Set<String> result = new TreeSet<>();
        // now extract any fives from it
        Matcher m = p3.matcher(hash);
        if( m.find() ) {
//            System.out.println("Found a 3: " + index + " " + m.group(1));
            result.add(m.group(1));
        }
        return result;
    }

    private void check5(int index) {
        String hash = hash(index);
        // now extract any fives from it
        Matcher m = p5.matcher(hash);
        while( m.find() ) {
//            System.out.println("Found a 5: " + index + " " + m.group(1));
            fives.put(m.group(1), index);
        }
    }

    private String hash(int index) {
        return hashCache.computeIfAbsent(index, this::part2Hash);
        //return DigestUtils.md5Hex(seed + index);
    }

    private String part1Hash(int i) {
        return DigestUtils.md5Hex(seed + i);
    }

    private String part2Hash(int i) {
        String hash = DigestUtils.md5Hex(seed+i);
//        System.out.println(hash);
        for( int x = 0; x < 2016; x++) {
            hash = DigestUtils.md5Hex(hash);
//            System.out.println(hash);
        }
        return hash;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(new Day14("abc").hash(0));
        new Day14("ihaygndm").run();


    }
}
