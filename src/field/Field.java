package field;

import data.Values;
import field.checkDirections.*;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Field {
    public static final int fieldSize = 8;
    private final Cell[][] data = new Cell[fieldSize][fieldSize];

    public Cell[][] getData() {
        return data;
    }

    public Field() {
        for (int i = 0; i < fieldSize; ++i) {
            for (int j = 0; j < fieldSize; ++j) {
                data[i][j] = new Cell();
            }
        }

        data[fieldSize / 2][fieldSize / 2].setValue(Values.white);
        data[fieldSize / 2 - 1][fieldSize / 2 - 1].setValue(Values.white);

        data[fieldSize / 2 - 1][fieldSize / 2].setValue(Values.black);
        data[fieldSize / 2][fieldSize / 2 - 1].setValue(Values.black);
    }

    public void print() {
        String separator = "  ";

        // Вывод букв
        System.out.print(" " + separator);
        for (int i = 0; i < fieldSize; i++) {
            System.out.print((char) ((int) 'A' + i) + separator);
        }
        System.out.println();


        // Вывод цифр и поля
        for (int i = 0; i < fieldSize; i++) {
            System.out.print(i + 1 + separator);
            for (Cell cell : data[i]) {
                System.out.print(cell + separator);
            }
            System.out.println();
        }
    }

    public static Values getEnemyValue(Values value) {
        Values enemyValue;
        if (Values.black.equals(value)) {
            enemyValue = Values.white;
        } else if (Values.white.equals(value)) {
            enemyValue = Values.black;
        } else {
            throw new IllegalArgumentException(value + " must be either " + Values.white + " or " + Values.black);
        }
        return enemyValue;
    }

    private boolean isPossibleNeighborsCheck(Values value, int i, int j) {
        Values enemyValue = getEnemyValue(value);

        boolean checkRight = enemyValue.equals(data[i][max(j - 1, 0)].getValue());
        boolean checkLeft = enemyValue.equals(data[i][min(j + 1, fieldSize - 1)].getValue());
        boolean checkTop = enemyValue.equals(data[max(i - 1, 0)][j].getValue());
        boolean checkBottom = enemyValue.equals(data[min(i + 1, fieldSize - 1)][j].getValue());

        return (checkRight || checkLeft || checkTop || checkBottom);
    }

    private boolean isPossibleDirectionsCheck(Values value, int i, int j) {
        if (new CheckTop().isPossible(i, j, this.data, value)) {
            return true;
        }
        if (new CheckTopRight().isPossible(i, j, this.data, value)) {
            return true;
        }
        if (new CheckRight().isPossible(i, j, this.data, value)) {
            return true;
        }
        if (new CheckBottomRight().isPossible(i, j, this.data, value)) {
            return true;
        }
        if (new CheckBottom().isPossible(i, j, this.data, value)) {
            return true;
        }
        if (new CheckBottomLeft().isPossible(i, j, this.data, value)) {
            return true;
        }
        if (new CheckLeft().isPossible(i, j, this.data, value)) {
            return true;
        }
        if (new CheckTopLeft().isPossible(i, j, this.data, value)) {
            return true;
        }
        return false;
    }

    public boolean isPossible(Values value, int i, int j) {
        // 0. Marked as possible?
        if (Values.possible.equals(data[i][j].getValue())) {
            return true;
        }

        // 1. Empty check
        if (!Values.empty.equals(data[i][j].getValue())) {
            return false;
        }

        // 2. Neighbors check
        if (!isPossibleNeighborsCheck(value, i, j)) {
            return false;
        }

        // 3. Ray check
        return isPossibleDirectionsCheck(value, i, j);
    }

    public void markPossible(Values value) {
        for (int i = 0; i < fieldSize; ++i) {
            for (int j = 0; j < fieldSize; ++j) {
                if (isPossible(value, i, j)) {
                    data[i][j].setValue(Values.possible);
                }
            }
        }
    }

    public void unMarkPossible() {
        for (int i = 0; i < fieldSize; ++i) {
            for (int j = 0; j < fieldSize; ++j) {
                if (Values.possible.equals(data[i][j].getValue())) {
                    data[i][j].setValue(Values.empty);
                }
            }
        }
    }

    public int countValues(Values value) {
        int count = 0;
        for (int i = 0; i < fieldSize; ++i) {
            for (int j = 0; j < fieldSize; ++j) {
                if (value.equals(data[i][j].getValue())) {
                    count++;
                }
            }
        }
        return count;
    }

    public void addChip(int i, int j, Values value) throws IllegalArgumentException {
        if (!isPossible(value, i, j)) {
            throw new IllegalArgumentException("It is impossible to add chip here!");
        }
        new CheckTop().paint(i, j, this.data, value);
        new CheckTopRight().paint(i, j, this.data, value);
        new CheckRight().paint(i, j, this.data, value);
        new CheckBottomRight().paint(i, j, this.data, value);
        new CheckBottom().paint(i, j, this.data, value);
        new CheckBottomLeft().paint(i, j, this.data, value);
        new CheckLeft().paint(i, j, this.data, value);
        new CheckTopLeft().paint(i, j, this.data, value);
        data[i][j].setValue(value);
    }
}
