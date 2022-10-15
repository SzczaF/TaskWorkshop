package pl.coderslab;

import java.nio.file.Path;
import java.util.Scanner;

public class TaskManager {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static Path filePath;

    public static void main(String[] args) {


        initialFileRead("tasks.csv");
        showGreeteng();
        showMenu();

        while(true) {

        }

    }

    private static void showGreeteng() {
        System.out.println(ConsoleColors.GREEN);
        System.out.println("--------------------");
        System.out.println("*** TASK MANAGER ***");
        System.out.println("--------------------");
    }

    private static void showMenu() {
        System.out.println(ConsoleColors.CYAN);
        System.out.print("Wybierz opcję:");
        System.out.println(ConsoleColors.BLUE_BOLD);
        System.out.println("add | remove | list | exit");
    }

    private static void initialFileRead(String file) {

    }
}