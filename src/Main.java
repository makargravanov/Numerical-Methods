import Methods.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Object> s = new ArrayList<>();
        DividingSegments ds = new DividingSegments();
        s.add(ds);
        TangentMethod tm = new TangentMethod();
        Iter im = new Iter();
        ChordMethod cm = new ChordMethod();
        SimpsonIntegration si = new SimpsonIntegration();
        Gauss ga = new Gauss();
        GaussDeterminant gd = new GaussDeterminant();
        s.add(tm);
        s.add(im);
        s.add(cm);
        s.add(si);
        s.add(ga);
        s.add(gd);
        System.out.println("Выберите метод из списка: ");
        int i = 0;
        for (Object obj: s) {
            System.out.println(i + ") " + obj.getClass().getName());
            i++;
        }
        Scanner in = new Scanner(System.in);
        int j = in.nextInt();

        Object selectedObject = s.get(j);

        if (selectedObject instanceof DividingSegments) {
            ((DividingSegments) selectedObject).execute(in);
        } else if (selectedObject instanceof TangentMethod) {
            ((TangentMethod) selectedObject).execute(in);
        } else if (selectedObject instanceof Iter) {
            ((Iter) selectedObject).execute(in);
        } else if (selectedObject instanceof ChordMethod) {
            ((ChordMethod) selectedObject).execute(in);
        } else if (selectedObject instanceof SimpsonIntegration) {
            ((SimpsonIntegration) selectedObject).execute(in);
        } else if (selectedObject instanceof Gauss) {
        ((Gauss) selectedObject).execute(in);
        }else if (selectedObject instanceof GaussDeterminant) {
            ((GaussDeterminant) selectedObject).execute(in);
        }   else {
            System.out.println("Выбран недопустимый объект");
        }

    }
}