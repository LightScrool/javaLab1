package field;

import data.Values;
import data.Chars;
import data.Colors;

public class Cell {
    private Values value;
    private char character;
    private String color;

    public Cell(Values _value) {
        setValue(_value);
    }

    public Cell() {
        this(Values.empty);
    }

    public Values getValue() {
        return value;
    }

    public void setValue(Values value) {
        switch (value) {
            case empty -> {
                this.character = Chars.empty;
                this.color = Colors.empty;
            }
            case black -> {
                this.character = Chars.black;
                this.color = Colors.black;
            }
            case white -> {
                this.character = Chars.white;
                this.color = Colors.white;
            }
            case possible -> {
                this.character = Chars.possible;
                this.color = Colors.possible;
            }
            default -> throw new IllegalArgumentException(value + " is illegal as value of Cell");
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return color + character + Colors._default;
    }
}
