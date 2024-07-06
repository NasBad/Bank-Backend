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
        user.setActive(true);
        return userDAO.save(user);
    }

    public List<users> getAllUsers(){
        System.out.println("Finding all users...");
        return userDAO.findAll();
    }

    public Optional<users> getUserById(Integer userId){
        System.out.println("Finding user with the id: "+userId);
        return userDAO.findById(userId);
    }

    public users updateUser(Integer userId,users userDetails){
        Optional<users> usersOptional = userDAO.findById(userId);
        if(usersOptional.isPresent()){
            users user = usersOptional.get();
            user.setUserName(userDetails.getUserName());
            user.setUserEmail(userDetails.getUserEmail());
            user.setUserPassword(userDetails.getUserPassword());
            user.setUserBalance(userDetails.getUserBalance());
            return userDAO.save(user);
        }
        else {
            return null;
        }
    }

    public void deleteUser(Integer userId){
        Optional<users> userDetail = userDAO.findById(userId);
        System.out.println("Deleting user with the id: "+userId);
        users user = userDetail.get();
        user.setActive(false);
        userDAO.save(user);
    }

}
