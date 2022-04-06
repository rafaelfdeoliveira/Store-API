package com.rafael.storeapi.repository;

import com.rafael.storeapi.model.Authority;
import com.rafael.storeapi.model.AuthorityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityKey> { }
