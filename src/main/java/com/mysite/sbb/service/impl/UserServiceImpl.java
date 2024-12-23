package com.mysite.sbb.service.impl;


import com.mysite.sbb.domain.entity.SiteUser;
import com.mysite.sbb.domain.repository.UserRepository;
import com.mysite.sbb.global.exception.DataNotFoundException;
import com.mysite.sbb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스.
 * 사용자 생성, 조회 등의 기능을 제공.
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // 사용자 정보를 저장 및 조회하는 레포지토리
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화를 위한 PasswordEncoder

    /**
     * 새로운 사용자를 생성하고 데이터베이스에 저장.
     *
     * @param username 사용자 이름
     * @param email    이메일
     * @param password 비밀번호 (암호화 후 저장)
     * @return 생성된 사용자 객체
     */
    @Override
    public SiteUser create(String username, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username); // 사용자 이름 설정
        user.setEmail(email); // 이메일 설정
        user.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화 후 설정
        userRepository.save(user); // 데이터베이스에 사용자 저장
        return user;
    }

    /**
     * 사용자 이름(username)으로 사용자 정보를 조회.
     *
     * @param username 사용자 이름
     * @return 조회된 사용자 객체
     * @throws DataNotFoundException 사용자를 찾을 수 없을 때 발생
     */
    @Override
    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = userRepository.findByusername(username);
        if (siteUser.isPresent()) {
            return siteUser.get(); // 사용자 정보 반환
        } else {
            throw new DataNotFoundException("siteuser not found"); // 사용자 없음 예외
        }
    }
}
