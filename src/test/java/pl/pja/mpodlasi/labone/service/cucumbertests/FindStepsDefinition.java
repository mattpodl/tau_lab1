package pl.pja.mpodlasi.labone.service.cucumbertests;


import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.pja.mpodlasi.labone.domain.Expense;
import pl.pja.mpodlasi.labone.domain.ExpenseRecord;
import pl.pja.mpodlasi.labone.service.ExpenseService;
import pl.pja.mpodlasi.labone.service.IExpenseService;

import java.util.ArrayList;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class FindStepsDefinition {
    private IExpenseService expenseService;
    private ArrayList<ExpenseRecord> db;
    private Expense expense;

    // first step for each scenario
    @Before
    public void setUp() {
        this.db = new ArrayList<>();
        this.expenseService = new ExpenseService(db);
        this.expense = null;
    }

    @Given("^The is an expense object with (.*)$")
    public void there_is_an_expense_object_with_description(String arg1) throws Exception {
        Expense expense = new Expense(1, 1.0, new Date(), arg1);
        this.expenseService.Create(expense);
    }
    //(?<=This is)(.*)(?=sentence)

    @When("^I look for (.*)$")
    public void record_can_be_found(String arg1) throws Exception {
        this.expense = this.expenseService.Find(arg1);
    }

    @Then("^I get an expense with (.*)$")
    public void the_result_should_be(String arg1) throws Exception {
        assertEquals(arg1 , this.expense.getDescription());
    }

}
