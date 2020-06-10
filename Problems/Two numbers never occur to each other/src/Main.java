import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] array = new int[len];
        boolean flag = true;

        for (int i = 0; i < len ;i++){
            array[i] = scanner.nextInt();
        }

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        for (int i = 1; i < len ;i++){
            if((array[i-1] == n && array[i] == m) || (array[i-1] == m && array[i] == n)){
                flag = false;
                break;
            }
        }

        System.out.println(flag);
    }
}