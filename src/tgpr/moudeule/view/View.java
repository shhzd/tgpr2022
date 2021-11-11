package tgpr.moudeule.view;

import org.beryx.textio.ReadHandlerData;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.swing.SwingTextTerminal;
import tgpr.moudeule.MoudeuleApp;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public abstract class View {
    public static class ActionInterruptedException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    public static class Action {
        private final char action;
        private int number = -1;

        public Action(char action) {
            this.action = action;
        }

        public Action(char action, int number) {
            this.action = action;
            this.number = number;
        }

        public char getAction() {
            return action;
        }

        public int getNumber() {
            return number;
        }
    }

    private static final TextIO textIO;
    private static final SwingTextTerminal terminal;

    static {
        System.setProperty("org.beryx.textio.TextTerminal", "org.beryx.textio.swing.SwingTextTerminal");
        textIO = TextIoFactory.getTextIO();
        terminal = (SwingTextTerminal) textIO.getTextTerminal();
        terminal.registerHandler("ESCAPE", View::keypressed);
        centerTerminal();
    }

    private static ReadHandlerData keypressed(SwingTextTerminal terminal) {
        throw new ActionInterruptedException();
    }

    private static void centerTerminal() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = terminal.getFrame().getWidth();
        int left = (screenSize.width - width) / 2;
        int height = terminal.getFrame().getHeight();
        int top = (screenSize.height - height) / 2;
        terminal.getFrame().setLocation(left, top);
    }

    public void error(String err) {
        terminal.executeWithPropertiesPrefix("error", t -> t.println(err));
    }

    public void warning(String warn) {
        terminal.executeWithPropertiesPrefix("warn", t -> t.println(warn));
    }

    public void close() {
        terminal.dispose();
    }

    public void print(Object obj) {
        terminal.print(obj == null ? "null" : obj.toString());
    }

    public void println(Object obj) {
        terminal.println(obj == null ? "null" : obj.toString());
    }

    public void printf(String format, Object... args) {
        terminal.printf(format, args);
    }

    public void clear() {
        terminal.resetToOffset(0);
    }

    public String readLine() {
        return readLine(false);
    }

    public String readLine(boolean masking) {
        return terminal.read(masking);
    }

    public void pause() {
        try {
            readLine();
        } catch (ActionInterruptedException e) {
            // do nothing
        }
    }

    public void pause(String msg) {
        terminal.print(msg);
        pause();
    }

    public void pausedWarning(String warn) {
        warning(warn + " Press ENTER or ESC to continue.");
        pause();
    }

    public void pausedError(String err) {
        error(err + " Press ENTER or ESC to continue.");
        pause();
    }

    public Object ifNull(Object obj, Object nullDefault) {
        return obj == null ? nullDefault : obj;
    }

    public String ifNull(Object obj, String nullDefault) {
        return obj == null ? nullDefault : obj.toString();
    }

    public Action doAskForAction(int maxNumber, String prompt, String actionsPattern) {
        char letter = 0;
        int number = 0;
        boolean valid = false;
        println(prompt);
        while (!valid) {
            valid = false;
            String input = readLine();
            if (input.matches(actionsPattern)) {
                letter = Character.toUpperCase(input.charAt(0));
                if (input.length() > 1) {
                    number = Integer.parseInt(input.substring(1));
                    if (number < 1 || number > maxNumber)
                        error("invalid number");
                    else
                        valid = true;
                } else
                    valid = true;
            } else {
                error("invalid action");
            }
        }
        return new Action(letter, number);
    }

    public String askString(String prompt, String defaultValue, boolean masked) {
        String value = null;
        print(prompt);
        String input = readLine(masked);
        if (input.isEmpty())
            value = defaultValue;
        else if (input.trim().toLowerCase().equals("null"))
            value = null;
        else
            value = input;
        return value;
    }

    public String askString(String prompt, String defaultValue) {
        return askString(prompt, defaultValue, false);
    }

    public LocalDate askDate(String prompt, LocalDate defaultValue) {
        LocalDate value = null;
        boolean hasError;
        do {
            hasError = false;
            print(prompt);
            String input = readLine();
            try {
                if (input.isEmpty())
                    value = defaultValue;
                else if (input.trim().toLowerCase().equals("null"))
                    value = null;
                else
                    value = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                error("invalid date");
                hasError = true;
            }
        } while (hasError);
        return value;
    }

    public Boolean askBoolean(String prompt, Boolean defaultValue) {
        Boolean value = null;
        boolean hasError;
        do {
            hasError = false;
            print(prompt);
            String input = readLine();
            try {
                if (input.isEmpty())
                    value = defaultValue;
                else if (input.trim().toLowerCase().equals("null"))
                    value = null;
                else
                    value = Boolean.parseBoolean(input);
            } catch (Exception e) {
                error("invalid boolean");
                hasError = true;
            }
        } while (hasError);
        return value;
    }

    public Integer askInteger(String prompt, Integer defaultValue) {
        Integer value = null;
        boolean hasError;
        do {
            hasError = false;
            print(prompt);
            String input = readLine();
            try {
                if (input.isEmpty())
                    value = defaultValue;
                else if (input.trim().toLowerCase().equals("null"))
                    value = null;
                else
                    value = Integer.parseInt(input);
            } catch (Exception e) {
                error("invalid integer");
                hasError = true;
            }
        } while (hasError);
        return value;
    }

    public void showErrors(List<String> errors) {
        error("\nERRORS:");
        for (var err : errors)
            error("* " + err);
        pausedError("\nTry again");
    }

    protected void displayHeader(String header) {

        print("\n=== " + header);
        println(" ===\n");
    }

    protected void displayHeaderWithPseudo(String header) {
        clear();
        print("\n=== " + header);
        if (MoudeuleApp.isLogged())
            print(" - " + MoudeuleApp.getLoggedUser().getPseudo());
        println(" ===\n");
    }

    protected void displayHeaderWithLogo(String header) {
        clear();
        print("\n\n                                  __                     ___              \n");
        print("    /'\\_/`\\                      /\\ \\                   /\\_ \\             \n");
        print("   /\\      \\     ___    __  __   \\_\\ \\      __    __  __\\//\\ \\       __   \n");
        print("   \\ \\ \\__\\ \\   / __`\\ /\\ \\/\\ \\  /'_` \\   /'__`\\ /\\ \\/\\ \\ \\ \\ \\    /'__`\\ \n");
        print("    \\ \\ \\_/\\ \\ /\\ \\_\\ \\\\ \\ \\_\\ \\/\\ \\_\\ \\ /\\  __/ \\ \\ \\_\\ \\ \\_\\ \\_ /\\  __/ \n");
        print("     \\ \\_\\\\ \\_\\\\ \\____/ \\ \\____/\\ \\___,_\\\\ \\____\\ \\ \\____/ /\\____\\\\ \\____\\\n");
        print("      \\/_/ \\/_/ \\/___/   \\/___/  \\/__,_ / \\/____/  \\/___/  \\/____/ \\/____/\n");
        println("       ====================================================================\n");
        print("\n");
        if (MoudeuleApp.isLogged()) {
            print(" (" + MoudeuleApp.getLoggedUser().getPseudo() + ")");
        }
    }
}