package org.solarflare.BankBackend.dao;

import org.solarflare.BankBackend.beans.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userDAO extends JpaRepository<users,Integer> {
    Optional<users> findByAccountNumber(Integer accountNumber);
}
