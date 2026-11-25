import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    static int sum = 0;
    static final int[][] DIRECT = new int[][]{
            {0, 1},     // Right
            {1, 0},     // Up
            {0, -1},    // Left
            {-1, 0}     // Down
    };

    public static void printArray(boolean[][] array) {
        System.out.println();
        for (boolean[] row : array) {
            for (boolean value : row) {
                System.out.print(value ? "*" : "_");
            }
            System.out.println();
        }
    }

    public static boolean[][] readInput(String filepath) {
        Scanner input = null;
        try {
            input = new Scanner(new File(filepath));
        } catch (FileNotFoundException e) {
            System.out.println("***  Can't open " + filepath + " ***");
            System.exit(1);
        }
        // Read the first line to get the dimensions of the array
        int rows = input.nextInt();
        int cols = input.nextInt(); input.nextLine();
        // System.out.println("Rows: " + rows + ", Cols: " + cols);  // Debugging line
        boolean[][] array = new boolean[rows][cols];
        // Read the rest of the file to fill the array
        for (int i = 0; i < rows; i++) {
            if (input.hasNext()) {
                String nextLine = input.nextLine();
                // System.out.println(nextLine);
                for (int j = 0; j < cols; j++) {
                    char nextChar = nextLine.charAt(j);
                    array[i][j] = (nextChar == '*');  // * is bacteria
                    // if (array[i][j]) System.out.println(i + " " + j + " " + nextLine);
                }
            }
        }
        return array;
    }

    public static void DFS(boolean[][] slide, int row, int col) {
        if (!slide[row][col]) {  // Does not contain bacteria
            return;
        }
        sum++;  // Contains bacteria
        slide[row][col] = false;  // Mark as visited
        // System.out.println(row + " " + col);
        for (int[] dir : DIRECT) {
            int nextR = row + dir[0];
            int nextC = col + dir[1];
            DFS(slide, nextR, nextC);
        }
    }

    public static void main(String[] args) {
        boolean[][] array = readInput("src/main/resources/DATA_50x50.txt");
        // Print the array to verify it was read correctly
        printArray(array);
        DFS(array, 2, 31);
        System.out.println(sum);
    }
}
