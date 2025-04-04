import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int rowsA, rowsB, colsA, colsB;
        Scanner scanner = new Scanner(System.in);

        try {
            // get dimensions through user input
            System.out.println("Enter dimensions of matrix A (format: row,column)");
            String tmp = scanner.nextLine();

            // extracting integers from the input
            String[] dimensions = tmp.split(",");
            rowsA = Integer.parseInt(dimensions[0].trim());
            colsA = Integer.parseInt(dimensions[1].trim());

            System.out.println("Enter dimensions of matrix B (format: row,column)");
            tmp = scanner.nextLine();

            dimensions = tmp.split(",");
            rowsB = Integer.parseInt(dimensions[0].trim());
            colsB = Integer.parseInt(dimensions[1].trim());

            // validating dimensions, only valid when number of columns in matrix 1 is equal to rows in matrix 2
            if (colsA != rowsB) {
                System.out.println("BAD REQUEST: the provided dimensions can not be multiplied");
                return;
            }

            // declare matrices according to user input
            int[][] matrixA = new int[rowsA][colsA];
            int[][] matrixB = new int[rowsB][colsB];
            int[][] matrixC = new int[rowsA][colsB]; // A(n,m) * B(m,p) = C(n,p)

            // input for matrix A
            System.out.println("Matrix A:");
            matrixInput(matrixA, rowsA, colsA, scanner);

            // input for matrix B
            System.out.println("Matrix B:");
            matrixInput(matrixB, rowsB, colsB, scanner);

            // initialise matrixC with zeros
            initMatrixWithZeros(matrixC, rowsA, colsB);

            // multiply
            calculate(matrixA, matrixB, matrixC, rowsA, colsA, colsB);

            // displaying the result matrix
            System.out.println("Matrix C: ");
            displayMatrix(matrixC, rowsA, colsB);

        } catch (Exception e) {
            System.out.println("ERROR: Something went wrong");
        }
    }

    public static void matrixInput(int[][] matrix, int rows, int cols, Scanner scanner) {
        int counter_row;
        int counter_col;

        System.out.println("--------");
        for (int i = 0; i < rows; i++) {
            counter_row = i + 1;
            for (int j = 0; j < cols; j++) {
                counter_col = j + 1;
                System.out.println("Enter value for row" + counter_row + " column " + counter_col);
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    public static void displayMatrix(int[][] matrix, int rows, int cols) {
        for (int p = 0; p < rows; p++) {
            System.out.print("\n| ");
            for (int q = 0; q < cols; q++) {
                System.out.print(matrix[p][q] + " ");
            }
            System.out.print("|");
        }
    }

    public static void calculate(int[][] matA, int[][] matB, int[][] matC, int rowsA, int colsA, int colsB) {
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    matC[i][j] += matA[i][k] * matB[k][j];
                }
            }
        }
    }

    public static void initMatrixWithZeros(int[][] matr, int rows, int cols) {
        for (int l = 0; l < rows; l++) {
            for (int m = 0; m < cols; m++) {
                matr[l][m] = 0;
            }
        }
    }
}