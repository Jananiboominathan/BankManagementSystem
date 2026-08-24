package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankService(AccountRepository accountRepository,
                       TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccount(Account account) {
        if (accountRepository.findByAccountNumber(account.getAccountNumber()).isPresent()) {
            throw new IllegalArgumentException("Account number already exists");
        }

        if (accountRepository.findByEmail(account.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        return accountRepository.save(account);
    }

    public Account login(String email, String password) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!account.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        return account;
    }

    public Account deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        account.deposit(amount);
        Account saved = accountRepository.save(account);
        transactionRepository.save(new Transaction(accountNumber, "DEPOSIT", amount));
        return saved;
    }

    public Account withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        account.withdraw(amount);
        Account saved = accountRepository.save(account);
        transactionRepository.save(new Transaction(accountNumber, "WITHDRAW", amount));
        return saved;
    }

    public List<Transaction> getTransactions(String accountNumber) {
        return transactionRepository
                .findByAccountNumberOrderByTransactionDateDesc(accountNumber);
    }

    private Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }
}
