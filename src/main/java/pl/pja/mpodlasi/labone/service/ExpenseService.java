package pl.pja.mpodlasi.labone.service;

import pl.pja.mpodlasi.labone.domain.Expense;
import java.util.ArrayList;

public class ExpenseService implements IExpenseService{
    private ArrayList<Expense> expensesCollection = new ArrayList<>();

    public void Create(Expense expense) {
        if (expensesCollection.contains(expense)) {
            throw new IllegalArgumentException("The value is already in the list.");
        }
        expensesCollection.add(expense);
    }

    public Expense Read(int id){
        for (Expense ex : expensesCollection){
            if (ex.getId() == id) return ex;
        }
        throw new IllegalArgumentException("Expense with id: "+id+" doesn't exist");
    }

    public ArrayList<Expense> ReadAll(){
        return expensesCollection;
    }

    public void Update(Expense expense){
        for (Expense ex : expensesCollection){
            if (ex.getId() == expense.getId()) {
                ex.setValue(expense.getValue());
                ex.setDate(expense.getDate());
                return;
            }
        }
        throw new IllegalArgumentException("Expense doesn't exist");
    }

    public void Delete(Expense expense){
        for (Expense ex : expensesCollection) {
            if (ex.getId() == expense.getId()) {
                expensesCollection.remove(expense);
                return;
            }
        }
        throw new IllegalArgumentException("Expense doesn't exist");
    }
}