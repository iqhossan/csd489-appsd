package testWithMockito;

import org.example.ArrayFlattenerService;
import org.example.ArrayReversor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayReversorTest {

    @Test
    public void testReverseArrayValidInput() {
        // Create a mock of ArrayFlattenerService
        ArrayFlattenerService flattenerService = Mockito.mock(ArrayFlattenerService.class);
        Mockito.when(flattenerService.flattenArray(Mockito.any(int[][].class)))
                .thenReturn(new int[]{1, 3, 0, 4, 5, 9}); // Mock flattened array

        ArrayReversor reversor = new ArrayReversor(flattenerService);
        int[][] inputArray = {{1, 3}, {0}, {4, 5, 9}};
        int[] reversedArray = reversor.reverseArray(inputArray);

        int[] expectedOutput = {9, 5, 4, 0, 3, 1};
        assertArrayEquals(expectedOutput, reversedArray);
    }

    @Test
    public void testReverseArrayNullInput() {
        // Create a mock of ArrayFlattenerService
        ArrayFlattenerService flattenerService = Mockito.mock(ArrayFlattenerService.class);
        Mockito.when(flattenerService.flattenArray(null))
                .thenReturn(new int[0]); // Mock flattened array for null input

        ArrayReversor reversor = new ArrayReversor(flattenerService);
        int[][] inputArray = null;
        int[] reversedArray = reversor.reverseArray(inputArray);

        assertEquals(0, reversedArray.length);
    }
}
