package com.example.team8recipe.security;


import com.example.team8recipe.entity.User;
import com.example.team8recipe.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// UserDetailsService 가 자동으로 데이터베이스와 비교해서 생성하는데 그것을 사용하지않고 커스텀해서 사용
// UserDetail를 사용하게 되면 Security가 자동으로 해주느 인증시스템을 사용하지 않겠다고 설정한것과 같다.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + userId));

        return new UserDetailsImpl(user);
    }
}
