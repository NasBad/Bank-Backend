package org.solarflare.BankBackend.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.solarflare.BankBackend.dao.LoanDAO;

@Service
public class LoanBL {
    @Autowired
    private LoanDAO loanDAO;
}
