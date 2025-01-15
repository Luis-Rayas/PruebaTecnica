package com.estafanini.pruebatecnica.LRCRR.repositories;

import com.estafanini.pruebatecnica.LRCRR.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.fh_eliminacion IS NULL")
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.fh_eliminacion IS NOT NULL")
    List<User> findAllDeleted();

}
