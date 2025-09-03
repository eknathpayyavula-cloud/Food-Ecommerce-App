package eku.EcommerceApp.service;


import eku.EcommerceApp.model.UserDTO;
import eku.EcommerceApp.model.Users;
import eku.EcommerceApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public List<UserDTO> getAllUser() {
        System.out.println("FRom service DTO");
        return repo.findAll()
                .stream()
                .map(users -> new UserDTO(users.getUser_id(), users.getUser_name(), users.getRole(), users.getCreated_at()))
                .collect(Collectors.toList());
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users addUser(Users user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        System.out.println(user.getPassword());

        user.setCreated_at(new Date());


        return repo.save(user);
    }

    public void deleteByid(Long id) {

         repo.deleteById(id);
    }

    public Optional<Users> getById(Long id) {
        return repo.findById(id);
    }

   // @Cacheable("byName")
    public Optional<Users> findByUser_name(String user_name) {
        System.out.println(" Fetching From User Table DB...");
        return  repo.findByUser_name(user_name);
    }


    public void updateUser(Users existingUser) {
        repo.save(existingUser);
    }
}
