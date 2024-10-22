package base;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleInputHelper implements InputHelper {

    Scanner scanner = new Scanner(System.in);

    @Override
    public String getInputString(String prompt) {
        System.out.println(prompt + " ");
        String input = scanner.nextLine().trim();
        while(input.isEmpty()){
            System.out.println("Input can not be empty");
            input = scanner.nextLine().trim();
        }
        return input;
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
