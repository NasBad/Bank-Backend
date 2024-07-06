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
    public ResponseEntity<users> getUserById(@RequestParam  Integer userId) {
        Optional<users> user = userService.getUserById(userId);
        return ResponseEntity.ok(user.get());
    }

    @PutMapping("/updateUser/")
    public ResponseEntity<users> updateUser(@RequestParam Integer userId, @RequestBody users userDetails) {
        users updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<Void> deleteUser(@RequestParam  Integer userId){
        Optional<users> userDetail = userService.getUserById(userId);
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }


}
