package Methods;

import java.util.Scanner;

public class GaussInverse {
    final int N = 4;

    public void execute(Scanner scanner) {
        double[][] A = new double[N][N];
        double[][] B = new double[N][N];

        // Ввод матрицы A и формирование матрицы B как единичной
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("A(" + (i + 1) + "," + (j + 1) + ")=");
                A[i][j] = scanner.nextDouble();
                B[i][j] = (i == j) ? 1 : 0;
            }
        }

        // Приведение A к треугольному виду
        for (int k = 0; k < N - 1; k++) {
            if (A[k][k] == 0) {
                boolean swapped = false;
                for (int i = k + 1; i < N; i++) {
                    if (A[i][k] != 0) {
                        swapRows(A, k, i);
                        swapRows(B, k, i);
                        swapped = true;
                        break;
                    }
                }
                if (!swapped) {
                    System.out.println("МАТРИЦА НЕ ОБРАТИМА");
                    return;
                }
            }

            for (int j = k + 1; j < N; j++) {
                A[k][j] /= A[k][k];
            }
            for (int j = 0; j < N; j++) {
                B[k][j] /= A[k][k];
            }

            for (int i = k + 1; i < N; i++) {
                for (int j = k + 1; j < N; j++) {
                    A[i][j] -= A[k][j] * A[i][k];
                }
                for (int j = 0; j < N; j++) {
                    B[i][j] -= B[k][j] * A[i][k];
                }
            }

            // Вывод промежуточных значений
            System.out.println("После шага " + (k + 1) + ":");
            printMatrix(A, "A");
            printMatrix(B, "B");
        }

        if (A[N - 1][N - 1] == 0) {
            System.out.println("МАТРИЦА НЕ ОБРАТИМА");
            return;
        }

        for (int j = 0; j < N; j++) {
            B[N - 1][j] /= A[N - 1][N - 1];
        }
        A[N - 1][N - 1] = 1;

        // Приведение матрицы A к единичной
        for (int k = N - 1; k >= 1; k--) {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < N; j++) {
                    B[i][j] -= B[k][j] * A[i][k];
                }
            }
        }

        // Печать обратной матрицы
        System.out.println("Обратная матрица:");
        printMatrix(B, "B");
    }

    private void swapRows(double[][] matrix, int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    private void printMatrix(double[][] matrix, String name) {
        System.out.println("Матрица " + name + ":");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%6.3f ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}