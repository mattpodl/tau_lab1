package pl.pja.mpodlasi.labone.service.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.pja.mpodlasi.labone.service.ExpenseService;
import pl.pja.mpodlasi.labone.domain.Expense;
import pl.pja.mpodlasi.labone.service.IExpenseService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(JUnit4.class)
public class ExpenseServiceTest {
    private IExpenseService expenseService;
    private List<Expense> expenseList;

    @Before
    public void CreateExpenses() {
        this.expenseService = new ExpenseService();
        expenseList = new ArrayList(List.of(
                new Expense(1, 1.0, new Date()),
                new Expense(2, 2.0, new Date()),
                new Expense(3, 3.0, new Date()),
                new Expense(4, 4.0, new Date()),
                new Expense(5, 5.0, new Date())));
    }

    @Test
    public void AddExpense() {
        expenseService.Create(this.expenseList.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddExpenseWithExistingId() {
        expenseService.Create(this.expenseList.get(0));
        expenseService.Create(this.expenseList.get(0));
    }

    @Test
    public void ReadAnExpense() {
        expenseService.Create(this.expenseList.get(0));
        assertEquals(this.expenseList.get(0), expenseService.Read(this.expenseList.get(0).getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ReadUnexistingExpense() {
        expenseService.Create(this.expenseList.get(0));
        int notExistingId = 5;
        assertNotSame(notExistingId, this.expenseList.get(0).getId());
        expenseService.Read(notExistingId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ReadEmptyDatabase() {
        expenseService.Read(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ReadNegativeIdNumber() {
        expenseService.Read(-1);
    }

    @Test
    public void ReadAllWhenDatabaseIsEmpty() {
        assertEquals(new ArrayList<Expense>(), expenseService.ReadAll());
    }

    @Test
    public void ReadAllReturnCollectionWithFiveElements() {
        for (Expense ex : this.expenseList)
            expenseService.Create(ex);

        Assert.assertEquals(expenseService.ReadAll(), expenseList);
    }

    @Test
    public void UpdateExpense() {
        expenseService.Create(this.expenseList.get(0));
        expenseService.Update(this.expenseList.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void UpdateUnexistingExpense() {
        expenseService.Create(this.expenseList.get(0));
        expenseService.Update(this.expenseList.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void UpdateOnEmptyDatabase() {
        expenseService.Update(this.expenseList.get(0));
    }

    @Test
    public void DeleteExpense() {
        expenseService.Create(this.expenseList.get(0));
        expenseService.Delete(this.expenseList.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void DeleteOnEmptyDatabase() {
        expenseService.Delete(this.expenseList.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void DeleteUnexistingExpense() {
        expenseService.Create(this.expenseList.get(0));
        expenseService.Delete(this.expenseList.get(1));
    }

}
