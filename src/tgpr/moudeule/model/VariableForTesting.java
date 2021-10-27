package tgpr.moudeule.model;

import java.time.LocalDate;

public class VariableForTesting {
    private static LocalDate currentDate;

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(LocalDate currentDate) {
        VariableForTesting.currentDate = currentDate;
    }
}
