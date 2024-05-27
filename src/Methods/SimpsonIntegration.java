package Methods;

import Formatting.Class.Table;

import java.util.ArrayList;
import java.util.Scanner;

public class SimpsonIntegration {
    public void execute(Scanner scanner) {
        Double a, b; // концы промежутка интегрирования
        Double x, h, s;
        int i,n, m;
        ArrayList<Double> arr = new ArrayList<>();

        Table table = new Table();

        System.out.println("Введите концы промежутка:");
        a = scanner.nextDouble();
        b = scanner.nextDouble();

        System.out.println("Введите M:");
        m = scanner.nextInt();
        n=m*2;

        h = (b - a) / n;
        s = (f(a) - f(b)) / 2;

        boolean first = true;
        for (i = 1; i <= n / 2; i++) {
            x = a + 2 * i * h;
            s = s + f(x) + 2 * f(x - h);
            arr.add(x);
            arr.add(f(x));
        }
        ArrayList<String> headers = new ArrayList<>();
        headers.add("xi");
        headers.add("f(xi)");
        table.drawCustomTable(arr, headers);
        s = s * 2 * h / 3;
        System.out.println("Значение интеграла: " + s);
    }

    public static double f(double x) {
        return java.lang.Math.sin(x);
    }
}
