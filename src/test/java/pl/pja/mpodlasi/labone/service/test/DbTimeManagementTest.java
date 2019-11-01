package pl.pja.mpodlasi.labone.service.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import pl.pja.mpodlasi.labone.domain.Expense;
import pl.pja.mpodlasi.labone.domain.ExpenseRecord;
import pl.pja.mpodlasi.labone.service.ExpenseService;

import java.util.ArrayList;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class DbTimeManagementTest {

    @InjectMocks
    ExpenseService expenseService;
    ArrayList<ExpenseRecord> db = new ArrayList<>();

    @Mock ExpenseRecord expense1;
    @Mock ExpenseRecord expense2;
    @Mock Expense e1;
    @Mock Expense e2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        db.add(expense1);
        db.add(expense2);
        expenseService = new ExpenseService(db);
    }

    @Test
    public void DefaultStoreAccessRecordsIsTurnOn() {
        assertTrue(expenseService.isStoreCreated());
        assertTrue(expenseService.isStoreLastModified());
        assertTrue(expenseService.isStoreLastRead());
    }

    @Test
    public void StoreAccessRecordsCanBeTurnOff() {
        expenseService.setStoreCreated(false);
        expenseService.setStoreLastModified(false);
        expenseService.setStoreLastRead(false);
        assertFalse(expenseService.isStoreCreated());
        assertFalse(expenseService.isStoreLastModified());
        assertFalse(expenseService.isStoreLastRead());
    }

    @Test
    public void CreateTimeIsNotSet(){
        expenseService.setStoreCreated(false);
        expenseService.Create(e2);
        when(e2.getId()).thenReturn(100);
        assertNull(expenseService.GetCreated(100));
    }
    @Test
    public void ReadTimeChangeOnReadRecordWhenFalse(){
        expenseService.setStoreLastRead(false);
        when(expense1.getExpense()).thenReturn(e1);
        when(e1.getId()).thenReturn(1);
        expenseService.Read(1);
        verify(expense1, never()).setLastRead();
        verify(expense2, never()).setLastRead();
    }

    @Test
    public void ReadTimeChangeOnModifyRecordWhenFalse(){
        expenseService.setStoreLastRead(false);
        expenseService.setStoreLastModified(true);
        when(expense1.getExpense()).thenReturn(e2);
        when(e2.getId()).thenReturn(2);
        expenseService.Update(e2);
        verify(expense1, never()).setLastRead();
        verify(expense1, times(1)).setLastModified();
    }

    @Test
    public void ReadTimeChangeOnReadAllRecordsWhenFalse(){
        expenseService.setStoreLastRead(false);
        when(expense1.getExpense()).thenReturn(e1);
        when(expense2.getExpense()).thenReturn(e2);
        expenseService.ReadAll();
        verify(expense1, times(1)).setLastRead();
        verify(expense2, times(1)).setLastRead();
    }

    @Test
    public void ModifyTimeChangeOnModifyRecordWhenFalse(){
        expenseService.setStoreLastRead(true);
        expenseService.setStoreLastModified(false);

        when(expense1.getExpense()).thenReturn(e2);
        when(e2.getId()).thenReturn(2);
        expenseService.Update(e2);
        verify(expense1, never()).setLastModified();
        verify(expense1, times(1)).setLastRead();
    }

    @Test
    public void ReadTimeChangeOnReadRecord(){
        when(expense1.getExpense()).thenReturn(e1);
        when(e1.getId()).thenReturn(1);
        expenseService.Read(1);
        verify(expense1, times(1)).setLastRead();
        verify(expense2, never()).setLastRead();
    }

    @Test
    public void ReadTimeChangeOnModifyRecord(){
        when(expense1.getExpense()).thenReturn(e2);
        when(e2.getId()).thenReturn(2);
        expenseService.Update(e2);
        verify(expense1, times(1)).setLastRead();
    }

    @Test
    public void ReadTimeChangeOnReadAllRecords(){
        when(expense1.getExpense()).thenReturn(e1);
        when(expense2.getExpense()).thenReturn(e2);
        expenseService.ReadAll();
        verify(expense1, times(1)).setLastRead();
        verify(expense2, times(1)).setLastRead();
    }

    @Test
    public void ModifyTimeChangeOnModifyRecord(){
        when(expense1.getExpense()).thenReturn(e2);
        when(e2.getId()).thenReturn(2);
        expenseService.Update(e2);
        verify(expense1, times(1)).setLastModified();
    }
}
