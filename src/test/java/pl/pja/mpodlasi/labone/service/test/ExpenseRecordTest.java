package pl.pja.mpodlasi.labone.service.test;

import org.junit.Test;
import org.mockito.Mock;
import pl.pja.mpodlasi.labone.domain.Expense;
import pl.pja.mpodlasi.labone.domain.ExpenseRecord;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ExpenseRecordTest {

    @Mock
    Expense expense;

    @Test
    public void CreateExpenseRecord(){
        ExpenseRecord expenseRecord = new ExpenseRecord(expense);
        assertNotNull(expenseRecord.getCreated());
        assertNotNull(expenseRecord.getLastModified());
        assertNotNull(expenseRecord.getLastRead());
    }

    @Test
    public void AfterCreateExpenseRecordAllDatesAreEqual(){
        ExpenseRecord expenseRecord = new ExpenseRecord(expense);
        assertEquals(expenseRecord.getCreated(),expenseRecord.getLastModified());
        assertEquals(expenseRecord.getLastRead(),expenseRecord.getLastModified());
    }

}
