package com.salbin.gatewayserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salbin.gatewayserver.auth.JwtToken;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken,String> {
}