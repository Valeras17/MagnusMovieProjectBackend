package valera.gord.magnusmovieproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valera.gord.magnusmovieproject.dto.UserPageDto;
import valera.gord.magnusmovieproject.dto.UserRequestDto;
import valera.gord.magnusmovieproject.dto.UserResponseDto;
import valera.gord.magnusmovieproject.entity.User;
import valera.gord.magnusmovieproject.error.ResourceNotFoundException;
import valera.gord.magnusmovieproject.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


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


}


