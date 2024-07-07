package org.solarflare.BankBackend.systemFunc;

import org.solarflare.BankBackend.beans.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.solarflare.BankBackend.systemFunc.emailService;
import java.util.Optional;

@Component
public class userAtmService {

    @Autowired
    private userCrudService userService;
    @Autowired
    private emailService emailService;

    public boolean withdraw(Integer accountNumber,double amount){
        Optional<user> usersOptional = userService.getUserByAccountNumber(accountNumber);
            user user = usersOptional.get();
            if (user.getUserBalance() >= amount && user.getStatus().equals("Active")) {
                emailService.sendEmail(user.getUserEmail(), "Successfully Withdrew Money", "you have been Successfully Withdrew: "+amount+"EP from your bank account");
                user.setUserBalance(user.getUserBalance() - amount);
                userService.updateUser(accountNumber, user);
                return true;
            }
            System.out.println("Insufficient funds.");
            return false;
    }

    public boolean deposit(Integer accountNumber,double amount){
        Optional<user> usersOptional=userService.getUserByAccountNumber(accountNumber);
        user user=usersOptional.get();
        if (!user.getStatus().equals("Deleted")) {
            emailService.sendEmail(user.getUserEmail(), "Successfully Deposited Money", "you have been Successfully Deposited: "+amount+"EP from your bank account"
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
        Optional<user> usersOptional = userService.getUserByAccountNumber(accountNumber);
        user user = usersOptional.get();
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
