package players;


import data.Values;
import field.Field;

public abstract class Player {
    protected Field field;
    protected Values value;
    protected int score = 0;
    protected String name;

    public Player(String name, Field field, Values value) {
        this.name = name;
        this.field = field;
        this.value = value;
    }

    public Values getValue() {
        return value;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void makeMove() {
    }
}
