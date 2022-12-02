package players;

import data.Input;
import data.Messages;
import data.Values;
import field.Field;

public class RealPlayer extends Player {
    public RealPlayer(String name, Field field, Values value) {
        super(name, field, value);
    }

    private int getJ(String line) {
        int j = (int) (line.charAt(0)) - (int) ('A');
        if (j < 0 || j >= Field.fieldSize) {
            throw new IllegalArgumentException("wrong format");
        }
        return j;
    }

    private int getI(String line) {
        int i = (int) (line.charAt(1)) - (int) ('0') - 1;
        if (i < 0 || i >= Field.fieldSize) {
            throw new IllegalArgumentException("wrong format");
        }
        return i;
    }

    @Override
    public void makeMove() {
        while (true) {
            String line = Input.input(Messages.inputMove).toUpperCase();
            try {
                if (line.length() != 2) {
                    throw new IllegalArgumentException("wrong format");
                }
                int i = getI(line);
                int j = getJ(line);
                field.addChip(i, j, value);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(Messages.wrongMoveFormat);
            }
        }
    }
}
