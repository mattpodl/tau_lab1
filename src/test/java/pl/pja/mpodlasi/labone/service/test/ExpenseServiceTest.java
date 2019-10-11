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

import static junit.framework.TestCase.assertNotNull;

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
        expenseService.Create(this.expenseList.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddExpenseWithExistingId() {
        expenseService.Create(this.expenseList.get(1));
        expenseService.Create(this.expenseList.get(1));
    }

    @Test
    public void ReadAllWhenExpenseService() {
        assertNotNull(expenseService.ReadAll());
    }

    @Test
    public void ReadAllReturnCollectionWithFiveElements() {
        for (Expense ex : this.expenseList)
            expenseService.Create(ex);

        Assert.assertEquals(expenseService.ReadAll(), expenseList);
    }

    @Test
    public void UpdateRecord(){
        expenseService.Create(this.expenseList.get(1));
        expenseService.Update(this.expenseList.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void UpdateUnexistingRecord(){
        expenseService.Update(this.expenseList.get(1));
    }


}
