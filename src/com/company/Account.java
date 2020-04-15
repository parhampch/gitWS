package com.company;


import java.util.ArrayList;

public class Account {
    private static ArrayList<Account> allAccounts = new ArrayList<Account>();
    private Bank bank;
    private int id;
    private int money;
    private int remainingDuration;
    private int interest;
    private Customer customer;

    public Account(Bank bank, int id, int money, int remainingDuration, int interest, Customer customer) {
        this.bank = bank;
        this.id = id;
        this.money = money;
        this.remainingDuration = remainingDuration;
        this.interest = interest;
        this.customer = customer;
        allAccounts.add(this);
    }

    public Bank getBank()
    {
        return bank;
    }

    public int getId()
    {
        return id;
    }

    public double getAmountOfMonetForLeaving()
    {
        if(remainingDuration != 0)
        {
            return money;
        }
        else
        {
            return money * (1+ ((double)interest/(double)100));
        }
    }

    public static void deleteAccount(Account account)
    {
            allAccounts.remove(account);
    }

    public static void passMonth()
    {
        ArrayList<Account> tempAllAccounts = new ArrayList<Account>();
        tempAllAccounts.addAll(allAccounts);
        for(int i = 0; i < tempAllAccounts.size(); i++)
        {
            tempAllAccounts.get(i).passMonthEach();
        }
    }
    private void passMonthEach()
    {
        this.remainingDuration -= 1;
        if(this.remainingDuration == 0)
        {
            this.customer.leaveAccount(id);
        }
    }


}
