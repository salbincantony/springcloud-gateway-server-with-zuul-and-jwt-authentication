package com.salbin.gatewayserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salbin.gatewayserver.auth.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
//    @Query(value="{'email' : ?0}")
    User findByEmail(String email);
}