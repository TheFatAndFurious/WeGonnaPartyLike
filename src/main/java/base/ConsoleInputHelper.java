package base;

import java.time.LocalDate;

public class ConsoleInputHelper implements InputHelper {

    @Override
    public String getInputString(String prompt) {
        return "";
    }

    @Override
    public int getInputInteger(String prompt) {
        return 0;
    }

    @Override
    public LocalDate getInputLocalDate(LocalDate prompt) {
        return null;
    }
}
