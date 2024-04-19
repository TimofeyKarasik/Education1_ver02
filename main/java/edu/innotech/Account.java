package edu.innotech;

import java.util.*;

public class Account {
    private String name;
    private Map<Currency,Integer> currenc = new HashMap<>();
    private Deque<Command> saves = new ArrayDeque<>();


    public Account(String nameIn)
    {
        Account.this.name = nameIn;
    }

    public void setName(String nameIn)
    {
        String tmp = Account.this.name;
        if (nameIn == null || nameIn == ""||nameIn.isBlank()){
            throw new NullPointerException();
        }
        saves.push(()->Account.this.name=tmp);
        this.name = nameIn;
    }



    public String getName() {

        return name;
    }


    public Map<Currency, Integer> getCurrency() {

        return new HashMap<>(currenc);
    }

    public void addCyrreancy(Currency currency, Integer amount){
        Integer tmpAmount =  Account.this.currenc.get(currency);
        if (amount < 0){
            throw new IllegalArgumentException("Сумма не может быть меньше нуля");
        }
        saves.push(()->Account.this.currenc.put(currency,tmpAmount));
        Account.this.currenc.put(currency,amount);
    }

    public SaveAccount saveAccount(){
        return new AccSave();
    }

    public void undo(){
        if (saves.size() == 0){
            throw new IllegalArgumentException("Нет предыдущих состояний");
        }
        saves.pop().make();
    }

    private class AccSave implements SaveAccount {
        private String name = Account.this.name;
        private final Map<Currency,Integer> currenc = new HashMap<>(Account.this.currenc);


        public void load(){
            Account.this.name = name;
            Account.this.currenc.clear();
            Account.this.currenc.putAll(currenc);
        }

    }


    interface Command {
        void make();
    }

}


