package org.solarflare.BankBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.solarflare.BankBackend.beans.loan;
import org.springframework.stereotype.Repository;

@Repository
public interface loanDAO extends JpaRepository<loan, Integer>{
}
