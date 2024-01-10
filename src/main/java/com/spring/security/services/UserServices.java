package com.spring.security.services;

import com.spring.security.dto.Mail;
import com.spring.security.entity.AppUser;
import com.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailServices mailServices;


    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    public void save(AppUser user) {
        Mail mail = new Mail(user.getEmail());
        mailServices.sendMail(mail);
        user.setVerifyCode(mail.getVerifyCode());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    public void update(AppUser user){
        userRepository.save(user);
    }

    public void verify(String verify, Long id) {
        AppUser user = findById(id).get();
        if (verify.equals(user.getVerifyCode())) {
            user.setIsVerify(1);
            update(user);
            user.setVerifyCode("0");
            update(user);
        }
        else
            throw new RuntimeException("invalid code");

    }

    public void activeAccount(Long id){
        AppUser user = findById(id).get();
        user.setIsActive(1);
        update(user);

    }

 public void unActive(Long id){
        AppUser user = findById(id).get();
        user.setIsActive(0);
        update(user);

    }


}
