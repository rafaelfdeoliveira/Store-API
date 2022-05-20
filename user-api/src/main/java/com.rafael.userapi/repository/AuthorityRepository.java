package com.rafael.userapi.repository;

import com.rafael.userapi.model.Authority;
import com.rafael.userapi.model.AuthorityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityKey> { }
