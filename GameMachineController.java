package practice.dwunastylipca.first.vendingmachine;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GameMachineController {
    private GameMachine gameMachine;
    private UserWallet userWallet;
    private boolean newUserLoopOn = true;
    private boolean optionsLoopOn = true;

    public GameMachineController() {
        createGameMachine(collectGames());

        while (newUserLoopOn) {

            handleNewUser();
            while (optionsLoopOn) {
                handleOptions();

            }
        }
    }

    private List<Game> collectGames() {

        List<Game> gamesList = new LinkedList<>();
        gamesList.add(new Game("Heroes III The Might and Magic", 10));
        gamesList.add(new Game("Mario Bros", 5));
        gamesList.add(new Game("The Witcher", 15));
        gamesList.add(new Game("Fifa 2010", 9));
        gamesList.add(new Game("Robocop", 12));
        gamesList.add(new Game("War Craft", 9));
        return gamesList;
    }

    private void handleNewUser() {

        try {
            newUser();
        } catch (RuntimeException exception) {

            System.out.println("Niepoprawne dane... spróbuj jeszcze raz...");
            handleNewUser();

        }

        if (newUserLoopOn == false) {

            return;

        }

        System.out.println("Witaj " + userWallet.getName() + " twoje środki to " + userWallet.getFunds() + " PLN");

    }

    private void handleOptions() {

        try {
            options();
        } catch (RuntimeException exception) {

            System.out.println("Niepoprawne dane... spróbuj jeszcze raz...");
            handleOptions();

        }


    }

    private void createGameMachine(List<Game> games) {

        this.gameMachine = new GameMachine(games);

    }

    private void newUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w naszym automacie do gier!!!");
        System.out.println("Aby zakończyć wpisz: EXIT");
        System.out.println("Wpisz proszę swoje imię..");
        String name = scanner.nextLine();
        if (name.equalsIgnoreCase("exit")) {
            optionsLoopOn = false;
            newUserLoopOn = false;
            return;
        }

        System.out.println("Określ prosze środki pieniężne jakimi dysponujesz (PLN)..");
        int wallet = scanner.nextInt();
        this.userWallet = new UserWallet(name, wallet);
        optionsLoopOn = true;

    }

    private void emptyScannerBuffer(Scanner scanner) {
        System.out.println("Naciśnij dowolny klawisz....");
        scanner.next();
    }

    private Game handleFindGame() {
        Game game;
        try {
            game = findGame();
        } catch (GameMachineException e) {

            System.out.println(e.getMessage());
            System.out.println("Spróbuj jeszcze raz");
            return handleFindGame();

        }

        System.out.println("Znaleziono pozycje " + game.getTitle() + " cena: " + game.getPrice() + "PLN");
        System.out.println(game);
        return game;

    }

    private void gameTransaction(Game game) {

        userWallet.setFunds(gameMachine.sellGame(game, userWallet.getFunds()));
        userWallet.addGame(game);
    }

    private Game findGame() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz nazwę gry, która Cię interesuje...");
        String gameName = scanner.nextLine().toLowerCase();
        List<Game> foundGames = new LinkedList<>();
        for (Game game : gameMachine.getGamesAvailable()) {
            if (game.getTitle().toLowerCase().contains(gameName))
                foundGames.add(game);
        }

        if (foundGames.size() == 0) {
            throw new GameMachineException("Nie ma takiej gry...");
        } else if (foundGames.size() > 1) {
            throw new GameMachineException("Dostępne sa " + foundGames.size() + " gry, określ bardziej precyzyjnie...");
        } else {

            return foundGames.get(0);

        }


    }


    private int printOptions(Scanner scanner) {

        System.out.println(userWallet.getName() + ", wybierz jedną z dostępnych opcji:");
        System.out.println("1 -> pokaż wszystkie gry");
        System.out.println("2 -> pokaż tylko gry dostępne do kupienia ");
        System.out.println("3 -> wpisz tytuł, gry którą chcesz kupić ");
        System.out.println("4 -> statystyki użytkownika ");
        System.out.println("9 -> zakończ");
        return scanner.nextInt();
    }

    private boolean isEnoughFunds(Game chosenGame){
        if (userWallet.getFunds() < chosenGame.getPrice()) {
            System.out.println("Nie masz wystarczająco środków aby zakupić tę pozycje...");
            System.out.println("Idź do pracy... znajdź sponsora..");
            return false;
        }
        return true;
    }

    private boolean makeDecision(Game chosenGame){
        Scanner scannerPurchase = new Scanner(System.in);
        System.out.println("Aby potwierdzić zakup naciśnij Y");
        System.out.println("Aby anulować naciśnij dowolny klawisz...");
        String answer = scannerPurchase.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            gameTransaction(chosenGame);
            return true;

        } else {
            System.out.println("Transakcja anulowana...");
            return false;
        }

    }

    private void executeOptions(int option) {
        switch (option) {
            case 1:
                System.out.println("Wszystkie gry: ");
                gameMachine.printAllGames();
                break;
            case 2:
                System.out.println("Gry dostępne do kupienia: ");
                gameMachine.printAvailableGames();
                break;
            case 3:
                Game chosenGame = handleFindGame();
                if(isEnoughFunds(chosenGame)){
                   makeDecision(chosenGame);
                }
                break;
            case 4:
                System.out.println(userWallet);
                break;
            case 9:
                optionsLoopOn = false;
                break;


        }
    }

    private void options() {
        Scanner scanner = new Scanner(System.in);
        int option = printOptions(scanner);
        executeOptions(option);
        emptyScannerBuffer(scanner);
    }

    public GameMachine getGameMachine() {
        return gameMachine;
    }

    public UserWallet getUserWallet() {
        return userWallet;
    }
}
