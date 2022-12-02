package field.checkDirections;

import data.Values;
import field.Cell;
import field.Field;

import static field.Field.getEnemyValue;

public abstract class CheckDirection {
    private boolean indexesAreCorrect(int i, int j) {
        return i >= 0 && j >= 0 && i < Field.fieldSize && j < Field.fieldSize;
    }

    protected abstract int newI(int prevI);

    protected abstract int newJ(int prevJ);

    public boolean isPossible(int i, int j, Cell[][] data, Values value) {
        Values enemyValue = getEnemyValue(value);
        boolean foundEnemy = false;
        i = newI(i);
        j = newJ(j);
        while (indexesAreCorrect(i, j)) {
            Values curValue = data[i][j].getValue();
            if (curValue == value) {
                return false;
            }
            if (curValue == enemyValue) {
                foundEnemy = true;
                break;
            }

            i = newI(i);
            j = newJ(j);
        }
        if (!foundEnemy) return false;

        i = newI(i);
        j = newJ(j);
        while (indexesAreCorrect(i, j)) {
            Values curValue = data[i][j].getValue();
            if (curValue == value) {
                return true;
            }

            i = newI(i);
            j = newJ(j);
        }
        return false;
    }

    public void paint(int i, int j, Cell[][] data, Values value) {
        if (!isPossible(i, j, data, value)) return;

        Values enemyValue = getEnemyValue(value);
        i = newI(i);
        j = newJ(j);
        while (indexesAreCorrect(i, j)) {
            if (data[i][j].getValue() == value) {
                break;
            }
            if (data[i][j].getValue() == enemyValue) {
                data[i][j].setValue(value);
            }

            i = newI(i);
            j = newJ(j);
        }
    }

    private boolean isOnEdge(int i, int j) {
        return i == 0 || j == 0 || i == Field.fieldSize - 1 || j == Field.fieldSize - 1;
    }

    private boolean isOnAngle(int i, int j) {
        return (i == 0 || i == Field.fieldSize - 1) && (j == 0 || j == Field.fieldSize - 1);
    }

    public double countValue(int i, int j, Cell[][] data, Values value) {
        double result = 0;

        if (!isPossible(i, j, data, value)) return result;

        if (isOnEdge(i, j)) {
            result += isOnAngle(i, j) ? 0.8 : 0.4;
        }

        Values enemyValue = getEnemyValue(value);
        i = newI(i);
        j = newJ(j);
        while (indexesAreCorrect(i, j)) {
            if (data[i][j].getValue() == value) {
                break;
            }
            if (data[i][j].getValue() == enemyValue) {
                result += isOnEdge(i, j) ? 2 : 1;
            }

            i = newI(i);
            j = newJ(j);
        }

        return result;
    }
}
