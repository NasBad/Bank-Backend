package org.solarflare.BankBackend.systemFuncAPI;

import org.solarflare.BankBackend.beans.user;
import org.solarflare.BankBackend.systemFunc.userCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.solarflare.BankBackend.systemFunc.emailService;

import java.util.List;
import java.util.Optional;

@Component
@RestController
@RequestMapping("user")
public class userCrudAPI {
    @Autowired
    private userCrudService userService;
    @Autowired
    private emailService emailService;

    @PostMapping("/add")
    public user addUser(@RequestBody user user){
        return userService.addUser(user);
    }

    @GetMapping("/getAll")
    public List<user> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get/")
    public ResponseEntity<user> getUserById(@RequestParam  Integer accountNumber) {
        Optional<user> user = userService.getUserByAccountNumber(accountNumber);
        return ResponseEntity.ok(user.get());
    }

    @PutMapping("/updateUser/")
    public ResponseEntity<user> updateUser(@RequestParam Integer accountNumber, @RequestBody user userDetails) {
        user updatedUser = userService.updateUser(accountNumber, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<Void> deleteUser(@RequestParam  Integer accountNumber){
        Optional<user> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.deleteUser(accountNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/activate/")
    public ResponseEntity<Void> activateUser(@RequestParam  Integer accountNumber){
        Optional<user> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.activateUser(accountNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/freez/")
    public ResponseEntity<Void> freezUser(@RequestParam  Integer accountNumber){
        Optional<user> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.freezUser(accountNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/block/")
    public ResponseEntity<Void> blockUser(@RequestParam  Integer accountNumber){
        Optional<user> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.blockUser(accountNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sendReminder/")
    public ResponseEntity<String> sendReminder(@RequestParam Integer accountNumber, @RequestParam String subject, @RequestParam String body) {
        userService.sendReminderEmail(accountNumber, subject, body);
        return ResponseEntity.ok("Reminder email sent.");
    }
}
