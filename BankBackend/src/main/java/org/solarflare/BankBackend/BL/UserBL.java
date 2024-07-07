package org.solarflare.BankBackend.BL;

import org.solarflare.BankBackend.beans.User;
import org.solarflare.BankBackend.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBL {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EmailBL emailBL;

    public User addUser(User user){
        System.out.println("Adding user...");
        user.setUserBalance(0);
        user.setStatus("Active");
        emailBL.sendEmail(user.getUserEmail(), "MR. "+user.getUserName()+" Welcome to The National Bank of Egypt!", "Dear MR."+user.getUserName() +
                "\n" +
                "Welcome to The National Bank of Egypt! We're thrilled to have you onboard as our newest customer.\n" +
                "\n" +
                "Attached, you'll find your account details and a quick guide to our services. Should you have any questions or need assistance, please don't hesitate to contact us.\n" +
                "\n" +
                "We look forward to assisting you in achieving your financial goals.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "Hisham Akasha\n" +
                "CEO of\n" +
                "The National Bank of Egypt");
        return userDAO.save(user);
    }

    public List<User> getAllUsers(){
        System.out.println("Finding all users...");
        return userDAO.findAll();
    }

    public Optional<User> getUserByAccountNumber(Integer accountNumber){
        System.out.println("Finding user with the account Number: "+accountNumber);
        return userDAO.findByAccountNumber(accountNumber);
    }

    public User updateUser(Integer accountNumber, User userDetails){
        Optional<User> usersOptional = userDAO.findByAccountNumber(accountNumber);
        if(usersOptional.isPresent()){
            User user = usersOptional.get();
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
        Optional<User> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Deleting user with the account Number: "+accountNumber);
        User user = userDetail.get();
        user.setStatus("Deleted");
        user.setUserBalance(0.0);
        userDAO.save(user);
    }

    public void activateUser(Integer accountNumber){
        Optional<User> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Activating user with the account Number: "+accountNumber);
        User user = userDetail.get();
        user.setStatus("Active");
        userDAO.save(user);
    }

    public void freezUser(Integer accountNumber){
        Optional<User> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Freezing user with the account Number: "+accountNumber);
        User user = userDetail.get();
        user.setStatus("Freezed");
        userDAO.save(user);
    }

    public void blockUser(Integer accountNumber){
        Optional<User> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Blocking user with the account Number: "+accountNumber);
        User user = userDetail.get();
        user.setStatus("Blocked");
        userDAO.save(user);
    }

    public void sendReminderEmail(Integer accountNumber, String subject, String body) {
        Optional<User> userOptional = userDAO.findByAccountNumber(accountNumber);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            emailBL.sendEmail(user.getUserEmail(), subject, body);
        } else {
            System.out.println("User not found.");
        }
    }
}
