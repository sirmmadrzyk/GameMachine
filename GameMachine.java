package practice.dwunastylipca.first.vendingmachine;

import java.util.LinkedList;
import java.util.List;

public class GameMachine {
    private List<Game> gamesAvailable;
    private List<Game> gamesSold = new LinkedList<>();
    private int money = 0;

    public int getMoney() {
        return money;
    }

    public List<Game> getGamesSold() {
        return gamesSold;
    }

    public GameMachine(List<Game> gamesAll) {
        this.gamesAvailable = gamesAll;
    }

    public void addGame(Game game){
        this.gamesAvailable.add(game);
    }

    public int sellGame(Game game, int money){

        if(!gamesAvailable.contains(game)){
            throw new GameMachineException("Nie znaleziono gry");
        }
        if(money < game.getPrice()){
            throw new GameMachineException("Nie masz wystarczająco środków na zakup wybranej gry");
        }

        if( (gamesAvailable.contains(game)) && money >= game.getPrice() ){
            gamesAvailable.remove(game);
            gamesSold.add(game);
            this.money += game.getPrice();
            return (money - game.getPrice());
        }
        return -1;

    }

    public void printAllGames(){

        System.out.println("------------------------------------------------------------------");
        if(gamesAvailable.size()>0) {
            System.out.println("Dostępne pozycje: ");
            for (Game game : gamesAvailable) {
                System.out.println("* " + game.getTitle() + "\n" +
                        " cena: " + game.getPrice() + " PLN");
            }
            System.out.println("------------------------------------------------------------------");
        }

        if(gamesSold.size()>0) {
            System.out.println("Pozycje niedostępne: ");
            for (Game game : gamesSold) {
                System.out.println("* " + game.getTitle() + " cena " + game.getPrice() + " PLN");
            }
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void printAvailableGames(){

        System.out.println("------------------------------------------------------------------");
        System.out.println("Dostępne pozycje: ");
        if(gamesAvailable.size() >0) {
            for (Game game : gamesAvailable) {
                System.out.println("* " + game.getTitle() + " cena " + game.getPrice() + " PLN");
            }
        }else{
            System.out.println("Brak dostępnych pozycji. Przepraszamy!!!");
        }
        System.out.println("------------------------------------------------------------------");

    }

    public List<Game> getGamesAvailable() {
        return gamesAvailable;
    }

    @Override
    public String toString() {
        return "GameMachine{" +
                "gamesAvailable=" + gamesAvailable +
                ", gamesSold=" + gamesSold +
                ", money=" + money +
                '}';
    }
}
