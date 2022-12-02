package data;

public class Menu {
    private static String generateHelpLine(String alias, String descriptions) {
        return alias + " - " + descriptions + "\n";
    }

    public static final String helpAlias = "/h";
    public static final String bestAlias = "/r";
    public static final String pvpAlias = "/p";
    public static final String botAlias = "/b";
    public static final String exitAlias = "/e";

    public static final String helpDescriptions = "Список команд";
    public static final String bestDescriptions = "Лучший счёт за сессию";
    public static final String pvpDescriptions = "Играть в режиме PvP";
    public static final String botDescriptions = "Играть против бота";
    public static final String exitDescriptions = "Выйти";

    public static final String inputCommand = "Введите команду: ";
    public static final String bestText = "Лучший счёт за сессию:";
    public static final String undefinedCommand = "Неизвестная команда, введите \"" + helpAlias + "\" для справки";
    public static final String helpText = generateHelpLine(helpAlias, helpDescriptions)
            + generateHelpLine(bestAlias, bestDescriptions)
            + generateHelpLine(pvpAlias, pvpDescriptions)
            + generateHelpLine(botAlias, botDescriptions)
            + generateHelpLine(exitAlias, exitDescriptions);


    private Menu() {
    }
}
