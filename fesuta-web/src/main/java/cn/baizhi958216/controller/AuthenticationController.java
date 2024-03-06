package cn.baizhi958216.controller;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.response.LoginResponse;
import cn.baizhi958216.service.AuthenticationService;
import cn.baizhi958216.utils.JwtService;
import cn.baizhi958216.viewobject.UserVO;

@CrossOrigin
@RequestMapping("/api/v1/auth")
@RestController
@ResponseResultBody
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(
            JwtService jwtService,
            AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public UserVO register(@RequestBody UserVO userVO) {
        return authenticationService.signup(userVO);
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody UserVO userVO) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            Optional<UserDO> authenticatedUser = authenticationService.authenticate(userVO);

            String jwtToken = jwtService.generateToken(authenticatedUser.get());
            loginResponse.setToken(jwtToken)
                    .setExpiresIn(jwtService.getExpirationTime());
        } catch (BadCredentialsException e) {
            throw new UsernameNotFoundException("用户名或密码错误!");
        }
        return loginResponse;
    }
}
