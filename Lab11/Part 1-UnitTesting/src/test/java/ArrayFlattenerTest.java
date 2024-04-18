import org.example.ArrayFlattener;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayFlattenerTest {

    @Test
    public static void main(String[] args) {
        ArrayFlattenerTest t = new ArrayFlattenerTest();
        t.testFlattenArray();
    }
    @Test
    public void testFlattenArray() {
        ArrayFlattener flattener = new ArrayFlattener();
        int[][] inputArray = {{1, 3}, {0}, {4, 5, 9}};
        int[] expectedOutput = {1, 3, 0, 4, 5, 9};
        int[] flattenedArray = flattener.flattenArray(inputArray);
        assertArrayEquals(expectedOutput, flattenedArray);
    }

    @Test
    public void testFlattenArrayNullInput() {
        ArrayFlattener flattener = new ArrayFlattener();
        int[][] inputArray = null;
        int[] flattenedArray = flattener.flattenArray(inputArray);
        assertEquals(0, flattenedArray.length);
    }
}
