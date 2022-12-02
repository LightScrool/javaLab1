package data;

import java.util.Scanner;

public class Input {
    public static final Scanner scanner = new Scanner(System.in);

    public static String input(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
