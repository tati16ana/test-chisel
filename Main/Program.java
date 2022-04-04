import java.util.Scanner;
public class Program {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Введите число a: ");
        double a = console.nextInt();
        System.out.print("Введите число b: ");
        double b = console.nextInt();
        System.out.print("Введите число d: ");
        double d = console.nextInt();
        double s = (a + b + d) / 3;
        System.out.println("Среднее арифметическое чисел: " + s);
        double fl = (a + d);
        double one = Math.pow(fl, 2);
        double two = Math.pow(b, 3);
        double otv = (one - two);
        System.out.println("Разность удвоенной суммы первого и третьего чисел и утроенного второго числа = " + otv);
    }
}