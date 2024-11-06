package se.dzmitry.projekt2Game3;

import se.dzmitry.projekt2Game3.model.Burglar;
import se.dzmitry.projekt2Game3.model.Entity;
import se.dzmitry.projekt2Game3.model.Resident;

import java.util.Scanner;

public class Game {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean running = true;
    public static final String SOVRUMMET = "sovrummet";
    public static final String KITCHEN = "kitchen";
    public static final String VARDAGSRUMMET = "vardagsrummet";
    public static final String HALL = "hall";
    public static final String KONTORET = "kontoret";
    public static final String START = "start";

    private static String currentLocation = START;
    private static final String WRONG_WAY = "Wrong way";
    public static Resident resident;
    public static Burglar burglar;
    private static String userInput;



    public static void main(String[] args) {
        resident = new Resident("Resident", 12, 3);
        burglar = new Burglar("Burglar", 12, 4);
        printMenu();
        vardagsrummet();
        while (running && resident.isAlive() && burglar.isAlive()) {
            String userInput = getUserInput();
            running = processInput(userInput);
        }
        kontoret();

        System.out.println("thank you for playing!");
    }

    private static void printMenu() {
        System.out.println("Welcome to Adventure. A burglar broke into the house!");
    }

    private static String getUserInput() {
        return ScannerUtil.getInput();
    }

    private static void vardagsrummet() {
        if (!currentLocation.equals(VARDAGSRUMMET)) {
            System.out.println("You are in the vardagsrummet. Which direction do you want to move?");
            currentLocation = VARDAGSRUMMET;
        } else {
            System.out.println(WRONG_WAY);
        }
    }

    static boolean processInput(String userInput) {
        switch (userInput) {
            case "go to kitchen" -> kitchen();
            case "go to kontoret" -> kontoret();
            case "go to sovrummet" -> sovrummet();
            case "go to hall" -> hall();
            case "go to vardagsrummet" -> vardagsrummet();
            case "fight" -> fightOneRound();
            case "quit" -> running = false;
            default -> System.out.println("Invalid input");
        }
        return true; // Всегда возвращаем true для продолжения
    }

    private static void sovrummet() {
        if (currentLocation.equals(VARDAGSRUMMET)) {
            System.out.println("Nobody here. Which direction do you want to move?");
            currentLocation = SOVRUMMET;
        } else {
            System.out.println(WRONG_WAY);
        }
    }

    private static void kitchen() {
        if (currentLocation.equals(VARDAGSRUMMET)) {
            System.out.println("Stekpanna is here. Take it!");
            resident.setDamage(resident.getDamage() + 3);
            currentLocation = KITCHEN;
        } else {
            System.out.println(WRONG_WAY);
        }
    }

    private static void hall() {
        if (currentLocation.equals(VARDAGSRUMMET)) {
            System.out.println("You meet a monster! Do you want to fight or go to the kitchen?");
            currentLocation = HALL;
        } else {
            System.out.println(WRONG_WAY);
        }
    }

    private static void kontoret() {
        if (currentLocation.equals(VARDAGSRUMMET)) {
            System.out.println("You have entered the kontoret.");
            currentLocation = KONTORET;
            if (!burglar.isAlive()) {
                System.out.println("The burglar is defeated. Calling the police...");
                callPolice();
            } else {
                System.out.println("The burglar is still alive. You cannot call the police.");
            }
        //} else {
        //    System.out.println(WRONG_WAY);
        }
    }

    private static void fightOneRound() {
        if (!currentLocation.equals(HALL)) {
            System.out.println("Nobody to fight here");
            return;
        }
        executeAttack(resident, burglar);
        if (burglar.isAlive()) {
            executeAttack(burglar, resident);
        } else {
            System.out.println("Go to kontoret and call the police to finish the game!");
            playerTurnAfterVictory(); // Ждем ввода от игрока
            return;
        }

        if (resident.isAlive() && burglar.isAlive()) {
            System.out.println("Do you want to keep fighting?");
        }
        if (!resident.isAlive()) {
            System.out.println("Resident is dead. Game over!");
        }
        if (!burglar.isAlive()) {
            System.out.println("You have defeated the Burglar! Go to kontoret and call the police to finish the game!");
            playerTurnAfterVictory();
        }
    }

    private static void playerTurnAfterVictory() {
        while (true) { // && !currentLocation.equals(KONTORET)) {
            System.out.println("Which direction do you want to move?");
            String input = getUserInput();
            processInput(input);
            System.out.println("Current location:" + currentLocation);
            if (currentLocation.equals(KONTORET) && !burglar.isAlive()) {
                callPolice();
                System.out.println("You won the game!");
                break;
            }
        }
    }

    private static void callPolice() {
        System.out.println("Calling the police");
        running = false;
    }

    public static void executeAttack(Entity attacker, Entity defender) {
        attacker.punch(defender);

        if (defender.isAlive()) {
            System.out.println(defender.getRole() + " is alive");
        } else {
            System.out.println(defender.getRole() + " is dead");
        }
    }

    public static void setUserInput(String userInput) {
        Game.userInput = userInput;
    }
}


