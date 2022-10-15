package pl.coderslab;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TaskManager {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static Path filePath;
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static ConsoleColors color;
    private static final String YES_VALUE = "t";
    private static final String NO_VALUE = "n";
    private static final String YES_NO_OPTIONS = color.WHITE + YES_VALUE + "/" + NO_VALUE;


    public static void main(String[] args) {

        SCANNER.useDelimiter("\n");
        initialFileRead("tasks.csv");
        showGreeting();
        runMenu();

    }

    private static void addTask() {
        SCANNER.useDelimiter("\n");

        Task task = new Task();
        System.out.print(color.YELLOW_BRIGHT);

        System.out.print("Podaj opis zadania: ");
        String desc = SCANNER.next();
        task.setTaskDescription(desc);

        System.out.print("Podaj datę realizacji zadania: ");
        String date = SCANNER.next();
        task.setTaskDate(date);


        System.out.print("Czy zadanie jest istotne? (" + YES_NO_OPTIONS + color.YELLOW_BRIGHT + "): ");

        while (true) {

            String importantVal = SCANNER.next();
            if (Objects.equals(importantVal, YES_VALUE)) {
                task.setTaskImportant(true);
                break;
            } else if (Objects.equals(importantVal, NO_VALUE)) {
                task.setTaskImportant(false);
                break;
            } else {
                System.out.println(color.RED + "Nieprawidłowa opcja! wpisz " + YES_NO_OPTIONS);
            }
        }
        taskList.add(task);
        System.out.print(color.GREEN_BOLD_BRIGHT);
        System.out.println();
//        System.out.println("-----------------------");
        System.out.println("Zadanie dodane do listy");
//        System.out.println("-----------------------");
        resetColor();
    }

    private static void showTaskList() {
        System.out.println("\n" + color.CYAN + "Twoja aktualna lista zadań (" + color.RED + "czerwona" + color.CYAN + " data oznacza istotne zadanie): ");
        resetColor();
        int taskNumber = 0;
        for (Task task : taskList) {
            taskNumber++;
            System.out.println(taskNumber + " : " + task.toString());
            resetColor();
        }
        System.out.println();
    }

    private static void showGreeting() {
        System.out.print(color.GREEN);
        System.out.println("--------------------");
        System.out.println("*** TASK MANAGER ***");
        System.out.println("--------------------");
        resetColor();
    }

    private static void runMenu() {
        System.out.print(color.CYAN + "Wybierz opcję: ");
        System.out.print(color.BLUE_BOLD + "add | remove | list | exit -> ");
        resetColor();

        selectAndRunMenuOption();
    }

    private static void selectAndRunMenuOption() {

        String input = SCANNER.next();

        switch (input) {
            case "add":
                addTask();
                runMenu();
                break;
            case "remove":
                removeTask(); // TODO
                runMenu();
                break;
            case "list":
                showTaskList();
                runMenu();
                break;
            case "exit":
//                saveTaskstoFile(); TODO
                System.out.println(color.RED + "Twoja lista została zapisana. KONIEC PROGRAMU!");
                break;
            default:
                System.out.println(color.YELLOW + "Nieprawidłowa komenda!!!");
                runMenu();
        }

    }

    private static void removeTask() {
        showTaskList();

        System.out.println(color.CYAN + "Podaj numer zadania do " + color.RED_UNDERLINED + "usunięcia" + color.CYAN + ":");

        while (!SCANNER.hasNextInt()) {
            SCANNER.next();
            System.out.print(color.YELLOW + "Nieprawidłowe dane!!! \nPodaj prawidłowy numer numer zadania (Musi być większy lub równy 1: )");
        }

        int taskNumberToRemove = SCANNER.nextInt();
        int index = taskNumberToRemove - 1;

        boolean taskExists = index > 0 && index <taskList.size();

        if (taskExists) {
            System.out.println(color.CYAN + "Czy na pewno usunąć zadanie nr: " + taskNumberToRemove + "?");
            while (true) {

                String confirm = SCANNER.next();
                if (Objects.equals(confirm, YES_VALUE)) {
                    taskList.remove(index);
                    System.out.println(color.GREEN_BOLD_BRIGHT + "Zadanie " + taskNumberToRemove + " zostało usunięte z listy");
                    break;
                } else if (Objects.equals(confirm, NO_VALUE)) {
                    break;
                } else {
                    System.out.println(color.RED + "Nieprawidłowa opcja! wpisz " + YES_NO_OPTIONS);
                }
            }

        } else {
            System.out.println("Zadanie o numerze " + taskNumberToRemove + " nie isnieje");
        }
        System.out.println();

    }

    private static void initialFileRead(String file) {
        filePath = Paths.get(file);

        try (Scanner s = new Scanner(filePath)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] taskItems = line.split(", ");

                Task task = new Task();
                task.setTaskDescription(taskItems[0]);
                task.setTaskDate(taskItems[1]);
                task.setTaskImportant(Boolean.parseBoolean(taskItems[2]));

                taskList.add(task);
            }

        } catch (IOException e) {

            System.out.println(color.RED_BACKGROUND + "Błąd wczytywania pliku zadań!!!");
            resetColor();
            throw new RuntimeException(e);
        }

    }

    private static void resetColor() {
        System.out.print(color.RESET);
    }


}