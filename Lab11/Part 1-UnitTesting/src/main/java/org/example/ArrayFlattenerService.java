package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayFlattenerService {

    public int[] flattenArray(int[][] inputArray){
        IntStream flattenedStream = Arrays.stream(inputArray).flatMapToInt(Arrays::stream);
        //flattenedStream.forEach(n->System.out.println(n));
        int[] data = flattenedStream.toArray();
        return data;
    }
}
