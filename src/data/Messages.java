package data;

import field.Cell;
import players.Player;

public class Messages {
    public static final String inputMove = "Введите ваш ход: ";
    public static final String wrongMoveFormat = "Ход должен быть введён в формате по типу \"F3\"; ходить можно только на клектки, помеченные " + (new Cell(Values.possible));
    public static final String skipLines = "\n\n\n\n\n\n\n\n";
    public static final String gameOver = "Игра окончена!";

    public static String currentTurn(Player curPlayer) {
        return "Ходит " + curPlayer.getName() + " (" + new Cell(curPlayer.getValue()) + ")";
    }

    public static String score(Player[] players) {
        StringBuilder result = new StringBuilder();
        for (Player player : players) {
            result.append(player.getName()).append(" - ").append(player.getScore()).append("  ");
        }
        return result.toString();
    }

    public static String currentScore(Player[] players) {
        return "Текущий счёт   " + score(players);
    }

    public static String finalScore(Player[] players) {
        return "Итоговый счёт   " + score(players);
    }

    private Messages() {
    }
}
