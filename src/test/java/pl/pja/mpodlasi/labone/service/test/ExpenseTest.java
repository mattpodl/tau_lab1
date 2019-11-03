package pl.pja.mpodlasi.labone.service.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.pja.mpodlasi.labone.domain.Expense;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;


@RunWith(JUnit4.class)
public class ExpenseTest {
    private Expense expense;
    private double expenseValue = 1.1;
    private Date expenseDate = new Date();
    private int expenseId = 1;
    private String expenseDescription = "my first expense";


    @Before
    public void init() {
        expense = new Expense(expenseId, expenseValue, expenseDate, expenseDescription);
    }


    @Test(expected = IllegalArgumentException.class)
    public void CreateExpenseWithIncorrectId() {
        new Expense(-1, 1.0, new Date(), "custom string");
    }

    //Getters and Setters
    @Test
    public void CheckDate() {
        assertEquals(expenseDate, expense.getDate());
        expense.setDate(new Date());
        assertNotSame(expenseDate, expense.getDate());
    }

    @Test
    public void CheckValue() {
        assertEquals(expenseValue, expense.getValue());
        expense.setValue(1.5);
        assertEquals(1.5, expense.getValue());
    }

    @Test
    public void CheckID() {
        assertEquals(expenseId, expense.getId());
    }

    @Test
    public void CheckDescription() {
        assertEquals(expenseDescription, expense.getDescription());
        String new_description = "new description";
        expense.setDescription(new_description);
        assertEquals(new_description,expense.getDescription());
    }
}
