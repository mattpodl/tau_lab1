package pl.pja.mpodlasi.labone.service;

import pl.pja.mpodlasi.labone.domain.Expense;

import java.util.ArrayList;

public interface IExpenseService {
    void Create(Expense expense);

    Expense Read(int id);

    ArrayList<Expense> ReadAll();

    void Update(Expense expense);

    void Delete(Expense expense);


}
