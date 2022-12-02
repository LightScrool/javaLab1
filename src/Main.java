import data.Input;
import data.Menu;
import data.Messages;
import players.Player;

public class Main {
    private static int bestScore = 0;

    private static void printBestScore() {
        System.out.println(Menu.bestText + " " + bestScore);
    }

    private static void pvp() {
        Game game = new Game(false);
        game.startGame();
        Player[] players = game.getPlayers();
        int score1 = players[0].getScore();
        int score2 = players[1].getScore();
        int bestOfGame = Math.max(score1, score2);
        bestScore = Math.max(bestScore, bestOfGame);
        System.out.print(Messages.skipLines);
        System.out.println(Messages.gameOver);
        System.out.println(Messages.finalScore(players));
    }

    private static void bot() {
        Game game = new Game(true);
        game.startGame();
        Player[] players = game.getPlayers();
        bestScore = Math.max(bestScore, players[0].getScore());
        System.out.print(Messages.skipLines);
        System.out.println(Messages.gameOver);
        System.out.println(Messages.finalScore(players));
    }

    private static void help() {
        System.out.println(Menu.helpText);
    }

    private static void exit() {
        System.exit(0);
    }

    private static void handleUndefinedCommand() {
        System.out.println(Menu.undefinedCommand);
    }

    public static void main(String[] args) {
        help();
        while (true) {
            String command = Input.input(Menu.inputCommand);
            switch (command) {
                case Menu.helpAlias -> help();
                case Menu.bestAlias -> printBestScore();
                case Menu.pvpAlias -> pvp();
                case Menu.botAlias -> bot();
                case Menu.exitAlias -> exit();
                default -> handleUndefinedCommand();
            }
        }
    }
}