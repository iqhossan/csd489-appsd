package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayFlattener {

    public static void main(String[] args) {
        int[][] inputArray = {{1, 3}, {0}, {4, 5, 9}};
        ArrayFlattener fl = new ArrayFlattener();
        int[] flattenedArray = fl.flattenArray(inputArray);
        System.out.println(Arrays.toString(flattenedArray));
    }

    public int[] flattenArray(int[][] inputArray){
        List<Integer> flattenedList = new ArrayList<>();
        if(inputArray == null){
            return new int[0];
        }
        //add elements to the flattened list
        for (int[] innerArray : inputArray) {
            for (int num : innerArray) {
                flattenedList.add(num);
            }
        }

      //flattenedList.stream().forEach(num->System.out.println(num));
        // Convert the list to an array
        int[] flattenedArray = new int[flattenedList.size()];
        for (int i = 0; i < flattenedList.size(); i++) {
            flattenedArray[i] = flattenedList.get(i);
        }

        return flattenedArray;
    }
}
