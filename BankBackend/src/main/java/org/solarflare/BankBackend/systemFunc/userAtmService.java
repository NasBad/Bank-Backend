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

    public boolean withdraw(Integer userId,int amount){
        Optional<users> usersOptional = userService.getUserById(userId);
            users user = usersOptional.get();
            if (user.getUserBalance() >= amount && user.isActive()) {
                user.setUserBalance(user.getUserBalance() - amount);
                userService.updateUser(userId, user);
                return true;
            }
            System.out.println("Insufficient funds.");
            return false;
    }

    public boolean deposit(Integer userId,int amount){
        Optional<users> usersOptional=userService.getUserById(userId);
        users user=usersOptional.get();
        user.setUserBalance(user.getUserBalance()+amount);
        userService.updateUser(userId,user);
        return true;
    }

    public int checkBalance(Integer userId)
    {
        Optional<users> usersOptional = userService.getUserById(userId);
        users user = usersOptional.get();
        return user.getUserBalance();
    }

    public boolean transfer(Integer userId1,Integer userId2, int amount){
        withdraw(userId1,amount);
        deposit(userId2,amount);
        return true;
    }
}
