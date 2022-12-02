package field.checkDirections;

public class CheckRight extends CheckDirection {
    @Override
    protected int newI(int prevI) {
        return prevI;
    }

    @Override
    protected int newJ(int prevJ) {
        return prevJ + 1;
    }
}