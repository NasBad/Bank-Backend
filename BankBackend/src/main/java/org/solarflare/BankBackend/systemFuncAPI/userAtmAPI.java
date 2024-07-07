package org.solarflare.BankBackend.systemFuncAPI;

import org.solarflare.BankBackend.systemFunc.userAtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Component
@RestController
@RequestMapping("atm")
public class userAtmAPI {
    @Autowired
    private userAtmService userAtm;

    @PostMapping("/withdraw/")
    public ResponseEntity<String> withdraw(@RequestParam Integer accountNumber, @RequestParam double amount) {
        if (userAtm.withdraw(accountNumber, amount)) {
            return ResponseEntity.ok("Successfully Withdrawed: "+amount);
        } else {
            return ResponseEntity.status(400).body("Withdrawal failed.");
        }
    }

    @PostMapping("/deposit/")
    public ResponseEntity<String> deposit(@RequestParam Integer accountNumber, @RequestParam double amount) {
        if (userAtm.deposit(accountNumber, amount)) {
            return ResponseEntity.ok("Successfully Deposited: "+amount);
        } else {
            return ResponseEntity.status(400).body("Deposit failed.");
        }
    }

    @GetMapping("/balance/")
    public ResponseEntity<Double> checkBalance(@RequestParam Integer accountNumber) {
        double balance = userAtm.checkBalance(accountNumber);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/transfer/")
    public ResponseEntity<String> transfer(@RequestParam Integer accountNumber1,@RequestParam Integer accountNumber2, @RequestParam double amount){
        if(userAtm.transfer(accountNumber1,accountNumber2,amount)){
            return ResponseEntity.ok("user with id:'"+accountNumber1+"' transfered to user with the id: '"+accountNumber2+"' "+amount+"$");
        } else {
            return ResponseEntity.status(400).body("transfer failed.");
        }
    }
}
