package valera.gord.magnusmovieproject.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import valera.gord.magnusmovieproject.dto.SignUpRequestDto;
import valera.gord.magnusmovieproject.dto.UserPageDto;
import valera.gord.magnusmovieproject.dto.UserRequestDto;
import valera.gord.magnusmovieproject.dto.UserResponseDto;
import valera.gord.magnusmovieproject.entity.User;
import valera.gord.magnusmovieproject.error.BadRequestException;
import valera.gord.magnusmovieproject.error.MagnusMovieException;
import valera.gord.magnusmovieproject.error.ResourceNotFoundException;
import valera.gord.magnusmovieproject.repository.RoleRepository;
import valera.gord.magnusmovieproject.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService, UserDetailsService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponseDto createNewUser(UserRequestDto userRequestDto) {
        var entity = modelMapper.map(userRequestDto, User.class);
        var saved = userRepository.save(entity);
        return modelMapper.map(saved, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user,UserResponseDto.class))
                .toList();
    }
//handle method fo find by id
    private User findUserEntity(long id) {
        return userRepository.findById(id).orElseThrow(
                ()-> new  ResourceNotFoundException("User", id,"Not Found"));

    }


    @Override
    public UserResponseDto findUserById(long id) {
        User user = findUserEntity(id);
        return modelMapper.map(user,UserResponseDto.class);
    }



    @Override
    public UserResponseDto updateUserById(UserRequestDto userRequestDto, long id) {
        var userFromDB = findUserEntity(id);

        //update =>copy new props from dto,save
        userFromDB.setUsername(userRequestDto.getUsername()); //assert that id exists
        userFromDB.setEmail(userRequestDto.getEmail());
        userFromDB.setPassword(userRequestDto.getPassword());
        //save
        var saved = userRepository.save(userFromDB);
        //return response dto
        return modelMapper.map(saved,UserResponseDto.class);
    }

    @Override
    public UserResponseDto deleteUserById(long id) {
        User user = findUserEntity(id);
        userRepository.deleteById(id);
        return modelMapper.map(user, UserResponseDto.class);
    }



        @Override
        public List<UserResponseDto> deleteAllUsers() {
            // get all users
            List<User> users = userRepository.findAll();

            // users -> DTO
            List<UserResponseDto> userResponseDtos = users.stream()
                    .map(user -> modelMapper.map(user, UserResponseDto.class))
                    .collect(Collectors.toList());

            // delete from db
            userRepository.deleteAll();

            // deleted to dto
            return userResponseDtos;
        }

    @Override
    public UserPageDto getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.Direction.fromString(sortDir),sortBy);

        Page<User> page = userRepository.findAll(pageable);
        return UserPageDto.builder()
                .result(page.stream().map(user -> modelMapper.map(user,UserResponseDto.class)).toList())
                .pageSize(pageSize)
                .pageNo(pageNo)
                .totalAmountPages(page.getTotalPages())
                .totalAmountUsers(page.getTotalElements())
                .isLast(page.isLast())
                .isFirst(page.isFirst())
                .build();

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(()->new UsernameNotFoundException(username));
        var roles = user
                .getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
        return new org.springframework.security.core.userdetails
                .User(user.getUsername(),user.getPassword(),roles);
    }




    @Transactional
    public UserResponseDto signUp(SignUpRequestDto dto){
        //1) get the user role from role repo
        var  userRole = roleRepository
                .findByNameIgnoreCase("ROLE_USER")
                .orElseThrow(()->new MagnusMovieException("Please contact to admin"));

        //2) if email/username  exists -> Go sign in (Exception)
        var  byUser = userRepository.findByUsernameIgnoreCase(dto.getUsername().trim());
        var  byEmail = userRepository.findByEmailIgnoreCase(dto.getEmail().trim());
        if (byEmail.isPresent()){
            throw new BadRequestException("email","Email  already exist");
        } else if (byUser.isPresent()) {
            throw new BadRequestException("username","Username  already exist");
        }

        //3) val user = new User(...encoded-password
        var user = new User(
                null,
                dto.getUsername(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword().trim()),
                List.of(),
                Set.of(userRole)
        );

        //4) save
        var savedUser = userRepository.save(user);
        //5) response dto
        return modelMapper.map(savedUser, UserResponseDto.class);

    }













}


