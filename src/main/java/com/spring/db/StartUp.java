package com.spring.db;

import com.spring.security.entity.AppUser;
import com.spring.security.entity.Authority;
import com.spring.security.services.AuthorityService;
import com.spring.security.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class StartUp implements CommandLineRunner {

    private final UserServices userServices;
    private final AuthorityService authorityService;


    @Override
    public void run(String... args) throws Exception {
        if (false) {
            List<Authority> authorities = authorityService.findAll();
            if (userServices.findAll().isEmpty()) {
                AppUser user = new AppUser("muh", ("123"), 1);
                user.getAuthorities().add(authorities.get(0));
                user.getAuthorities().add(authorities.get(1));
                user.getAuthorities().add(authorities.get(2));
                userServices.save(user);
                AppUser user0 = new AppUser("yas", ("456"), 1);
                user0.getAuthorities().add(authorities.get(1));
                user0.getAuthorities().add(authorities.get(2));
                userServices.save(user0);
                AppUser user1 = new AppUser("mus", ("789"), 1);
                user1.getAuthorities().add(authorities.get(2));
                userServices.save(user1);
            }
            if (authorityService.findAll().isEmpty()) {
                authorityService.insert(new Authority(1L, "ROLE_ADMIN"));
                authorityService.insert(new Authority(2L, "ROLE_MANGER"));
                authorityService.insert(new Authority(3L, "ROLE_USER"));
            }
        }
    }
}
