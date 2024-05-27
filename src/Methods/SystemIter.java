package Methods;

import java.util.Scanner;

public class SystemIter {
    final int N = 4;
    double[][] A = new double[N][N];
    double[] B = new double[N];
    double[] X1 = new double[N];
    double[] X2 = new double[N];
    double E;

    public void execute(Scanner scanner) {
        inp(scanner);
        double T = teta(A);
        System.out.println("""
                Тета ищется так:
                1)Для каждой строки матрицы вычисляется сумма абсолютных значений всех элементов строки.
                2)Эта сумма делится на диагональный элемент строки.
                3)Максимальное значение среди всех строк сохраняется.
                4)Из этого максимального значения вычитается единица.""");
        System.out.println("TETA = " + T);

        for (int i = 0; i < N; i++) {
            X2[i] = 0;
        }

        int iteration = 0;
        do {
            System.arraycopy(X2, 0, X1, 0, N);
            step(X1, X2);
            iteration++;
            System.out.println("Iteration " + iteration + ":");
            for (int i = 0; i < N; i++) {
                System.out.printf("X(%d) = %.6f\n", i + 1, X2[i]);
            }
        } while (norm(X1, X2) > E * (1 - T) / T);

        System.out.println("Final solution:");
        for (int i = 0; i < N; i++) {
            System.out.printf("X(%d) = %.6f\n", i + 1, X2[i]);
        }

        check();
    }

    private void inp(Scanner scanner) {
        System.out.println("ВВЕДИТЕ КОЭФФИЦИЕНТЫ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("A(%d,%d)= ", i + 1, j + 1);
                A[i][j] = scanner.nextDouble();
            }
        }
        System.out.println("ВВЕДИТЕ СВОБ. ЧЛЕНЫ");
        for (int i = 0; i < N; i++) {
            System.out.printf("B(%d)= ", i + 1);
            B[i] = scanner.nextDouble();
        }
        System.out.println("ВВЕДИТЕ ТОЧНОСТЬ");
        E = scanner.nextDouble();
    }

    private double teta(double[][] A) {
        double T = 0;
        for (int i = 0; i < N; i++) {
            double S = 0;
            for (int j = 0; j < N; j++) {
                S += Math.abs(A[i][j]);
            }
            S /= A[i][i];
            if (T < S) {
                T = S;
            }
        }
        return T - 1;
    }

    private double norm(double[] X, double[] Y) {
        double D = 0;
        for (int i = 0; i < N; i++) {
            double S = Math.abs(X[i] - Y[i]);
            if (S > D) {
                D = S;
            }
        }
        return D;
    }

    private void step(double[] X, double[] Y) {
        for (int i = 0; i < N; i++) {
            Y[i] = -B[i];
            for (int j = 0; j < N; j++) {
                Y[i] += A[i][j] * X[j];
            }
            Y[i] = X[i] - Y[i] / A[i][i];
        }
    }

    private void check() {
        System.out.println("Проверка решения:");
        for (int i = 0; i < N; i++) {
            double S = 0;
            for (int j = 0; j < N; j++) {
                S += A[i][j] * X2[j];
            }
            System.out.printf("Row %d: %.6f = %.6f\n", i + 1, S, B[i]);
        }
    }
}