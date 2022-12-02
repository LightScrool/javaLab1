package field.checkDirections;

public class CheckBottomRight extends CheckDirection {
    @Override
    protected int newI(int prevI) {
        return prevI + 1;
    }

    @Override
    protected int newJ(int prevJ) {
        return prevJ + 1;
    }
}