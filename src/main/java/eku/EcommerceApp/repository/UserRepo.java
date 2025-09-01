package eku.EcommerceApp.repository;

import eku.EcommerceApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

    @Query("SELECT u FROM Users u WHERE u.user_name = :userName")
    Optional<Users> findByUser_name(@Param("userName") String userName);

}

