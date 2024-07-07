package org.solarflare.BankBackend.systemFuncAPI;

import org.solarflare.BankBackend.dao.LoanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanAPI {
    @Autowired
    private LoanDAO loanDAO;


}
