package org.solarflare.BankBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.solarflare.BankBackend.beans.Loan;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDAO extends JpaRepository<Loan, Integer>{
}
