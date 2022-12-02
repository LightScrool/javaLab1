package players;

import data.Values;
import field.Cell;
import field.Field;
import field.checkDirections.*;

public class Bot extends Player {
    public Bot(String name, Field field, Values value) {
        super(name, field, value);
    }

    private double countValue(int i, int j, Cell[][] fieldData) {
        double result = 0;
        result += new CheckTop().countValue(i, j, fieldData, this.value);
        result += new CheckTopRight().countValue(i, j, fieldData, this.value);
        result += new CheckRight().countValue(i, j, fieldData, this.value);
        result += new CheckBottomRight().countValue(i, j, fieldData, this.value);
        result += new CheckBottom().countValue(i, j, fieldData, this.value);
        result += new CheckBottomLeft().countValue(i, j, fieldData, this.value);
        result += new CheckLeft().countValue(i, j, fieldData, this.value);
        result += new CheckTopLeft().countValue(i, j, fieldData, this.value);
        return result;
    }

    @Override
    public void makeMove() {
        Cell[][] fieldData = field.getData();

        double maxValue = 0;
        int maxI = 0;
        int maxJ = 0;
        for (int i = 0; i < Field.fieldSize; ++i) {
            for (int j = 0; j < Field.fieldSize; ++j) {
                if (fieldData[i][j].getValue() != Values.possible) continue;
                double curValue = countValue(i, j, fieldData);
                if (curValue > maxValue) {
                    maxValue = curValue;
                    maxI = i;
                    maxJ = j;
                }
            }
        }
        field.addChip(maxI, maxJ, value);
    }
}
