package Methods;
import java.util.Scanner;

public class GaussDeterminant {
    final int N = 4;

    public void execute(Scanner scanner) {
        double[][] A = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("A[" + (i + 1) + ", " + (j + 1) + "] = ");
                A[i][j] = scanner.nextDouble();
            }
        }

        double det = calculateDeterminant(A);
        System.out.println("DET = " + det);
    }

    public double calculateDeterminant(double[][] A) {
        int N = A.length;
        double det = 1;

        for (int k = 0; k < N - 1; k++) {
            System.out.println("k = " + (k + 1) + ":");

            if (A[k][k] == 0) {
                boolean swapped = false;
                for (int i = k + 1; i < N; i++) {
                    if (A[i][k] != 0) {
                        swapRows(A, k, i);
                        System.out.println("Меняем строку " + (k + 1) + " со строкой " + (i + 1));
                        swapped = true;
                        break;
                    }
                }
                if (!swapped) {
                    return 0;
                }
            }

            for (int i = k + 1; i < N; i++) {
                double factor = A[i][k] / A[k][k];
                for (int j = k + 1; j < N; j++) {
                    A[i][j] -= A[k][j] * factor;
                }
                A[i][k] = 0;
            }

            printMatrix(A);
        }

        for (int i = 0; i < N; i++) {
            det *= A[i][i];
        }

        return det;
    }

    public void swapRows(double[][] A, int row1, int row2) {
        int N = A.length;
        for (int j = 0; j < N; j++) {
            double temp = A[row1][j];
            A[row1][j] = A[row2][j];
            A[row2][j] = temp;
        }
    }

    public void printMatrix(double[][] A) {
        int N = A.length;
        System.out.println("Текущая матрица:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%10.4f ", A[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}