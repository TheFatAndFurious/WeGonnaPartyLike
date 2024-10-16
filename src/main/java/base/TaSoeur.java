package base;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;

public class TaSoeur {
    public static void main(String[] args){
        System.out.println("Alive and well");

        Scanner scanner = new Scanner(System.in);
        BirthdaysManager birthdayJulie = new BirthdaysManager();
        Database.createTable();

        System.out.println("Please enter a given name");
        String givenName = scanner.nextLine();
        birthdayJulie.setGivenName(givenName);
        System.out.println("Please enter a family name");
        String familyName = scanner.nextLine();
        birthdayJulie.setFamilyName(familyName);
        System.out.println("Please enter a day");
        int dayOfBirth = scanner.nextInt();
        System.out.println("Please enter a month");
        int monthOfBirth = scanner.nextInt();
        System.out.println("Please enter a year");
        int yearOfBirth = scanner.nextInt();
        LocalDate date = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
        birthdayJulie.setBirthdate(date);
        Database.insertData(birthdayJulie);

        System.out.println("L'anniversaire de " + birthdayJulie.getGivenName() + " " + birthdayJulie.getFamilyName() + " est le: " + birthdayJulie.getBirthdate());
    }
}
