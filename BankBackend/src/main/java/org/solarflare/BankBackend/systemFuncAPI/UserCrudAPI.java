package org.solarflare.BankBackend.systemFuncAPI;

import org.solarflare.BankBackend.beans.User;
import org.solarflare.BankBackend.BL.UserBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.solarflare.BankBackend.BL.EmailBL;

import java.util.List;
import java.util.Optional;

@Component
@RestController
@RequestMapping("user")
public class UserCrudAPI {
    @Autowired
    private UserBL userService;
    @Autowired
    private EmailBL emailBL;

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get/")
    public ResponseEntity<User> getUserById(@RequestParam  Integer accountNumber) {
        Optional<User> user = userService.getUserByAccountNumber(accountNumber);
        return ResponseEntity.ok(user.get());
    }

    @PutMapping("/updateUser/")
    public ResponseEntity<User> updateUser(@RequestParam Integer accountNumber, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(accountNumber, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<Void> deleteUser(@RequestParam  Integer accountNumber){
        Optional<User> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.deleteUser(accountNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/activate/")
    public ResponseEntity<Void> activateUser(@RequestParam  Integer accountNumber){
        Optional<User> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.activateUser(accountNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/freez/")
    public ResponseEntity<Void> freezUser(@RequestParam  Integer accountNumber){
        Optional<User> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.freezUser(accountNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/block/")
    public ResponseEntity<Void> blockUser(@RequestParam  Integer accountNumber){
        Optional<User> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.blockUser(accountNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sendReminder/")
    public ResponseEntity<String> sendReminder(@RequestParam Integer accountNumber, @RequestParam String subject, @RequestParam String body) {
        userService.sendReminderEmail(accountNumber, subject, body);
        return ResponseEntity.ok("Reminder email sent.");
    }
}
