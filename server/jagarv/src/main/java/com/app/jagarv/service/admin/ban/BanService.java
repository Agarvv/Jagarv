package com.app.jagarv.service.admin.ban;

import com.app.jagarv.dto.user.BanUserDTO;
import com.app.jagarv.repository.user.BanRepository;
import com.app.jagarv.entity.user.Ban;

import java.time.LocalDate;

public class BanService
{
    private final BanRepository banRepository; 

    public BanService(BanRepository banRepository)
    {
        this.banRepository = banRepository;
    }

    public String banOrUnbanUser(BanUserDTO ban)
    {
        Ban existingBan = banRepository.findByUserId(ban.getUserId());
        if(existingBan != null)
        {
            banRepository.delete(existingBan);
            return "UNBANNED";
        }

        Ban newBan = new Ban();
        newBan.setUserId(ban.getUserId());
        newBan.setBanDate(LocalDate.now().toString());

        String expiryDate = LocalDate.now().plusMonths(1).toString();
        newBan.setBanExpiry(expiryDate);

        banRepository.save(newBan);

        return "BANNED";
    }
    
    public Boolean isBanned(Long userId)
    {
        return banRepository.existsByUserId(userId);
    }
}