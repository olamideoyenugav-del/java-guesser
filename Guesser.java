import java.util.Scanner;

public class Guesser {
    private final Scanner input;

    public Guesser() {
        input = new Scanner(System.in);
        String choice;
        do {
            choice = menu();
            if (choice.equals("1")) {
                humanGuesser();
            } else if (choice.equals("2")) {
                computerGuesser();
            } else if (!choice.equals("0")) {
                System.out.println("Please choose 0, 1, or 2.");
            }
        } while (!choice.equals("0"));
    }

    private String menu() {
        System.out.println("\n0) Exit");
        System.out.println("1) Human Guesser");
        System.out.println("2) Computer guesser");
        System.out.print("\nPlease enter 0-2: ");
        return input.nextLine().trim();
    }

    private void humanGuesser() {
        System.out.println("human guesser");
        int target = (int) (Math.random() * 100) + 1;
        int attempts = 0;
        while (true) {
            System.out.print((attempts + 1) + ") Please enter a number: ");
            String entry = input.nextLine().trim();
            int guess;
            try {
                guess = Integer.parseInt(entry);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number from 1 to 100.");
                continue;
            }
            if (guess < 1 || guess > 100) {
                System.out.println("Please enter a number from 1 to 100.");
                continue;
            }
            attempts++;
            if (guess < target) {
                System.out.println("too low...");
            } else if (guess > target) {
                System.out.println("too high...");
            } else {
                System.out.println("got it!");
                System.out.println("Very good! You needed " + attempts + " guesses.");
                return;
            }
        }
    }

    private void computerGuesser() {
        System.out.println("computer guesser");
        System.out.println("Think of a whole number from 1 to 100.");
        int low = 1;
        int high = 100;
        int attempts = 0;
        while (low <= high) {
            int guess = low + (high - low) / 2;
            attempts++;
            System.out.println(attempts + ") I guess " + guess);
            System.out.print("Too (H)igh, too (L)ow, or (C)orrect? ");
            String response = input.nextLine().trim().toLowerCase();
            if (response.equals("c")) {
                System.out.println("Got it in " + attempts + " guesses!");
                return;
            } else if (response.equals("h")) {
                high = guess - 1;
            } else if (response.equals("l")) {
                low = guess + 1;
            } else {
                System.out.println("Please enter H, L, or C.");
                attempts--;
            }
        }
        System.out.println("Those answers conflict. Please try again with a number from 1 to 100.");
    }

    public static void main(String[] args) {
        new Guesser();
    }
}
