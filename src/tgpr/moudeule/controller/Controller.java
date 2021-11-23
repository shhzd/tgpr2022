package tgpr.moudeule.controller;

public abstract class Controller {

    static final int NUMBER_DISPLAY_LINE = 14;

    public abstract void run();

    public static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}