package org.solarflare.BankBackend.systemFunc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.solarflare.BankBackend.dao.loanDAO;

@Service
public class loanService {
    @Autowired
    private loanDAO loanDAO;
}
