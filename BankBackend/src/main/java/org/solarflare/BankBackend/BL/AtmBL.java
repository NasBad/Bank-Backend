package org.solarflare.BankBackend.BL;

import org.solarflare.BankBackend.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AtmBL {

    @Autowired
    private UserBL userService;
    @Autowired
    private EmailBL emailBL;

    public boolean withdraw(Integer accountNumber,double amount){
        Optional<User> usersOptional = userService.getUserByAccountNumber(accountNumber);
            User user = usersOptional.get();
            if (user.getUserBalance() >= amount && user.getStatus().equals("Active")) {
                emailBL.sendEmail(user.getUserEmail(), "Successfully Withdrew Money", "you have been Successfully Withdrew: "+amount+"EP from your bank account");
                user.setUserBalance(user.getUserBalance() - amount);
                userService.updateUser(accountNumber, user);

                return true;
            }
            System.out.println("Insufficient funds.");
            return false;
    }

    public boolean deposit(Integer accountNumber,double amount){
        Optional<User> usersOptional=userService.getUserByAccountNumber(accountNumber);
        User user=usersOptional.get();
        if (!user.getStatus().equals("Deleted")) {
            emailBL.sendEmail(user.getUserEmail(), "Successfully Deposited Money", "you have been Successfully Deposited: "+amount+"EP from your bank account"
            +"\n"+"your balance now is: "+user.getUserBalance());
            user.setUserBalance(user.getUserBalance() + amount);
            userService.updateUser(accountNumber, user);
            return true;
        }
        System.out.println("Insufficient funds.");
        return false;
    }

    public double checkBalance(Integer accountNumber)
    {
        Optional<User> usersOptional = userService.getUserByAccountNumber(accountNumber);
        User user = usersOptional.get();
        if (!user.getStatus().equals("Deleted")) {
            return user.getUserBalance();
        }else{
            return 0;
        }
    }

    public boolean transfer(Integer accountNumber1,Integer accountNumber2, double amount){
        return withdraw(accountNumber1, amount) && deposit(accountNumber2, amount);
    }
}
