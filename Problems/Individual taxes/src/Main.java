import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[] incomes = new double[n];
        double[] taxes = new double[n];

        int mayor = 0;

        for (int i = 0; i < n; i++){
            incomes[i] = scanner.nextDouble();
        }

        for (int i = 0; i < n; i++) {
            taxes[i] = scanner.nextDouble();
            if (i > 0 && incomes[mayor] * taxes[mayor] / 100 < incomes[i] * taxes[i] / 100) {
                mayor = i;
            }

        }

        System.out.println(mayor+1);

    }
}