package pl.pja.mpodlasi.labone.domain;

import java.time.LocalDateTime;

public class ExpenseRecord {

    private LocalDateTime created;
    private LocalDateTime lastRead;
    private LocalDateTime lastModified;
    private Expense expense;

    public ExpenseRecord(Expense expense) {
        this.expense = expense;
        this.created = LocalDateTime.now();
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastRead() {
        return lastRead;
    }

    public void setLastRead() {
        this.lastRead = LocalDateTime.now();
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified() {
        this.lastModified = LocalDateTime.now();
    }
}
