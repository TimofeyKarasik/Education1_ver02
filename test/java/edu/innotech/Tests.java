package edu.innotech;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testAccountNotNull() {
        String name = "Вася";
        try {
            Account tmpAccount = new Account(name);
            System.out.println("Успех. Account создан");

        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testAccountNull() {
        String name = null;
        try {
            Account tmpAccount = new Account(name);

        } catch (NullPointerException e) {
            System.out.println("Успех. Исключение пустое имя");
        }
    }

    @Test
    public void testCurrencyQuantity() {
        Account account = new Account("Василий");
        account.addCyrreancy(Currency.RUB,100);

        System.out.println("Успех. добавления положительной суммы");

        try {
            account.addCyrreancy(Currency.RUB,-25);

        } catch (IllegalArgumentException e) {
            System.out.println("Успех. При отрицательной сумме ошибка");
        }
    }

    @Test
    public void testUndo() {
        Account account = new Account("Василий");

        account.setName("Иван");
        account.addCyrreancy(Currency.RUB,100);
        account.addCyrreancy(Currency.RUB,200);
        account.undo();
        account.undo();
        account.undo();
        System.out.println("Успех. Тест отката до первоначального состояния.");
        try {
            account.undo();
        } catch (IllegalArgumentException e) {

            System.out.println("Успех. Тест при отсутсвии предыдущих состояний.");
        }
    }

    @Test
    public void testCopyAndLoad() {
        SaveAccount saveAccount;
        Account account = new Account("Василий");
        account.setName("Иван");
        account.addCyrreancy(Currency.RUB,100);
        saveAccount = account.saveAccount();
        account.addCyrreancy(Currency.RUB,200);
        saveAccount.load();
        System.out.println("Успех. Копирования счета и его загрузки.");
    }
}
