package pl.pja.mpodlasi.labone.web;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.pja.mpodlasi.labone.domain.Expense;
import pl.pja.mpodlasi.labone.service.ExpenseService;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/*

todo:
- Error Responses 404, ...
- refactor
- remove unused methods and endpoints
*/

@RestController
@Api(value = "Expense Resource Rest Endpoint")
public class ExpenseApi {

    @Autowired
    ExpenseService expenseService;
@ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping("/")
    public String index() {
        return "This is non rest, just checking if everything works.";
    }

    @RequestMapping(value = "/Expense/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Expense getExpense(@PathVariable("id") int id) throws SQLException {
        return expenseService.Read(id);
    }

    @RequestMapping(value = "/Expenses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Expense> getExpenses(@RequestParam(value = "filter", required = false) String filter) throws SQLException {
        List<Expense> Expenses = new LinkedList<Expense>();
        for (Expense expense : expenseService.ReadAll()) {
            if (filter == null) {
                Expenses.add(expense);
            } else if (expense.getDescription().contains(filter)) {
                Expenses.add(expense);
            }
        }
        return Expenses;
    }

    @RequestMapping(value = "/Expense",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Expense addExpense(@RequestBody Expense expense) {
        try{
            expenseService.Create(expense);
        } catch (IllegalArgumentException e){
            return null;
        }
        return expense;
    }

    @RequestMapping(value = "/Expense/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteExpense(@PathVariable("id") int id) throws SQLException {
        try{
            expenseService.Delete(expenseService.Read(id));
        } catch (IllegalArgumentException e){
            return 1;
        }
        return 0;
    }

}
