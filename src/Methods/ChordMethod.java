package Methods;

import Formatting.Class.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ChordMethod {
    public void execute(Scanner input) {
        Table table = new Table();
        double a, b, m, e, x;

        System.out.println("Введите концы исходного промежутка:");
        a = input.nextDouble();
        b = input.nextDouble();

        System.out.println("Введите точность:");
        e = input.nextDouble();

        System.out.println("Введите параметр M:");
        m = input.nextDouble();

        System.out.println("Введите начальное приближение:");
        x = input.nextDouble();

        if (x == b) {
            b = a;
        }
        ArrayList <Double> data = new ArrayList<>();
        while (Math.abs(f(x) / m) > e) {
            data.add(x);
            data.add(f(x));
            data.add(Math.abs(f(x) / m));
            x = x - f(x) / (f(x) - f(b)) * (x - b);
        }
        data.add(x);
        data.add(f(x));
        data.add(Math.abs(f(x) / m));

        List<String> headers = new ArrayList<>();
        headers.add("xi");
        headers.add("f(xi)");
        headers.add("mod(f(xi)/m");
        try {
            table.drawCustomTable(data, headers);
            System.out.println("Значение корня функции с точностью " + e + " равно " + x);
            System.out.println("Значение функции F(x) = " + f(x));
        }catch (IllegalArgumentException ex){
        System.out.println(ex.getMessage());
    }
    }

    public double f(double x) {
        return Math.sqrt(1 + x*x) - x*x;
    }


}