package valera.gord.magnusmovieproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import valera.gord.magnusmovieproject.dto.SignInRequestDto;
import valera.gord.magnusmovieproject.dto.SignInResponseDto;
import valera.gord.magnusmovieproject.dto.SignUpRequestDto;
import valera.gord.magnusmovieproject.dto.UserResponseDto;
import valera.gord.magnusmovieproject.security.JWTProvider;
import valera.gord.magnusmovieproject.service.UserServiceImpl;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserServiceImpl authService;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody @Valid SignUpRequestDto dto) {
        return new ResponseEntity<>(authService.signUp(dto), HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto dto){
        var user = authService.loadUserByUsername(dto.getUsername());
        var savedPassword = user.getPassword();
        var givenPassword = dto.getPassword();
        if (passwordEncoder.matches(givenPassword, savedPassword)) {
            //grant:
            var token = jwtProvider.generateToken(user.getUsername());
            return ResponseEntity.ok(new SignInResponseDto(token));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
