package com.VidyutTech.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VidyutTech.models.User;

import java.util.Optional;

@Repository
    public interface UserRepo extends JpaRepository<User, Long> {

        public Optional<User> findByNumber(Long id);

    }
