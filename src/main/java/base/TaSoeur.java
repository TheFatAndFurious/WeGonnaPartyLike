package base;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class TaSoeur {
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome my guy");

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
        System.out.println("test");
        Database.queryData();
        System.out.println("retest");
        var results = Database.queryDataByDate(LocalDate.now());
        for(BirthdaysManager one : results){
            System.out.println(one.givenName + " " + one.familyName + " is " + one.birthdate);
        }
        System.out.println("delete");
        int user2delete = scanner.nextInt();
        Database.deleteData(user2delete);
        var results2 = Database.queryDataByDate(LocalDate.now());
        for(BirthdaysManager one : results2){
            System.out.println(one.givenName + " " + one.familyName + " is " + one.birthdate);
        }
    }
}
