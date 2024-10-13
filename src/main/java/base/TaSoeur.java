package base;

import java.time.LocalDate;

public class TaSoeur {
    public static void main(String[] args){
        System.out.println("Alive and well");
        // TODO: Create a "Birthday class" that will take a date and name as fields
        BirthdaysManager birthdayJulie = new BirthdaysManager();
        birthdayJulie.setFamilyName("Goineau");
        birthdayJulie.setGivenName("Julie");
        LocalDate date = LocalDate.of(1990, 11, 6);
        birthdayJulie.setBirthdate(date);

        System.out.println("L'anniversaire de " + birthdayJulie.getGivenName() + " " + birthdayJulie.getFamilyName() + " est le: " + birthdayJulie.getBirthdate());
    }
}
