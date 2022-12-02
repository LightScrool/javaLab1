import data.GameStatuses;
import data.Messages;
import data.Values;
import field.Field;
import players.Bot;
import players.Player;
import players.RealPlayer;

public class Game {
    private final Field field = new Field();
    private final Player[] players = new Player[2];
    private int currentTurn = 0;
    private GameStatuses status = GameStatuses.continuing;

    public Game(boolean bot) {
        if (bot) {
            players[0] = new RealPlayer("ИГРОК", field, Values.white);
            players[1] = new Bot("КОМПЬЮТЕР", field, Values.black);
        } else {
            players[0] = new RealPlayer("ИГРОК №1", field, Values.white);
            players[1] = new RealPlayer("ИГРОК №2", field, Values.black);
        }
    }

    private void changeTurn() {
        currentTurn = (currentTurn + 1) % 2;
    }

    private Player getCurPlayer() {
        return players[currentTurn];
    }

    private void updateGameData() {
        // Updating scores
        for (Player player : players) {
            player.setScore(field.countValues(player.getValue()));
        }

        // Updating status
        if (field.countValues(Values.possible) == 0) {
            status = currentTurn == 0 ? GameStatuses.secondWon : GameStatuses.firstWon;
        } else if (field.countValues(Values.empty) == 0) {
            if (players[0].getScore() < players[1].getScore()) {
                status = GameStatuses.secondWon;
            } else if (players[0].getScore() > players[1].getScore()) {
                status = GameStatuses.firstWon;
            } else {
                status = GameStatuses.draw;
            }
        } else {
            status = GameStatuses.continuing;
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public void startGame() {
        while (true) {
            // Preparation
            field.markPossible(getCurPlayer().getValue());

            // Display
            System.out.print(Messages.skipLines);
            System.out.println(Messages.currentScore(players));
            System.out.println(Messages.currentTurn(getCurPlayer()));
            field.print();

            // Checking game status
            updateGameData();
            if (status != GameStatuses.continuing) {
                break;
            }

            // Action
            getCurPlayer().makeMove();

            // Afterwards
            field.unMarkPossible();
            changeTurn();
        }
    }
}
