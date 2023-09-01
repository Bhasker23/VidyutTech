package com.VidyutTech.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VidyutTech.models.Battery;

@Repository
public interface BatteryRepo extends JpaRepository<Battery, Integer> {

}
