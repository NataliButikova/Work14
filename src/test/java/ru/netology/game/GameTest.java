package ru.netology.game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    @Test
    public void shouldRegisterPlayer() {
        Game game = new Game();
        Player player = new Player(4, "Natalia", 15);
        game.register(player);

        boolean expected = true;
        boolean actual = game.isRegistered(player);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldRoundWithRegisteredPlayers() throws NotRegisteredException {
        Game game = new Game();
        Player player1 = new Player(4, "Natalia", 100);
        Player player2 = new Player(5, "Maria", 75);
        game.register(player1);
        game.register(player2);

        game.round(player1.getName(), player2.getName());

        int expected = 1;
        int actual = game.round(player1.getName(), player2.getName());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldRoundIfSecondPlayerStronger() throws NotRegisteredException {
        Game game = new Game();
        Player player1 = new Player(4, "Butikova", 75);
        Player player2 = new Player(5, "Maria", 100);
        game.register(player1);
        game.register(player2);

        game.round(player2.getName(), player2.getName());

        int expected = 2;
        int actual = game.round(player1.getName(), player2.getName());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldRoundWithUnRegisteredPlayers() {
        Game game = new Game();
        Player player1 = new Player(4, "Butikova", 99);
        Player player2 = new Player(5, "Maria", 75);
        Exception exception = assertThrows(NotRegisteredException.class, () -> {
            game.round("Неизвестный игрок", "Неизвестный игрок");
        });

        String expected = "Один из игроков не зарегистрирован";
        String actial = exception.getMessage();
        Assertions.assertEquals(expected, actial);
    }

    @Test
    public void shouldRoundWithUnRegisteredPlayer1() {
        Game game = new Game();
        Player player1 = new Player(5, "Maria", 100);
        Player player2 = new Player(4, "Butikova", 99);
        game.register(player2);
        Exception exception = assertThrows(NotRegisteredException.class, () -> {
            game.round("Неизвестный игрок", "Natalia");
        });

        String expected = "Один из игроков не зарегистрирован";
        String actial = exception.getMessage();
        Assertions.assertEquals(expected, actial);
    }

    @Test
    public void shouldRoundWithUnRegisteredPlayer2() {
        Game game = new Game();
        Player player1 = new Player(5, "Maria", 100);
        Player player2 = new Player(4, "Natalia", 99);
        game.register(player1);
        Exception exception = assertThrows(NotRegisteredException.class, () -> {
            game.round("Maria", "Неизвестный игрок");
        });

        String expected = "Один из игроков не зарегистрирован";
        String actial = exception.getMessage();
        Assertions.assertEquals(expected, actial);
    }

    @Test
    public void shouldRoundWithTheSamePlayersStats() throws NotRegisteredException {
        Game game = new Game();
        Player player1 = new Player(4, "Natalia", 100);
        Player player2 = new Player(5, "Maria", 100);

        game.register(player1);
        game.register(player2);

        int excepted = 0;
        int actual = game.round(player1.getName(), player2.getName());

        Assertions.assertEquals(excepted, actual);
    }

}