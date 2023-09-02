package com.VidyutTech.Service;

import org.springframework.stereotype.Service;

import com.VidyutTech.models.Battery;

@Service
public interface BatteryService {

	public Battery addBattery(Battery battery);
}
