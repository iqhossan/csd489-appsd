package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayReversor {

    ArrayFlattenerService service;

    public ArrayReversor(ArrayFlattenerService service){
        this.service = service;
    }

    public static void main(String[] args){
        ArrayReversor reversor = new ArrayReversor(new ArrayFlattenerService());
        int[][] inputArray = {{1,3},{0},{4,5,9}};
        int[] outputArray = reversor.reverseArray(inputArray);
        System.out.println(Arrays.toString(outputArray));
    }

    public int[] reverseArray(int[][] inputArray){
        int[] flattenArray  = service.flattenArray(inputArray);
        int[] reverseData = IntStream.range(0,flattenArray.length)
                        .map(i->flattenArray[flattenArray.length-1-i])
                                .toArray();
       return reverseData;
    }
}
