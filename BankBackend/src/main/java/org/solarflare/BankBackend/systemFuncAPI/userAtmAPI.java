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
    public ResponseEntity<String> withdraw(@RequestParam Integer userId, @RequestParam int amount) {
        if (userAtm.withdraw(userId, amount)) {
            return ResponseEntity.ok("Successfully Withdrawed: "+amount);
        } else {
            return ResponseEntity.status(400).body("Withdrawal failed.");
        }
    }

    @PostMapping("/deposit/")
    public ResponseEntity<String> deposit(@RequestParam Integer userId, @RequestParam int amount) {
        if (userAtm.deposit(userId, amount)) {
            return ResponseEntity.ok("Successfully Deposited: "+amount);
        } else {
            return ResponseEntity.status(400).body("Deposit failed.");
        }
    }

    @GetMapping("/balance/")
    public ResponseEntity<Double> checkBalance(@RequestParam Integer userId) {
        double balance = userAtm.checkBalance(userId);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/transfer/")
    public ResponseEntity<String> transfer(@RequestParam Integer userId1,@RequestParam Integer userId2, @RequestParam int amount){
        if(userAtm.transfer(userId1,userId2,amount)){
            return ResponseEntity.ok("user with id:'"+userId1+"' transfered to user with the id: '"+userId2+"' "+amount+"$");
        } else {
            return ResponseEntity.status(400).body("transfer failed.");
        }
    }
}
