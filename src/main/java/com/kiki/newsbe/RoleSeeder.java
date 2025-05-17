package com.kiki.newsbe;

import com.kiki.newsbe.repositories.auth.RoleRepository;
import com.kiki.newsbe.repositories.entities.auth.RoleEntity;
import com.kiki.newsbe.utils.enumaration.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if(roleRepository.count() == 0) {
            for(RoleEnum roleEnum : RoleEnum.values()) {
                RoleEntity role = new RoleEntity();
                role.setName(roleEnum);
                role.setCreatedBy(createdBy());
                role.setCreatedDate(createdDate());

                roleRepository.save(role);
            }
        }
    }

    public String createdBy() {
        return "SYSTEM";
    }

    public Date createdDate() {
        return new Date();
    }
}
