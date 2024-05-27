package Methods;

import java.util.Scanner;

public class Iter {
    public void execute(Scanner scanner) {
        double x1, x2, e;

        System.out.println("Введите точность:");
        e = scanner.nextDouble();

        x1 = 3;
        x2 = f(x1);

        while (Math.abs(x2 - x1) > e) {
            System.out.println(x1);
            x1 = x2;
            x2 = f(x1);
            System.out.println(x2);
        }

        System.out.println("Значение корня с точностью " + e + " равно " + x2);
    }

    static double f(double x) {
        return 2 - 0.5 / Math.pow(Math.E, x); //(1 / (2 + x * x))
    }
}
