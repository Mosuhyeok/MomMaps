package com.ssafy.mvc.security;

import com.ssafy.mvc.dto.UserDto;
import com.ssafy.mvc.model.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomeUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try{
            UserDto userDto = userRepository.select(username);
            return  userDto;
        }
        catch (Exception e){
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다");
        }
    }
}
