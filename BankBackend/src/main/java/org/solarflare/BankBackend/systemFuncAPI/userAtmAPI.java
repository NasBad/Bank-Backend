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
            return ResponseEntity.ok("Successfully Withdrew: "+amount+"\n"+"your balance now is: "+userAtm.checkBalance(accountNumber));
        } else {
            return ResponseEntity.status(400).body("Withdrawal failed.");
        }
    }

    @PostMapping("/deposit/")
    public ResponseEntity<String> deposit(@RequestParam Integer accountNumber, @RequestParam double amount) {
        if (userAtm.deposit(accountNumber, amount)) {
            return ResponseEntity.ok("Successfully Deposited: "+amount+"\n"+"your balance now is: "+userAtm.checkBalance(accountNumber));
        } else {
            return ResponseEntity.status(400).body("Deposit failed.");
        }
    }

    @GetMapping("/balance/")
    public ResponseEntity<String> checkBalance(@RequestParam Integer accountNumber) {
        return ResponseEntity.ok("your balance is: "+userAtm.checkBalance(accountNumber));
    }

    @PostMapping("/transfer/")
    public ResponseEntity<String> transfer(@RequestParam Integer accountNumber1,@RequestParam Integer accountNumber2, @RequestParam double amount){
        if(userAtm.transfer(accountNumber1,accountNumber2,amount)){
            return ResponseEntity.ok("user with bank account Number:'"+accountNumber1+"' transfered money to user with the bank account Number: '"+accountNumber2+"' "+amount+"$ Succsessfully");
        } else {
            return ResponseEntity.status(400).body("transfer failed.");
        }
    }
}