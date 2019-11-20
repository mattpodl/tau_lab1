package pl.pja.mpodlasi.labone.service;

import org.springframework.stereotype.Component;
import pl.pja.mpodlasi.labone.domain.Expense;
import pl.pja.mpodlasi.labone.domain.ExpenseRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExpenseService implements IExpenseService {
    private ArrayList<ExpenseRecord> db;
    private boolean storeLastRead = true;
    private boolean storeLastModified = true;
    private boolean storeCreated = true;

    public ExpenseService() {
        this(new ArrayList<>());
    }

    public ExpenseService(ArrayList<ExpenseRecord> db) {
        this.db = db;
    }

    public boolean isStoreLastRead() {
        return storeLastRead;
    }

    public void setStoreLastRead(boolean storeLastRead) {
        this.storeLastRead = storeLastRead;
    }

    public boolean isStoreLastModified() {
        return storeLastModified;
    }

    public void setStoreLastModified(boolean storeLastModified) {
        this.storeLastModified = storeLastModified;
    }

    public boolean isStoreCreated() {
        return storeCreated;
    }

    public void setStoreCreated(boolean storeCreated) {
        this.storeCreated = storeCreated;
    }

    public LocalDateTime GetLastRead(int id) {
        return this.FindRecord(id).getLastRead();
    }

    public LocalDateTime GetLastModify(int id) {
        return this.FindRecord(id).getLastModified();
    }

    public LocalDateTime GetCreated(int id) {
        return this.FindRecord(id).getCreated();
    }

    private ExpenseRecord FindRecord(int id) {
        for (ExpenseRecord record : db) {
            if (record.getExpense().getId() == id)
                return record;
        }
        throw new IllegalArgumentException("Expense with id: " + id + " doesn't exist");
    }

    public void Create(Expense expense) {
        for (ExpenseRecord ex : db) {
            if (ex.getExpense().getId() == expense.getId())
                throw new IllegalArgumentException("The value is already in the list.");
        }
        ExpenseRecord record = new ExpenseRecord(expense);
        if (!isStoreCreated()) record.setCreated(null);
        if (isStoreLastRead()) record.setLastRead();
        if (isStoreLastModified()) record.setLastModified();
        db.add(record);
    }

    public Expense Read(int id) {
        for (ExpenseRecord ex : db) {
            if (ex.getExpense().getId() == id) {
                if (isStoreLastRead()) ex.setLastRead();
                return ex.getExpense();
            }
        }
        throw new IllegalArgumentException("Expense with id: " + id + " doesn't exist");
    }

    public ArrayList<Expense> ReadAll() {
        ArrayList<Expense> expenseList = new ArrayList<>();
        for (ExpenseRecord ex : db) {
            if (isStoreLastRead()) ex.setLastRead();
            expenseList.add(ex.getExpense());
        }
        return expenseList;
    }

    public void Update(Expense expense) {
        for (ExpenseRecord ex : db) {
            if (ex.getExpense().getId() == expense.getId()) {
                ex.setExpense(expense);
                if (isStoreLastModified()) ex.setLastModified();
                if (isStoreLastRead()) ex.setLastRead();
                return;
            }
        }
        throw new IllegalArgumentException("Expense doesn't exist");
    }

    public void Delete(Expense expense) {
        for (ExpenseRecord ex : db) {
            if (ex.getExpense().getId() == expense.getId()) {
                db.remove(ex);
                return;
            }
        }
        throw new IllegalArgumentException("Expense doesn't exist");
    }

    public Expense Find(String description) {
        //yes it's finding only first element that matches
        for (ExpenseRecord record : db) {
            if (record.getExpense().getDescription().contains(description))
                return record.getExpense();
        }
        return null;
    }

    public ArrayList<Expense> deleteRecordsByDescription(List<String> descriptionList) {
        ArrayList<Expense> deleteExpensesList = new ArrayList<>();
        for (String description : descriptionList) {
            while (true) {
                Expense expense = this.Find(description);
                if (expense == null) {
                    break;
                }
                deleteExpensesList.add(expense);
                this.Delete(expense);
            }
        }
        return deleteExpensesList;
    }
}