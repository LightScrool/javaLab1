package field.checkDirections;

public class CheckLeft extends CheckDirection {
    @Override
    protected int newI(int prevI) {
        return prevI;
    }

    @Override
    protected int newJ(int prevJ) {
        return prevJ - 1;
    }
}