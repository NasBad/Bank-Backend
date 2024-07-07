package org.solarflare.BankBackend.systemFunc;

import org.solarflare.BankBackend.beans.user;
import org.solarflare.BankBackend.dao.userDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userCrudService {
    @Autowired
    private userDAO userDAO;

    @Autowired
    private emailService emailService;

    public user addUser(user user){
        System.out.println("Adding user...");
        user.setUserBalance(0);
        user.setStatus("Active");
        emailService.sendEmail(user.getUserEmail(), "MR. "+user.getUserName()+" Welcome to The National Bank of Egypt!", "Dear MR."+user.getUserName() +
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

    public List<user> getAllUsers(){
        System.out.println("Finding all users...");
        return userDAO.findAll();
    }

    public Optional<user> getUserByAccountNumber(Integer accountNumber){
        System.out.println("Finding user with the account Number: "+accountNumber);
        return userDAO.findByAccountNumber(accountNumber);
    }

    public user updateUser(Integer accountNumber, user userDetails){
        Optional<user> usersOptional = userDAO.findByAccountNumber(accountNumber);
        if(usersOptional.isPresent()){
            user user = usersOptional.get();
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
        Optional<user> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Deleting user with the account Number: "+accountNumber);
        user user = userDetail.get();
        user.setStatus("Deleted");
        user.setUserBalance(0.0);
        userDAO.save(user);
    }

    public void activateUser(Integer accountNumber){
        Optional<user> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Activating user with the account Number: "+accountNumber);
        user user = userDetail.get();
        user.setStatus("Active");
        userDAO.save(user);
    }

    public void freezUser(Integer accountNumber){
        Optional<user> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Freezing user with the account Number: "+accountNumber);
        user user = userDetail.get();
        user.setStatus("Freezed");
        userDAO.save(user);
    }

    public void blockUser(Integer accountNumber){
        Optional<user> userDetail = userDAO.findByAccountNumber(accountNumber);
        System.out.println("Blocking user with the account Number: "+accountNumber);
        user user = userDetail.get();
        user.setStatus("Blocked");
        userDAO.save(user);
    }

    public void sendReminderEmail(Integer accountNumber, String subject, String body) {
        Optional<user> userOptional = userDAO.findByAccountNumber(accountNumber);
        if (userOptional.isPresent()) {
            user user = userOptional.get();
            emailService.sendEmail(user.getUserEmail(), subject, body);
        } else {
            System.out.println("User not found.");
        }
    }
}
