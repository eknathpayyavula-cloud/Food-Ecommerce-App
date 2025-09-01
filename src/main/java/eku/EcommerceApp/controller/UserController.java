package eku.EcommerceApp.controller;


import eku.EcommerceApp.jwt.JwtUtil;
import eku.EcommerceApp.model.UserDTO;
import eku.EcommerceApp.model.Users;
import eku.EcommerceApp.repository.UserRepo;
import eku.EcommerceApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")   // 👈 add this
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepo repo;

    @GetMapping("/getall")
    public List<UserDTO> getAllUser() {
        return service.getAllUser();
    }

    @GetMapping("/get/{id}")
    public Optional<Users> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/{user_name}")
    public Optional<Users> getByUser_name(@PathVariable String user_name) {
        return service.findByUser_name(user_name);
    }


    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody Users user) {
        if (service.findByUser_name(user.getUser_name()).isPresent()) {
            return ResponseEntity.badRequest().body("User with this "+user.getUser_name()+" already exists");
        }
        Users savedUser = service.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {


        if(user.getUser_name()==null || user.getUser_name().isEmpty() || user.getUser_name().isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "User Name Can't be Empty"));
        }

        Optional<Users> existingUser = service.findByUser_name(user.getUser_name());

        System.out.println("Login attempt for username: " + user.getUser_name() + " password: " + user.getPassword());


       // Users dbUser = existingUser.get(); // using Object from db

       // System.out.println("Raw password = " + user.getPassword());
       // System.out.println("Stored hash  = " + existingUser.get().getPassword());
        //System.out.println("Matches? " + passwordEncoder.matches(user.getPassword(), dbUser.getPassword()));


        if (existingUser.isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
                String token = jwtUtil.generateToken(existingUser.get().getUser_name(),existingUser.get().getRole());
                return ResponseEntity.ok(Map.of(
                        "token", token,
                        "username", existingUser.get().getUser_name(),
                        "role", existingUser.get().getRole()
                ));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Invalid password"));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Please Create Account"));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid token");
        }

        String token = authHeader.substring(7); // remove "Bearer "
        String username = jwtUtil.extractUsername(token); // extract username from JWT

        Optional<Users> user = service.findByUser_name(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(Map.of("username", user.get().getUser_name()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }



    @DeleteMapping("/delete/{id}")
    public void deleteByid(@PathVariable Long id) {
        service.deleteByid(id);
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody Users user) {
        Optional<Users> existingUserOpt = service.findByUser_name(user.getUser_name());

        if (existingUserOpt.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "User not found"));
        }

        Users existingUser = existingUserOpt.get();

        // ✅ Encode only the RAW password from input
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

        // ✅ Use save, not addUser (avoid creating new row)
        service.updateUser(existingUser);

        return ResponseEntity.ok(Map.of("message", "Password updated successfully"));
    }



}
