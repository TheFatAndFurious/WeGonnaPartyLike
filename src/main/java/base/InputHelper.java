package base;

import java.time.LocalDate;

public interface InputHelper {
    String getInputString(String prompt);
    int getInputInteger(String prompt);
    LocalDate getInputLocalDate(String prompt);
}
