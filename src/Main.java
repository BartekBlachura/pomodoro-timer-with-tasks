import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pomodoro pomodoro = new Pomodoro();

        int selectedOption = 8;

        System.out.println("pomodoro timer");

        Scanner scanner = new Scanner(System.in);
        while (selectedOption != 9) {
            pomodoro.displayMenu();
            if (pomodoro.isWorkPhase()) {
                System.out.println("\nIt's time to work!\n");
            }
            else {
                System.out.println("\nIt's time to rest!\n");
            }

            System.out.print("input number: ");
            selectedOption = scanner.nextInt();

            switch (selectedOption) {
                case 0 -> {
                    pomodoro.start();
                    System.out.println();
                }
                case 1 -> {
                    System.out.println("current work time: " + pomodoro.getWorkTimeMinutes());
                    System.out.print("input new work time: ");
                    pomodoro.setWorkTimeMinutes(scanner.nextInt());
                }
                case 2 -> {
                    System.out.println("current short break time: " + pomodoro.getShortBreakTimeMinutes());
                    System.out.print("input new short break time: ");
                    pomodoro.setShortBreakTimeMinutes(scanner.nextInt());
                }
                case 3 -> {
                    System.out.println("current long break time: " + pomodoro.getLongBreakTimeMinutes());
                    System.out.print("input new long break time: ");
                    pomodoro.setLongBreakTimeMinutes(scanner.nextInt());
                }
                case 9 -> System.out.println("thanks for use");
                default -> System.out.println("input correct number");
            }
        }
        scanner.close();
    }
}
