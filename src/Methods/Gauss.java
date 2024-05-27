package Methods;

import java.util.Scanner;

public class Gauss {

    private final int N = 4;
    private double[][] A = new double[N][N];
    private double[] B = new double[N];
    private double[] X = new double[N];

    public void execute(Scanner scanner) {

        // Ввод коэффициентов системы
        System.out.println("ВВЕДИТЕ КОЭФФИЦИЕНТЫ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("A(%d,%d)=", i + 1, j + 1);
                A[i][j] = scanner.nextDouble();
            }
        }

        // Ввод свободных членов
        System.out.println("ВВЕДИТЕ СВОБ. ЧЛЕНЫ");
        for (int i = 0; i < N; i++) {
            System.out.printf("B(%d)=", i + 1);
            B[i] = scanner.nextDouble();
        }

        System.out.println("Исходная система:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf(" %.2f ", A[i][j]);
            }
            System.out.printf("| %.2f\n", B[i]);
        }

        solve();
        System.out.println("Шаг 4: ");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf(" %.2f ", A[i][j]);
            }
            System.out.printf("| %.2f\n", B[i]);
        }

        // Вывод решения
        for (int i = 0; i < N; i++) {
            System.out.printf("X(%d) = %.3f\n", i + 1, X[i]);
        }

    }

    private void solve() {
        for (int k = 0; k < N - 1; k++) {
            if (A[k][k] == 0) {
                int i = k + 1;
                while (A[i][k] == 0 && i < N) {
                    i++;
                }
                if (i < N) {
                    reverseRows(k, i);
                }
            }

            divideRow(k);

            for (int i = k + 1; i < N; i++) {
                clearRow(k, i);
            }
        }

        divideRow(N - 1);

        for (int i = N - 1; i >= 0; i--) {
            X[i] = B[i];
            for (int j = i + 1; j < N; j++) {
                X[i] -= A[i][j] * X[j];
            }
        }
    }

    private void reverseRows(int k, int r) {
        double temp;
        for (int j = 0; j < N; j++) {
            temp = A[k][j];
            A[k][j] = A[r][j];
            A[r][j] = temp;
        }

        temp = B[k];
        B[k] = B[r];
        B[r] = temp;
    }

    private void divideRow(int k) {
        double divisor = A[k][k];
        if (divisor != 0) {
            for (int j = k + 1; j < N; j++) {
                A[k][j] /= divisor;
            }
            B[k] /= divisor;
            A[k][k] = 1;
        }
    }

    private void clearRow(int k, int r) {
        double factor = A[r][k];
        for (int j = k + 1; j < N; j++) {
            A[r][j] -= A[k][j] * factor;
        }
        B[r] -= B[k] * factor;
        A[r][k] = 0;
    }
}