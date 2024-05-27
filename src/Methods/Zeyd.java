package Methods;

import java.util.Scanner;

public class Zeyd {
    final int N = 4;
    double[][] A = new double[N][N];
    double[] B = new double[N];
    double[] X1 = new double[N];
    double[] X2 = new double[N];
    double E;

    public void execute(Scanner scanner) {
        inp(scanner);
        double T = teta(A);
        System.out.println("TETA = " + T);

        for (int i = 0; i < N; i++) {
            X2[i] = 0;
        }

        int iteration = 0;
        do {
            System.arraycopy(X2, 0, X1, 0, N);
            step(X1, X2);
            iteration++;
            printIteration(iteration, X2);
        } while (norma(X1, X2) > E * (1 - T) / T);

        for (int i = 0; i < N; i++) {
            System.out.println("X(" + (i + 1) + ") = " + X2[i]);
        }

        prowerka();
    }

    private void inp(Scanner scanner) {
        System.out.println("ВВЕДИТЕ КОЭФФИЦИЕНТЫ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("A(" + (i + 1) + "," + (j + 1) + ") = ");
                A[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("ВВЕДИТЕ СВОБ. ЧЛЕНЫ");
        for (int i = 0; i < N; i++) {
            System.out.print("B(" + (i + 1) + ") = ");
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

    private double norma(double[] X, double[] Y) {
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
            for (int j = i; j < N; j++) {
                Y[i] += A[i][j] * X[j];
            }
            for (int j = 0; j < i; j++) {
                Y[i] += A[i][j] * Y[j];
            }
            Y[i] = X[i] - Y[i] / A[i][i];
        }
    }

    private void prowerka() {
        for (int i = 0; i < N; i++) {
            double S = 0;
            for (int j = 0; j < N; j++) {
                S += A[i][j] * X2[j];
            }
            System.out.println(S + " = " + B[i]);
        }
    }

    private void printIteration(int iteration, double[] X) {
        System.out.print("X^" + iteration + ": ");
        for (int i = 0; i < N; i++) {
            System.out.printf("X%d = %.5f ", i + 1, X[i]);
        }
        System.out.println();
    }
}
