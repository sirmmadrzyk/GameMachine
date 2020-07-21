package practice.dwunastylipca.first.vendingmachine;

/*
Tworzymy aplikację dla automatu, w którym można kupić gry komputerowe. Automat
przechowuje kilka najnowszych tytułów. Każda gra opisana jest za pomocą nazwy i ceny.
Działanie automatu jest następujące:
● użytkownik wpisuje na ekranie nazwę gry, którą chce wypożyczyć
● użytkownik wrzuca do do automatu pieniądze (zakładamy pełne złotówki, bez
groszy)
● Użytkownik powinien zobaczyć w odpowiedzi, że grę udało się kupić lub, że dana gra
nie
● została odnaleziona.
● Dodatkowo jeśli użytkownik wrzucił zbyt mało pieniędzy powinien
● zobaczyć komunikat o zbyt małej kwocie, a jeśli wpłacił zbyt dużo pieniędzy, to
powinien
● otrzymać resztę.
Zdefiniuj następujące klasy:
Game - pojedyncza gra,
GameMachine - przechowuje kilka gier i pozwala je kupić. W sytuacji, gdy
użytkownik próbuje zakupić grę, której nie ma w automacie lub wpłacił zbyt mało
pieniędzy rzuć w metodzie obsługującej zakup odpowiednie wyjątki zdefiniowane
przez siebie,GameMachineController - klasa interakcji z użytkownikiem, jest to jedyne
miejsce w programie, gdzie powinno być wyświetlanie oraz wczytywanie danych od
użytkownika.
Oprócz tego może istnieć dodatkowa klasa startowa lub inne klasy, które uznasz za
stosowne
 */
public class Demo {
    public static void main(String[] args) {

        GameMachineController game = new GameMachineController();

    }
}
