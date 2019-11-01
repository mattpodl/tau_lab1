package pl.pja.mpodlasi.labone.service.test;

import org.junit.Test;
import org.mockito.Mock;
import pl.pja.mpodlasi.labone.domain.Expense;
import pl.pja.mpodlasi.labone.domain.ExpenseRecord;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

public class ExpenseRecordTest {

    @Mock
    Expense expense;

    @Test
    public void CreateExpenseRecord(){
        ExpenseRecord expenseRecord = new ExpenseRecord(expense);
        assertNotNull(expenseRecord.getCreated());
        assertNull(expenseRecord.getLastModified());
        assertNull(expenseRecord.getLastRead());
    }

    @Test
    public void AfterCreateExpenseRecordAllDatesAreEqual(){
        ExpenseRecord expenseRecord = new ExpenseRecord(expense);
        assertEquals(expenseRecord.getLastRead(),expenseRecord.getLastModified());
    }

}
