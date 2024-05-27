package Methods;

import java.util.Scanner;
public class DividingSegments {
    double a, b, c, e;

    double f(double x) {
        return java.lang.Math.tan(x)-x-1;
    }

    String getName() {
        return "Метод деления отрезков";
    }

    public void execute(Scanner in) {
        System.out.println("Введите первый конец промежутка: ");
        a = in.nextDouble();
        System.out.println("Введите второй конец промежутка: ");
        b = in.nextDouble();
        System.out.println("Введите точность: ");
        e = in.nextDouble();
        in.close();

        while (Math.abs(b - a) > 2*e) {
            c = (a + b) / 2;
            System.out.println("[" + a + ";" + b + "]");

            if (f(a) * f(c) > 0) {
                a = c;
            } else {
                b = c;
            }
        }

        double result = (a + b) / 2;
        System.out.println("Значение корня с точностью " + e + " равно " + result);
        System.out.println("Значение функци в точке "+ result + " = "+ f(result));
    }
}