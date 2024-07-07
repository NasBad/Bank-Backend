package org.solarflare.BankBackend.systemFunc;

import org.solarflare.BankBackend.beans.users;
import org.solarflare.BankBackend.dao.userDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class userAtmService {

    @Autowired
    private userCrudService userService;

    public boolean withdraw(Integer accountNumber,double amount){
        Optional<users> usersOptional = userService.getUserByAccountNumber(accountNumber);
            users user = usersOptional.get();
            if (user.getUserBalance() >= amount && user.getStatus().equals("Active")) {
                user.setUserBalance(user.getUserBalance() - amount);
                userService.updateUser(accountNumber, user);
                return true;
            }
            System.out.println("Insufficient funds.");
            return false;
    }

    public boolean deposit(Integer accountNumber,double amount){
        Optional<users> usersOptional=userService.getUserByAccountNumber(accountNumber);
        users user=usersOptional.get();
        if (!user.getStatus().equals("Deleted")) {
            user.setUserBalance(user.getUserBalance() + amount);
            userService.updateUser(accountNumber, user);
            return true;
        }
        System.out.println("Insufficient funds.");
        return false;
    }

    public double checkBalance(Integer accountNumber)
    {
        Optional<users> usersOptional = userService.getUserByAccountNumber(accountNumber);
        users user = usersOptional.get();
        if (!user.getStatus().equals("Deleted")) {
            return user.getUserBalance();
        }else{
            return 0;
        }
    }

    public boolean transfer(Integer accountNumber1,Integer accountNumber2, double amount){
        if(withdraw(accountNumber1,amount) && deposit(accountNumber2,amount))
        {
            return true;
        }
        return false;
    }
}
