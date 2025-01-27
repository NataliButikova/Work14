package ru.netology.game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> registeredPlayers = new ArrayList<>();

    public void register(Player player) {
        registeredPlayers.add(player);
    }

    public boolean isRegistered(Player player) {
        return registeredPlayers.contains(player);
    }

    public int round(String playerName1, String playerName2) throws NotRegisteredException {
        Player player1 = findPlayerByName(playerName1);
        Player player2 = findPlayerByName(playerName2);

        if (player1 == null) {
            throw new NotRegisteredException("Один из игроков не зарегистрирован");
        }
        if (player2 == null) {
            throw new NotRegisteredException("Один из игроков не зарегистрирован");
        }

        int strenght1 = player1.getStrength();
        int strenght2 = player2.getStrength();

        if (strenght1 > strenght2) {
            return 1;
        } else if (strenght1 < strenght2) {
            return 2;
        } else {
            return 0;
        }
    }

    public Player findPlayerByName(String name) {
        for (Player player : registeredPlayers) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }
}