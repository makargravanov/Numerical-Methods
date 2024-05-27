package Methods;

import java.util.Scanner;

public class SQU {
    final int N = 4;
    double[][] A = new double[N][N];
    double[][] T = new double[N][N];
    double[] D = new double[N];
    double[] B = new double[N];
    double[] X = new double[N];
    double[] Y = new double[N];

    public void execute(Scanner scanner) {
        inp(scanner);
        printMatrix("Матрица системы A", A);
        calculateTD();
        printMatrix("Матрица T", T);
        printVector("Вектор D", D);
        solveSystem();
        printVector("Вектор Y", Y);
        solveDTX();
    }

    private void inp(Scanner scanner) {
        System.out.println("ВВЕДИТЕ КОЭФФИЦИЕНТЫ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("A(" + (i + 1) + "," + (j + 1) + ")=");
                A[i][j] = scanner.nextDouble();
            }
        }
        System.out.println("ВВЕДИТЕ СВОБ. ЧЛЕНЫ");
        for (int i = 0; i < N; i++) {
            System.out.print("B(" + (i + 1) + ")=");
            B[i] = scanner.nextDouble();
        }
        scanner.close();
    }

    private int sign(double x) {
        return x < 0 ? -1 : 1;
    }

    private void calculateTD() {
        for (int i = 0; i < N; i++) {
            T[i][i] = A[i][i];
            for (int k = 0; k < i; k++) {
                T[i][i] -= T[k][i] * T[k][i] * D[k];
            }
            D[i] = sign(T[i][i]);
            T[i][i] = Math.sqrt(Math.abs(T[i][i]));
            for (int j = i + 1; j < N; j++) {
                T[i][j] = A[i][j];
                for (int k = 0; k < i; k++) {
                    T[i][j] -= T[k][i] * T[k][j] * D[k];
                }
                T[i][j] = T[i][j] / (D[i] * T[i][i]);
            }
        }
    }

    private void solveSystem() {
        for (int i = 0; i < N; i++) {
            Y[i] = B[i];
            for (int k = 0; k < i; k++) {
                Y[i] -= T[k][i] * Y[k];
            }
            Y[i] = Y[i] / T[i][i];
        }
    }

    private void solveDTX() {
        for (int i = N - 1; i >= 0; i--) {
            X[i] = Y[i] * D[i];
            for (int k = i + 1; k < N; k++) {
                X[i] -= T[i][k] * X[k];
            }
            X[i] = X[i] / T[i][i];
            System.out.printf("X(%d)= %.3f%n", i + 1, X[i]);
        }
    }

    private void printMatrix(String title, double[][] matrix) {
        System.out.println(title + ":");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%8.3f", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printVector(String title, double[] vector) {
        System.out.println(title + ":");
        for (int i = 0; i < N; i++) {
            System.out.printf(" %8.3f", vector[i]);
        }
        System.out.println();
    }
}