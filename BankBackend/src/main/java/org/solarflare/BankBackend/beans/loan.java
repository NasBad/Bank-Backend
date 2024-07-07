package org.solarflare.BankBackend.beans;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loan")
public class loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_number")
    private Integer accountNumber;

    @Column(name = "amount")
    private double amount;

    @Column(name = "pay_per_month")
    private double payPerMonth;

    @Column(name="loanDate")
    private LocalDate loanDate;

    public loan() {
        System.out.println("loans :)");
    }

    public loan(Integer accountNumber, double amount, double payPerMonth, LocalDate loanDate) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.payPerMonth = payPerMonth;
        this.loanDate = loanDate;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPayPerMonth() {
        return payPerMonth;
    }

    public void setPayPerMonth(double payPerMonth) {
        this.payPerMonth = payPerMonth;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }
}
