package practice.dwunastylipca.first.vendingmachine;

import java.util.LinkedList;
import java.util.List;

public class UserWallet {
    private String name;
    private int funds;
    private List<Game> boughtGames = new LinkedList<>();

    public UserWallet(String name, int funds) {
        this.funds = funds;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getFunds() {
        return funds;
    }

    public List<Game> getBoughtGames() {
        return boughtGames;
    }

    public void addGame(Game game) {
        boughtGames.add(game);
        System.out.println("Pozycja " + game.getTitle() + " została dodana do Twojej kolekcji");
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    @Override
    public String toString() {
        return "UserWallet{" +
                "name='" + name + '\'' +
                ", funds=" + funds +
                ", boughtGames=" + boughtGames +
                '}';
    }
}
