import java.util.Scanner;

public class MatrixDeterminant3x3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[3][3];

        System.out.println("=== 3x3 MATRIX DETERMINANT CALCULATOR ===");
        System.out.println("Please enter the values for a 3x3 matrix:\n");

        // Reading matrix elements from the user
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter value for position [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Displaying the input matrix configuration
        System.out.println("\nYour Input Matrix:");
        displayMatrix(matrix);

        // Calculating and rendering the final determinant result
        int determinant = calculateDeterminant(matrix);
        System.out.println("\nDeterminant of the matrix: " + determinant);

        scanner.close();
    }

    // Renders the matrix layout cleanly in the console
    public static void displayMatrix(int[][] matrix) {
        for (int i = 0; i < 3; i++) {
            System.out.print("[ ");
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("]");
        }
    }

    // Applies Sarrus' Rule / Laplace Expansion for a 3x3 matrix dimension
    public static int calculateDeterminant(int[][] matrix) {
        return matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
             - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
             + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
    }
}
