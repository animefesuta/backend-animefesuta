package cn.baizhi958216.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.viewobject.UserVO;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public UserVO signup(UserVO userVO) {
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
        return userService.createUser(userVO);
    }

    public UserDO authenticate(UserVO userVO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userVO.getEmail(),
                userVO.getPassword());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return userRepository.findByEmail(userVO.getEmail())
                .orElseThrow();
    }
}