package Methods;

import java.util.Scanner;

public class LagrangeInterpolation {

    public void execute(Scanner scanner) {

        final int N;
        System.out.println("Введите количество точек: ");
        N = scanner.nextInt() - 1;
        double[] X = new double[N + 1];
        double[] Y = new double[N + 1];

        // Values to evaluate
        double[] UTable = {0.340, 0.670, 0.800, 1.060, 1.400, 1.770};
        for (int i = 0; i <= N; i++) {
            System.out.print("Введите X" + i + ", Y" + i + ": ");
            X[i] = scanner.nextDouble();
            Y[i] = scanner.nextDouble();
        }

        System.out.print("X = ");
        double U = scanner.nextDouble();

        double S = 0;

        System.out.println("Значение интерп. мн-на в точке " + U + " равно " + lagrange(U, N, X, Y));

        double[] A = new double[N + 1];
        double[] S_coeff = new double[N + 1];

        for (int k = 0; k <= N; k++) {
            for (int i = 1; i <= N; i++) A[i] = 0;
            A[0] = 1;
            double W = 1;
            int D = 0;

            for (int i = 0; i <= N; i++) {
                if (i != k) {
                    W *= (X[k] - X[i]);
                    mul(X[i], D, A);
                    D++;
                }
            }

            for (int i = 0; i <= N; i++) {
                S_coeff[i] += Y[k] * A[i] / W;
            }
        }

        System.out.print("Коэффициенты интерполяционного многочлена: ");
        for (int i = 0; i <= N; i++) {
            System.out.print(S_coeff[i] + " ");
        }
        System.out.println();

        System.out.println(" x\tf(x)\t\tL(x)\t\t|f(x) - L(x)|");
        for (int i = 0; i <= N; i++) {
            double Lx = 0;
            for (int k = 0; k <= N; k++) {
                double W = 1;
                for (int j = 0; j <= N; j++) {
                    if (j != k) {
                        W *= (X[i] - X[j]) / (X[k] - X[j]);
                    }
                }
                Lx += Y[k] * W;
            }
            double fxu = lagrange(UTable[i], N, X, Y);
            //System.out.printf("%.3f\t%.5f\t%.5f\t%.5f\n", UTable[i], fxu, Lx, Math.abs(fxu - Lx));
        }
    }

    private void mul(double A, int K, double[] B) {
        for (int i = K + 1; i >= 1; i--) {
            B[i] = B[i - 1] - A * B[i];
        }
        B[0] = -A * B[0];
    }

    private double lagrange(double U, int N, double[] X, double[] Y) {
        double S = 0;
        for (int k = 0; k <= N; k++) {
            double W = 1;
            for (int i = 0; i <= N; i++) {
                if (i != k) {
                    W *= (U - X[i]) / (X[k] - X[i]);
                }
            }
            S += Y[k] * W;
        }
        return S;
    }
}