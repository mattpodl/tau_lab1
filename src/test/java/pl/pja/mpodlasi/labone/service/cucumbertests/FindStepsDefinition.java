package pl.pja.mpodlasi.labone.service.cucumbertests;


import cucumber.api.java.Before;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.pja.mpodlasi.labone.domain.Expense;
import pl.pja.mpodlasi.labone.domain.ExpenseRecord;
import pl.pja.mpodlasi.labone.service.ExpenseService;
import pl.pja.mpodlasi.labone.service.IExpenseService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class FindStepsDefinition {
    private IExpenseService expenseService;
    private ArrayList<Expense> removedExpenses;
    private ArrayList<ExpenseRecord> db;
    private Expense expense;

    // first step for each scenario
    @Before
    public void setUp() {
        this.db = new ArrayList<>();
        this.expenseService = new ExpenseService(db);
        this.expense = null;
        this.removedExpenses = new ArrayList<>();
    }

    @Given("^The is an expense with id (\\d+) object with (.*)$")
    public void there_is_an_expense_object_with_description(int arg0, String arg1) throws Exception {
        this.expense = new Expense(arg0, 1.0, new Date(), arg1);
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

    @When("^I delete expense with description (.*)$")
    public void iDeleteExpenseWithDescriptionWord(String arg1) {
        ArrayList<String> descriptionsList = new ArrayList<>(List.of(arg1));
        this.removedExpenses = this.expenseService.deleteRecordsByDescription(descriptionsList);
    }


    @Then("^I get an expense list with (\\d+) elements$")
    public void iGetAnExpenseListWithElements(int arg0) {
        assertEquals(arg0,this.removedExpenses.size());
    }

    @But("^There will be (\\d+) element in database$")
    public void thereWillBeElementInDatabase(int arg0) {
        assertEquals(arg0,this.expenseService.ReadAll().size());
    }
}
