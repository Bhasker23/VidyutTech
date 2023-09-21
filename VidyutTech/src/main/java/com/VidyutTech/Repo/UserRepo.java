package com.VidyutTech.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VidyutTech.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
