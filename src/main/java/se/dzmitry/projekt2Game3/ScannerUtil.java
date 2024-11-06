package se.dzmitry.projekt2Game3;

import java.util.Scanner;

public class ScannerUtil {

    private static Scanner scanner = new Scanner(System.in);

    private ScannerUtil() {
    }

    public static String getInput() {
        return scanner.nextLine();
    }

    public static void setScanner(Scanner newScanner) {
        scanner = newScanner;
    }
}


