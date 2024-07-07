package org.solarflare.BankBackend.systemFuncAPI;

import org.solarflare.BankBackend.beans.users;
import org.solarflare.BankBackend.systemFunc.userCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
@RestController
@RequestMapping("user")
public class userCrudAPI {
    @Autowired
    private userCrudService userService;

    @PostMapping("/add")
    public users addUser(@RequestBody users user){
        return userService.addUser(user);
    }

    @GetMapping("/getAll")
    public List<users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get/")
    public ResponseEntity<users> getUserById(@RequestParam  Integer accountNumber) {
        Optional<users> user = userService.getUserByAccountNumber(accountNumber);
        return ResponseEntity.ok(user.get());
    }

    @PutMapping("/updateUser/")
    public ResponseEntity<users> updateUser(@RequestParam Integer accountNumber, @RequestBody users userDetails) {
        users updatedUser = userService.updateUser(accountNumber, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<Void> deleteUser(@RequestParam  Integer accountNumber){
        Optional<users> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.deleteUser(accountNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/activate/")
    public ResponseEntity<Void> activateUser(@RequestParam  Integer accountNumber){
        Optional<users> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.activateUser(accountNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/freez/")
    public ResponseEntity<Void> freezUser(@RequestParam  Integer accountNumber){
        Optional<users> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.freezUser(accountNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/block/")
    public ResponseEntity<Void> blockUser(@RequestParam  Integer accountNumber){
        Optional<users> userDetail = userService.getUserByAccountNumber(accountNumber);
        userService.blockUser(accountNumber);
        return ResponseEntity.ok().build();
    }

}
