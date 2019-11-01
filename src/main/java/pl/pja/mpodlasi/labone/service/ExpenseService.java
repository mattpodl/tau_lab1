package pl.pja.mpodlasi.labone.service;

import pl.pja.mpodlasi.labone.domain.Expense;
import pl.pja.mpodlasi.labone.domain.ExpenseRecord;

import java.util.ArrayList;

public class ExpenseService implements IExpenseService{
    private ArrayList<ExpenseRecord> db;

    public boolean getStoreAccessRecords() {
        return StoreAccessRecords;
    }

    private Boolean StoreAccessRecords = true;

    public ExpenseService(){
        this(new ArrayList<>());
    }

    public ExpenseService(ArrayList<ExpenseRecord> db){
        this.db = db;
    }

    public void setStoreAccessRecords(Boolean storeAccessRecords) {
        StoreAccessRecords = storeAccessRecords;
    }

    public void Create(Expense expense) {
        for (ExpenseRecord ex : db){
            if (ex.getExpense().getId() == expense.getId())
            throw new IllegalArgumentException("The value is already in the list.");
        }

        db.add(new ExpenseRecord(expense));
    }

    public Expense Read(int id){
        for (ExpenseRecord ex : db){
            if (ex.getExpense().getId() == id){
                ex.setLastRead();
                return ex.getExpense();
        }}
        throw new IllegalArgumentException("Expense with id: "+id+" doesn't exist");
    }

    public ArrayList<Expense> ReadAll(){
        ArrayList<Expense> expenseList = new ArrayList<>();
        for (ExpenseRecord ex : db){
            ex.setLastRead();
            expenseList.add(ex.getExpense());
        }
        return expenseList;
    }

    public void Update(Expense expense){
        for (ExpenseRecord ex : db){
            if (ex.getExpense().getId() == expense.getId()) {
                ex.setExpense(expense);
                ex.setLastModified();
                ex.setLastRead();
                return;
            }
        }
        throw new IllegalArgumentException("Expense doesn't exist");
    }

    public void Delete(Expense expense){
        for (ExpenseRecord ex : db) {
            if (ex.getExpense().getId() == expense.getId()) {
                db.remove(ex);
                return;
            }
        }
        throw new IllegalArgumentException("Expense doesn't exist");
    }
}