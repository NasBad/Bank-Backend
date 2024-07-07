package org.solarflare.BankBackend.dao;

import org.solarflare.BankBackend.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
    Optional<User> findByAccountNumber(Integer accountNumber);
}
