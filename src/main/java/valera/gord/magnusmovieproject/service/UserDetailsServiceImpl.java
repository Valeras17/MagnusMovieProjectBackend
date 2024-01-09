package valera.gord.magnusmovieproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import valera.gord.magnusmovieproject.dto.SignUpRequestDto;
import valera.gord.magnusmovieproject.dto.UserResponseDto;
import valera.gord.magnusmovieproject.entity.User;
import valera.gord.magnusmovieproject.error.BadRequestException;
import valera.gord.magnusmovieproject.error.MagnusMovieException;
import valera.gord.magnusmovieproject.repository.RoleRepository;
import valera.gord.magnusmovieproject.repository.UserRepository;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    //props:
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto signUp(SignUpRequestDto dto){
        var  userRole = roleRepository.findByNameIgnoreCase("ROLE_USER").orElseThrow(()->new MagnusMovieException("Please contact to admin"));

        var  byUser = userRepository.findByUsernameIgnoreCase(dto.getUsername().trim());
        var  byEmail = userRepository.findByEmailIgnoreCase(dto.getEmail().trim());
        if (byEmail.isPresent()){
            throw new BadRequestException("email","Email  already exist");
        } else if (byUser.isPresent()) {
            throw new BadRequestException("username","Username  already exist");
        }

        var user = new User(
                null,
                dto.getUsername(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword().trim()),
                List.of(),
                Set.of(userRole)
        );

        var savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDto.class);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        var roles = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);

    }
}
