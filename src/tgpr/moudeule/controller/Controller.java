package tgpr.moudeule.controller;

public abstract class Controller {
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