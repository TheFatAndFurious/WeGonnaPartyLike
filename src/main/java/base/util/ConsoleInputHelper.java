package base.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleInputHelper implements InputHelper {

    Scanner scanner = new Scanner(System.in);

    @Override
    public String getInputString(String prompt) {
        System.out.println(prompt + " ");
        String input = scanner.nextLine().trim();
        boolean isEmpty = true;
        while(isEmpty){
            input = scanner.nextLine().trim();
            if(input.isEmpty()){
                System.out.println("Input can not be empty");
            } else {
                isEmpty = false;
            }
        }
        boolean isString = false;
        while (!isString){
            try{
                Integer.parseInt(input);
                System.out.println("Input can not be numeric");
                input = scanner.nextLine().trim();
            } catch (NumberFormatException e) {
                isString = true;
            }
            }
                return input;
        }


    @Override
    public int getInputInteger(String prompt) {
        System.out.println(prompt + " ");
        while (true){
        int input = scanner.nextInt();
        try{
            return input;
        } catch (NumberFormatException e) {
            System.out.println("Input must be integer. " + prompt + " ");;
        }
        }
    }

    @Override
    public LocalDate getInputLocalDate(String prompt) {
        System.out.println(prompt + " ");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        while(true){
            String input = scanner.nextLine().trim();
            try{
                return LocalDate.parse(input);
            } catch (DateTimeParseException e){
                System.out.println("Invalid date format. Please use YYYY-MM-DD");
            }
        }
    }
}


