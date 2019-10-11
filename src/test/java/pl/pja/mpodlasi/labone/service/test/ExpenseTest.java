package pl.pja.mpodlasi.labone.service.test;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.pja.mpodlasi.labone.domain.Expense;


@RunWith(JUnit4.class)
public class ExpenseTest {
    @Test
    public void CreateExpense() {
        new Expense(1, 1.0, new Date());
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateExpenseWithIncorrectId() {
        new Expense(-1, 1.0, new Date());
    }



}
