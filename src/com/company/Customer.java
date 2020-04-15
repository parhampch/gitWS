package com.company;


import java.util.ArrayList;

public class Customer {
    private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private String name;
    private double moneyInSafe;
    private ArrayList<Account> allActiveAccounts;
    private int totalNumberOfAccountsCreated;
    private int negativeScore;

    public Customer(String name, double moneyInSafe)
    {
        this.name = name;
        this.moneyInSafe = moneyInSafe;
        allActiveAccounts = new ArrayList<Account>();
        allCustomers.add(this);
    }

    public static Customer getCustomerByName(String name)
    {
        for (Customer customer : allCustomers) {
            if(customer.name.equals(name))
            {
                return customer;
            }
        }
        return null;
    }

    public double getMoneyInSafe()
    {
        return moneyInSafe;
    }

    public void createNewAccount(Bank bank, int money, int duration, int type)
    {
        Account account = new Account(bank, totalNumberOfAccountsCreated + 1, money, duration, type, this);
        moneyInSafe -=money;
        allActiveAccounts.add(account);
        totalNumberOfAccountsCreated ++;
    }

    private Account getAccountWhithId(int id)
    {
        for (Account activeAccount : allActiveAccounts) {
            if(activeAccount.getId() == id)
            {
                return activeAccount;
            }
        }
        return  null;
    }

    public int getNegativeScore()
    {
        return negativeScore;
    }

    public void addNegativeScore()
    {
        negativeScore++;
    }

    public boolean hasActiveAccountInBank(Bank bank)
    {
        for (Account activeAccount : allActiveAccounts) {
            if(activeAccount.getBank().getName().equals(bank.getName()))
            {
                return true;
            }
        }
        return false;
    }

    public void leaveAccount(int accountId)
    {
        Account account = getAccountWhithId(accountId);
        if(account == null)
        {
            System.out.println("Chizi zadi?!");
            return;
        }
        moneyInSafe += account.getAmountOfMonetForLeaving();
        allActiveAccounts.remove(account);
        Account.deleteAccount(account);
    }

    public boolean canGetLoan()
    {
        if(negativeScore >= 5)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean canPayLoan(double amount)
    {
        if(amount >= moneyInSafe)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void payLoan(double amount)
    {
        moneyInSafe -= amount;
    }

    public void setMoneyInSafe(double moneyInSafe)
    {
        this.moneyInSafe = moneyInSafe;
    }





}
