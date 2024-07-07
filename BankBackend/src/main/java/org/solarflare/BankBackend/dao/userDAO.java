package org.solarflare.BankBackend.dao;

import org.solarflare.BankBackend.beans.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userDAO extends JpaRepository<user,Integer> {
    Optional<user> findByAccountNumber(Integer accountNumber);
}
