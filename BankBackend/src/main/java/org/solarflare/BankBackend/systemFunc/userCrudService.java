package org.solarflare.BankBackend.systemFunc;

import org.solarflare.BankBackend.beans.users;
import org.solarflare.BankBackend.dao.userDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class userCrudService {
    @Autowired
    private userDAO userDAO;

    public users addUser( users user){
        System.out.println("Adding user...");
        user.setUserBalance(0);
        user.setStatus("Active");
        return userDAO.save(user);
    }

    public List<users> getAllUsers(){
        System.out.println("Finding all users...");
        return userDAO.findAll();
    }

    public Optional<users> getUserByAccountNumber(Integer accountNumber){
        System.out.println("Finding user with the account Number: "+accountNumber);
        return userDAO.findByAccountNumber(accountNumber);
    }

    public users updateUser(Integer accountNumber,users userDetails){
        Optional<users> usersOptional = userDAO.findByAccountNumber(accountNumber);
        if(usersOptional.isPresent()){
            users user = usersOptional.get();
            user.setUserName(userDetails.getUserName());
            user.setUserEmail(userDetails.getUserEmail());
            user.setUserPassword(userDetails.getUserPassword());
            return userDAO.save(user);
        }
        else {
            return null;
        }
    }

    public void deleteUser(Integer accountNumber){
        Optional<users> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Deleting user with the account Number: "+accountNumber);
        users user = userDetail.get();
        user.setStatus("Deleted");
        user.setUserBalance(0.0);
        userDAO.save(user);
    }

    public void activateUser(Integer accountNumber){
        Optional<users> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Activating user with the account Number: "+accountNumber);
        users user = userDetail.get();
        user.setStatus("Active");
        userDAO.save(user);
    }

    public void freezUser(Integer accountNumber){
        Optional<users> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Freezing user with the account Number: "+accountNumber);
        users user = userDetail.get();
        user.setStatus("Freezed");
        userDAO.save(user);
    }

    public void blockUser(Integer accountNumber){
        Optional<users> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Blocking user with the account Number: "+accountNumber);
        users user = userDetail.get();
        user.setStatus("Blocked");
        userDAO.save(user);
    }
}
