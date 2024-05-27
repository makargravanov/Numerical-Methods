package Methods;

import Formatting.Class.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TangentMethod {
    double a,b,m,e,x;
    ArrayList<Double> arr = new ArrayList<>();
    Table table = new Table();
    double f(double x){
        //return java.lang.Math.tan(0.5*x+0.1)-x*x;
        return x*x*x+x-5;
    }
    double f1(double x){
        //return (1/(2*java.lang.Math.cos(0.5*x+0.1)*java.lang.Math.cos(0.5*x+0.1)))-2*x;
        return 3*x*x+1;
    }
    String getName(){
        return "Метод касательных(Ньютона)";
    }
    public void execute(Scanner in){
        System.out.println("Введите первый конец промежутка: ");
        a = in.nextDouble();
        System.out.println("Введите второй конец промежутка: ");
        b = in.nextDouble();
        System.out.println("Введите точность: ");
        e = in.nextDouble();
        System.out.println("Введите параметр M: ");
        m = in.nextDouble();
        System.out.println("Введите начальное приближение: ");
        x = in.nextDouble();
        in.close();
        int i=0;
        while (java.lang.Math.abs(f(x)/m)>e){
            i++;
            System.out.println(i);
            arr.add((Double) x);
            arr.add((Double) f(x));
            arr.add((Double) f1(x));
            arr.add((Double) java.lang.Math.abs(f(x)/m));

            x=x-f(x)/f1(x);
        }

        arr.add((Double) x);
        arr.add((Double) f(x));
        arr.add((Double) f1(x));
        arr.add((Double) java.lang.Math.abs(f(x)/m));

        List<String> headers = new ArrayList<>();;
        headers.add("xi");
        headers.add("f(xi)");
        headers.add("f'(xi)");
        headers.add("mod(f(xi))/m");
        try{
            table.drawCustomTable(arr, headers);
            System.out.println("Значение корня с точностью "+e+" равно "+x);
            double result = f(x);
            System.out.println("Значение функции F(x) равно "+result);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
