import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int lower = Integer.MAX_VALUE;

        for(int i = 0; i < len ;i++) {
            int number = scanner.nextInt();
            if (lower > number) {
                lower = number;
            }
        }
        System.out.println(lower);
    }
}