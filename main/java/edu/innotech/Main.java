package edu.innotech;

public class Main {
    public static void main(String[] args) {
        SaveAccount saveAccount;
        Account account = new Account("Василий");
        account.setName("Иван");
        account.addCyrreancy(Currency.RUB, 100);
        saveAccount = account.saveAccount();
        account.addCyrreancy(Currency.RUB, 200);
        saveAccount.load();
    }

}