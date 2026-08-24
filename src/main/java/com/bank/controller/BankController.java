package com.bank.controller;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        try {
            return ResponseEntity.ok(bankService.createAccount(account));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(
                    bankService.login(request.email(), request.password())
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody MoneyRequest request) {
        try {
            return ResponseEntity.ok(
                    bankService.deposit(request.accountNumber(), request.amount())
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody MoneyRequest request) {
        try {
            return ResponseEntity.ok(
                    bankService.withdraw(request.accountNumber(), request.amount())
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/transactions/{accountNumber}")
    public List<Transaction> transactions(@PathVariable String accountNumber) {
        return bankService.getTransactions(accountNumber);
    }

    public record LoginRequest(String email, String password) {}
    public record MoneyRequest(String accountNumber, double amount) {}
}
